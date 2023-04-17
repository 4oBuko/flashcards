package com.flashcardsapi.services;

import com.flashcardsapi.dtos.flashcard.CreateFlashcardDTO;
import com.flashcardsapi.dtos.flashcardsset.CreateFlashcardsSetDTO;
import com.flashcardsapi.entities.JwtPayload;
import com.flashcardsapi.entities.db.*;
import com.flashcardsapi.exceptions.CustomEntityNotFoundException;
import com.flashcardsapi.repositories.*;
import com.flashcardsapi.utils.JwtPayloadReader;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FlashcardsSetService {

    private FlashcardsSetRepository flashcardsSetRepository;
    private FlashcardRepository flashcardRepository;
    private UserRepository userRepository;
    private LanguageRepository languageRepository;
    private TagRepository tagRepository;

    @Transactional
    public FlashcardsSet updateSet(FlashcardsSet setToUpdate) throws IllegalArgumentException {
        flashcardRepository.saveAll(setToUpdate.getFlashcards());
        return flashcardsSetRepository.save(setToUpdate);
    }

    @Transactional
    public FlashcardsSet addNewSet(CreateFlashcardsSetDTO dto, Jwt jwt) {
        FlashcardsSet set = new FlashcardsSet();
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);

        User user = userRepository.findById(payload.getUserId()).orElseThrow(CustomEntityNotFoundException::new);
        Language questionLanguage = languageRepository.findById(dto.getQuestionLanguageId()).orElseThrow(CustomEntityNotFoundException::new);
        Language answerLanguage = languageRepository.findById(dto.getAnswerLanguageId()).orElseThrow(CustomEntityNotFoundException::new);

        List<Flashcard> flashcards = new ArrayList<>();
        for (CreateFlashcardDTO cardDto : dto.getFlashcards()) {
            Flashcard flashcard = new Flashcard();
            flashcard.setQuestion(cardDto.getQuestion());
            flashcard.setAnswer(cardDto.getAnswer());
            flashcards.add(flashcard);
            flashcard.setSet(set);
        }
//        flashcardRepository.saveAll(flashcards);
        Iterable<Tag> iterable = tagRepository.findAllById(dto.getTagsId());
        List<Tag> tags = new ArrayList<>();
        iterable.forEach(tags::add);

        set.setUser(user);
        set.setName(dto.getName());
        set.setDescription(dto.getDescription());
        set.setPublic(dto.isPublic());
        set.setFlashcards(flashcards);
        set.setQuestionLanguage(questionLanguage);
        set.setAnswerLanguage(answerLanguage);
        set.setTags(tags);

        return flashcardsSetRepository.save(set);
    }

    public FlashcardsSet getSetById(long setId) {
        return flashcardsSetRepository.findById(setId).orElseThrow(CustomEntityNotFoundException::new);
    }

    @Transactional
    public void deleteSetById(long setId) {
        flashcardRepository.deleteAllBySetId(setId);
        flashcardsSetRepository.deleteById(setId);
    }

    @Transactional
    public List<FlashcardsSet> getUserSetsById(Long userId) {
        return flashcardsSetRepository.findAllByUser_id(userId);
    }
}
