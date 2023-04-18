package com.flashcardsapi.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.db.Color;
import com.flashcardsapi.entities.db.Tag;
import com.flashcardsapi.entities.db.User;
import com.flashcardsapi.repositories.TagRepository;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class TagsService {
    private final static Logger LOG = LoggerFactory.getLogger(TagsService.class);
    private TagRepository tagRepository;
    private ColorService colorService;

    public Tag getTagById(long tagId) {
        return tagRepository.findById(tagId).orElse(null);
    }

    public void deleteTag(long tagId) {
        try {
            tagRepository.deleteById(tagId);
        } catch (EmptyResultDataAccessException e) {
            LOG.debug("tag by " + tagId + "not found");
        }
    }

    public Tag createNewTag(String name, long colorId, User user) throws NullPointerException {
        Color color = colorService.getColorById(colorId);
        if (color == null) {
            throw new NullPointerException();
        }
        Tag newTag = new Tag(user, name, color);
        return tagRepository.save(newTag);
    }

    public Tag updateTag(Long tagId, Long newColorId, String newName) throws IllegalArgumentException {
        Tag tagToUpdate = tagRepository.findById(tagId).orElse(null);
        if (tagToUpdate == null) {
            throw new IllegalArgumentException();
        }
        if (!tagToUpdate.getColor().getId().equals(newColorId)) {
            tagToUpdate.setColor(colorService.getColorById(newColorId));
        }
        if (!tagToUpdate.getName().equals(newName)) {
            tagToUpdate.setName(newName);
        }
        tagToUpdate = tagRepository.save(tagToUpdate);
        return tagRepository.save(tagToUpdate);
    }

    @Transactional
    public List<Tag> getUserTagsById(Long userId) {
        //        todo: if user id isn't equal with user id in jwt return public tags of user by id
        return tagRepository.findAllByUser_id(userId);
    }
}
