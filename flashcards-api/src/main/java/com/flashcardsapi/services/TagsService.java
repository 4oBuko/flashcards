package com.flashcardsapi.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.flashcardsapi.entities.Color;
import com.flashcardsapi.entities.Tag;
import com.flashcardsapi.entities.User;
import com.flashcardsapi.repositories.TagRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TagsService {
    private final static Logger LOG = LoggerFactory.getLogger(TagsService.class);
    private TagRepository tagRepository;
    private ColorsService colorsService;

    public List<Tag> getTagsByUser(User user) {
        return tagRepository.findAllByUser(user);
    }

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

    public void createNewTag(String name, long colorId, User user) throws NullPointerException {
        Color color = colorsService.getColorById(colorId);
        if (color == null) {
            throw new NullPointerException();
        }
        Tag newTag = new Tag(user, name, color);
        tagRepository.save(newTag);
    }

    public Tag updateTag(Long tagId, Long newColorId, String newName) throws IllegalArgumentException {
        Tag tagToUpdate = tagRepository.findById(tagId).orElse(null);
        if (tagToUpdate == null) {
            throw new IllegalArgumentException();
        }
        if (!tagToUpdate.getColor().getId().equals(newColorId)) {
            tagToUpdate.setColor(colorsService.getColorById(newColorId));
        }
        if (!tagToUpdate.getName().equals(newName)) {
            tagToUpdate.setName(newName);
        }
        tagToUpdate = tagRepository.save(tagToUpdate);
        return tagRepository.save(tagToUpdate);
    }
}
