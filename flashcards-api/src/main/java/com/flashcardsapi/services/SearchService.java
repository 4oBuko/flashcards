package com.flashcardsapi.services;

import com.flashcardsapi.entities.JwtPayload;
import com.flashcardsapi.entities.SearchResult;
import com.flashcardsapi.entities.db.FlashcardsSet;
import com.flashcardsapi.entities.db.Tag;
import com.flashcardsapi.utils.JwtPayloadReader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service

public class SearchService {

    private final EntityManager entityManager;

    public SearchService(EntityManager entityManager) {
        this.entityManager = entityManager;
        builder = entityManager.getCriteriaBuilder();
    }

    private final CriteriaBuilder builder;

    @Transactional
    public SearchResult searchByName(String name, Jwt jwt) {
        JwtPayload payload = JwtPayloadReader.getPayload(jwt);
        SearchResult searchResult = new SearchResult();

        searchResult.setSets(entityManager.createQuery(createSetQuery(name, payload.getUserId())).getResultList());
        searchResult.setTags(entityManager.createQuery(createTagQuery(name, payload.getUserId())).getResultList());
        return searchResult;
    }

    private CriteriaQuery<FlashcardsSet> createSetQuery(String name, Long userId) {
        CriteriaQuery<FlashcardsSet> setQuery = builder.createQuery(FlashcardsSet.class);
        Root<FlashcardsSet> setRoot = setQuery.from(FlashcardsSet.class);

        Predicate isOwner = builder.equal(setRoot.get("user"), userId);
        Predicate nameContains = builder.like(setRoot.get("name"), "%" + name + "%");
        Predicate setIsPublic = builder.equal(setRoot.get("isPublic"), true);

        setQuery.select(setRoot)
                .where(builder.or(isOwner, setIsPublic), nameContains);
        return setQuery;
    }

    private CriteriaQuery<Tag> createTagQuery(String name, Long userId) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> tagQuery = builder.createQuery(Tag.class);
        Root<Tag> tagRoot = tagQuery.from(Tag.class);

        Predicate isOwner = builder.equal(tagRoot.get("user"), userId);
        Predicate nameContains = builder.like(tagRoot.get("name"), "%" + name + "%");
        Predicate tagIsPublic = builder.equal(tagRoot.get("isPublic"), true);

        tagQuery.select(tagRoot)
                .where(builder.or(isOwner, tagIsPublic), nameContains);
        return tagQuery;
    }
}
