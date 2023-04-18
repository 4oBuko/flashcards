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

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FlashcardsSetService {

    private FlashcardsSetRepository flashcardsSetRepository;
    private UserRepository userRepository;
    private LanguageRepository languageRepository;

    @Transactional
    public FlashcardsSet updateSet(UpdateFlashcardsSetDTO dto, Jwt jwt) {
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

        List<Tag> thirdPartyTags = fromDb.getTags().stream().filter(tag -> !tag.getUser().getId().equals(payload.getUserId())).toList();
        if(thirdPartyTags.isEmpty()) {
            fromDb.setPublic(dto.isPublic());
        }
        else if(!dto.isPublic()) {
            throw new AlreadyUsedCredentialsException("You cannot make this set private, because it is used by other users");
        }

        fromDb.setName(dto.getName());
        fromDb.setPublic(dto.isPublic());
        fromDb.setDescription(dto.getDescription());
        fromDb.setFlashcards(dto.getFlashcards());

        return flashcardsSetRepository.save(fromDb);
    }

    @Transactional
    public FlashcardsSet addNewSet(CreateFlashcardsSetDTO dto, Jwt jwt) {
        FlashcardsSet set = new FlashcardsSet();
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);

        User user = userRepository.findById(payload.getUserId()).orElseThrow(CustomEntityNotFoundException::new);
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

    public FlashcardsSet getSetById(long setId, Jwt jwt) {
        FlashcardsSet set = flashcardsSetRepository.findById(setId).orElseThrow(CustomEntityNotFoundException::new);
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
//        return error if set is private and user isn't author of the set
        if (set.isPublic() || set.getUser().getId().equals(payload.getUserId())) {
            return set;
        } else {
            throw new CustomAccessDeniedException("Access denied");
        }
    }

    @Transactional
    public void deleteSetById(long setId, Jwt jwt) {
        FlashcardsSet set = flashcardsSetRepository.findById(setId).orElseThrow(CustomEntityNotFoundException::new);
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        if (set.getUser().getId().equals(payload.getUserId())) {
            flashcardsSetRepository.deleteById(setId);
        }
        else {
            throw new CustomAccessDeniedException("Access denied. You are not the author of this set");
        }
        flashcardsSetRepository.deleteById(setId);
    }

    @Transactional
    public List<FlashcardsSet> getUserSetsById(Long userId, Jwt jwt) {
//        todo: if user id isn't equal with user id in jwt return public sets of user by id
        return flashcardsSetRepository.findAllByUser_id(userId);
    }
}
