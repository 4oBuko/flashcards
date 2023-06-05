package com.flashcardsapi.services;

import com.flashcardsapi.dtos.flashcardsset.CreateFlashcardsSetDTO;
import com.flashcardsapi.dtos.flashcardsset.UpdateFlashcardsSetDTO;
import com.flashcardsapi.entities.JwtPayload;
import com.flashcardsapi.entities.db.*;
import com.flashcardsapi.exceptions.AlreadyUsedCredentialsException;
import com.flashcardsapi.exceptions.CustomAccessDeniedException;
import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import com.flashcardsapi.repositories.*;
import com.flashcardsapi.utils.JwtPayloadReader;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FlashcardsSetService {

    private FlashcardsSetRepository flashcardsSetRepository;
    private TagRepository tagRepository;
    private UserService userService;
    private LanguageRepository languageRepository;

    @Transactional
    public FlashcardsSet update(UpdateFlashcardsSetDTO dto, Jwt jwt) {
        FlashcardsSet fromDb = flashcardsSetRepository.findById(dto.getId()).orElseThrow(CustomEntityNotFoundException::new);
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);

        if (!Objects.equals(fromDb.getUser().getId(), payload.getUserId())) {
            throw new CustomAccessDeniedException("You don't have permissions to change this set!");
        }

        if (!fromDb.getQuestionLanguage().getId().equals(dto.getQuestionLanguageId())) {
            Language questionLanguage = languageRepository.findById(dto.getQuestionLanguageId()).orElseThrow(CustomEntityNotFoundException::new);
            fromDb.setQuestionLanguage(questionLanguage);
        }

        if (!fromDb.getAnswerLanguage().getId().equals(dto.getAnswerLanguageId())) {
            Language answerLanguage = languageRepository.findById(dto.getAnswerLanguageId()).orElseThrow(CustomEntityNotFoundException::new);
            fromDb.setAnswerLanguage(answerLanguage);
        }

        for (int i = 0; i < dto.getFlashcards().size(); i++) {
            dto.getFlashcards().get(i).setIndex(i);
        }

        fromDb.setPublic(dto.isPublic());
        fromDb.setName(dto.getName());
        fromDb.setPublic(dto.isPublic());
        fromDb.setDescription(dto.getDescription());
        fromDb.setFlashcards(dto.getFlashcards());

        return flashcardsSetRepository.save(fromDb);
    }

    @Transactional
    public FlashcardsSet create(CreateFlashcardsSetDTO dto, Jwt jwt) {
        FlashcardsSet set = new FlashcardsSet();
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);

        User user = userService.getById(payload.getUserId());
        Language questionLanguage = languageRepository.findById(dto.getQuestionLanguageId()).orElseThrow(CustomEntityNotFoundException::new);
        Language answerLanguage = languageRepository.findById(dto.getAnswerLanguageId()).orElseThrow(CustomEntityNotFoundException::new);

        for (int i = 0; i < dto.getFlashcards().size(); i++) {
            dto.getFlashcards().get(i).setIndex(i);
        }

        set.setUser(user);
        set.setName(dto.getName());
        set.setDescription(dto.getDescription());
        set.setPublic(dto.isPublic());
        set.setFlashcards(dto.getFlashcards());
        set.setQuestionLanguage(questionLanguage);
        set.setAnswerLanguage(answerLanguage);

        return flashcardsSetRepository.save(set);
    }

    public FlashcardsSet getById(long id, Jwt jwt) {
        FlashcardsSet set = flashcardsSetRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
//        return error if set is private and user isn't author of the set
        if (set.isPublic() || set.getUser().getId().equals(payload.getUserId())) {
            return set;
        } else {
            throw new CustomAccessDeniedException("Access denied");
        }
    }

    @Transactional
    public void deleteById(long id, Jwt jwt) {
        FlashcardsSet set = flashcardsSetRepository.findById(id).orElseThrow(CustomEntityNotFoundException::new);
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        if (set.getUser().getId().equals(payload.getUserId())) {
            List<Tag> tags = set.getTags();
            for(Tag tag:  tags) {
                tag.getSets().remove(set);
            }
            tagRepository.saveAll(tags);
            flashcardsSetRepository.deleteById(id);
        } else {
            throw new CustomAccessDeniedException("Access denied. You are not the author of this set");
        }
    }


    public List<FlashcardsSet> getAllByUserId(Long id, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        List<FlashcardsSet> sets = flashcardsSetRepository.findAllByUser_id(id);
        return sets.stream()
                .filter(set -> set.isPublic() || set.getUser().getId().equals(payload.getUserId()))
                .toList();
    }

    public List<FlashcardsSet> getUsersSets(Jwt jwt) {
        JwtPayload jwtPayload = JwtPayloadReader.getPayload(jwt);
        return flashcardsSetRepository.findAllByUser_id(jwtPayload.getUserId());
    }

    @Transactional
    public List<FlashcardsSet> getById(List<Long> id) {
        Iterable<FlashcardsSet> iterable = flashcardsSetRepository.findAllById(id);
        List<FlashcardsSet> list = new LinkedList<>();
        iterable.forEach(list::add);
        return list;
    }

    public void likeSet(long setId, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userService.getById(payload.getUserId());
        FlashcardsSet set = flashcardsSetRepository.findById(setId).orElseThrow(CustomEntityNotFoundException::new);
//        if(set.getUser().getId().equals(payload.getUserId())) {
//            throw new AlreadyUsedCredentialsException("You cannot add your set in favorites");
//        }
        if (flashcardsSetRepository.existsById(setId)) {
            user.getLikedSets().add(setId);
            userService.updateFavorites(user);
        } else {
            throw new CustomEntityNotFoundException("Tag by id doesn't exist");
        }
    }

    public void unlikeSet(long setId, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userService.getById(payload.getUserId());
        user.getLikedSets().remove(setId);
        userService.updateFavorites(user);
    }

    public List<FlashcardsSet> getUserLikes(Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        User user = userService.getById(payload.getUserId());
        Iterable<FlashcardsSet> iterable = flashcardsSetRepository.findAllById(user.getLikedSets());
        List<FlashcardsSet> likedSets = new ArrayList<>();
        iterable.forEach(likedSets::add);
        return likedSets;
    }
}
