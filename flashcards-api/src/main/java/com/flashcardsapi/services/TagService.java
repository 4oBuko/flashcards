package com.flashcardsapi.services;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.flashcardsapi.dtos.tag.CreateTagDTO;
import com.flashcardsapi.dtos.tag.UpdateTagDTO;
import com.flashcardsapi.entities.JwtPayload;
import com.flashcardsapi.entities.db.FlashcardsSet;
import com.flashcardsapi.exceptions.AlreadyUsedCredentialsException;
import com.flashcardsapi.exceptions.CustomAccessDeniedException;
import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import com.flashcardsapi.repositories.FlashcardsSetRepository;
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

    private FlashcardsSetRepository setsRepository;

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
            List<FlashcardsSet> sets = tag.getSets();
            for (FlashcardsSet set : sets) {
                set.getTags().remove(tag);
            }
            setsRepository.saveAll(sets);
            tagRepository.deleteById(id);
        } else {
            throw new CustomAccessDeniedException("Access denied. You are not the author of this tag");
        }
    }

    //    @Transactional
    public Tag create(CreateTagDTO dto, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        Color color = colorService.getColorById(dto.getColorId());
        User user = userService.getById(payload.getUserId());
        Tag tag = new Tag();
        List<FlashcardsSet> sets = setService.getById(dto.getSets()).stream()
                .filter(set -> set.isPublic() || set.getUser().getId().equals(payload.getUserId()))
                .toList();

        List<FlashcardsSet> updatedSets = new ArrayList<>();
        for (FlashcardsSet set : sets) {
            set.getTags().add(tag);
            updatedSets.add(set);
        }
        tag.setSets(updatedSets);
        tag.setUser(user);
        tag.setPublic(dto.isPublic());
        tag.setName(dto.getName());
        tag.setColor(color);

        Tag newTag = tagRepository.save(tag);
        setsRepository.saveAll(updatedSets);
        return newTag;
    }

    @Transactional
    public Tag update(UpdateTagDTO dto, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        Tag tag = tagRepository.findById(dto.getId()).orElseThrow(CustomEntityNotFoundException::new);
        if (tag.getUser().getId().equals(payload.getUserId())) {

            List<FlashcardsSet> tagSets = tag.getSets();
            Iterable<FlashcardsSet> iterable = setsRepository.findAllById(dto.getSets());
            List<FlashcardsSet> updatedSets = new LinkedList<>();
            iterable.forEach(updatedSets::add);
            List<FlashcardsSet> newSets = updatedSets.stream()
                    .filter(set -> !tagSets.contains(set))
                    .filter(set -> set.isPublic() || set.getUser().getId().equals(payload.getUserId()))
                    .peek(set -> set.getTags().add(tag))
                    .toList();
            List<FlashcardsSet> removedSets = tagSets.stream()
                    .filter(set -> !updatedSets.contains(set))
                    .peek(set -> set.getTags().remove(tag))
                    .toList();

            if (!tag.getColor().getId().equals(dto.getColorId())) {
                Color color = colorService.getColorById(dto.getColorId());
                tag.setColor(color);
            }
            tagSets.addAll(newSets);
            tagSets.removeAll(removedSets);
            tag.setSets(tagSets);
            tag.setName(dto.getName());
            tag.setPublic(dto.isPublic());
            setsRepository.saveAll(removedSets);
            setsRepository.saveAll(tagSets);

            Tag newTag = tagRepository.save(tag);
            return newTag;
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

    public void likeTag(long tagId, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userService.getById(payload.getUserId());
        Tag tag = tagRepository.findById(tagId).orElseThrow(CustomEntityNotFoundException::new);
//        if(tag.getUser().getId().equals(payload.getUserId())) {
//            throw new AlreadyUsedCredentialsException("You cannot add your tag to favorites");
//        }
        if (tagRepository.existsById(tagId)) {
            user.getLikedTags().add(tagId);
            userService.updateFavorites(user);
        } else {
            throw new CustomEntityNotFoundException("Tag by id doesn't exist");
        }
    }

    public void unlikeTag(long tagId, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userService.getById(payload.getUserId());
        user.getLikedTags().remove(tagId);
        userService.updateFavorites(user);
    }

    public List<Tag> getUserLikes(Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userService.getById(payload.getUserId());
        Iterable<Tag> iterable = tagRepository.findAllById(user.getLikedTags());
        List<Tag> likedTags = new ArrayList<>();
        iterable.forEach(likedTags::add);
        return likedTags;
    }
}
