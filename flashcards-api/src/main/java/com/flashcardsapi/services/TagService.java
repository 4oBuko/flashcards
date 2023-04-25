package com.flashcardsapi.services;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import com.flashcardsapi.dtos.tag.CreateTagDTO;
import com.flashcardsapi.dtos.tag.UpdateTagDTO;
import com.flashcardsapi.entities.JwtPayload;
import com.flashcardsapi.entities.db.FlashcardsSet;
import com.flashcardsapi.exceptions.CustomAccessDeniedException;
import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import com.flashcardsapi.utils.JwtPayloadReader;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.db.Color;
import com.flashcardsapi.entities.db.Tag;
import com.flashcardsapi.entities.db.User;
import com.flashcardsapi.repositories.TagRepository;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TagService {

    private TagRepository tagRepository;

    private ColorService colorService;

    private UserService userService;

    private FlashcardsSetService setService;

    public Tag getTagById(long id, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        Tag tag = tagRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        if (tag.isPublic() || tag.getUser().getId().equals(payload.getUserId())) {
            return tag;
        } else {
            throw new CustomAccessDeniedException("Access denied");
        }
    }

    public void deleteById(long id, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        Tag tag = tagRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        if (tag.getUser().getId().equals(payload.getUserId())) {
            tagRepository.deleteById(id);
        } else {
            throw new CustomAccessDeniedException("Access denied. You are not the author of this tag");
        }
    }

    @Transactional
    public Tag create(CreateTagDTO dto, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        Color color = colorService.getColorById(dto.getColorId());
        User user = userService.getById(payload.getUserId());


        Tag tag = new Tag();
        List<FlashcardsSet> sets = setService.getById(dto.getSets()).stream()
                .filter(set -> set.isPublic() || set.getUser().getId().equals(payload.getUserId()))
                .peek(set -> set.getTags().add(tag))
                .toList();

        tag.setSets(sets);
        tag.setUser(user);
        tag.setPublic(dto.isPublic());
        tag.setName(dto.getName());
        tag.setColor(color);
        return tagRepository.save(tag);
    }

    @Transactional
    public Tag update(UpdateTagDTO dto, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        Tag tag = tagRepository.findById(dto.getId()).orElseThrow(CustomEntityNotFoundException::new);
        if (tag.getUser().getId().equals(payload.getUserId())) {

            List<FlashcardsSet> sets = new ArrayList<>();
            setService.getById(dto.getSets()).stream()
                    .filter(set -> set.isPublic() || set.getUser().getId().equals(payload.getUserId()))
                    .peek(set -> set.getTags().add(tag))
                    .forEach(sets::add);

            if (!tag.getColor().getId().equals(dto.getColorId())) {
                Color color = colorService.getColorById(dto.getColorId());
                tag.setColor(color);
            }
            tag.setSets(sets);
            tag.setName(dto.getName());
            tag.setPublic(dto.isPublic());
            return tagRepository.save(tag);
        } else {
            throw new CustomAccessDeniedException("Access denied. Your are not the author of this tag!");
        }
    }

    @Transactional
    public List<Tag> getAllByUserId(Long userId) {
        return tagRepository.findAllByUser_id(userId).stream().filter(Tag::isPublic).toList();
    }

    public List<Tag> getUserTags(@AuthenticationPrincipal Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        return tagRepository.findAllByUser_id(payload.getUserId());
    }
}
