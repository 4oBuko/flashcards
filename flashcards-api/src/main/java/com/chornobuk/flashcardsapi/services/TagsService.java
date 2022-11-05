package com.chornobuk.flashcardsapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chornobuk.flashcardsapi.entities.Tag;
import com.chornobuk.flashcardsapi.entities.User;
import com.chornobuk.flashcardsapi.repositories.TagRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagsService {
    private TagRepository tagRepository;

    public List<Tag> getTagsByUser(User user) {
        return tagRepository.findAllByUser(user);
    }

    public Tag getTagById(long tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    public void deleteTag(long tagId) {
        tagRepository.deleteById(tagId);
    }
}
