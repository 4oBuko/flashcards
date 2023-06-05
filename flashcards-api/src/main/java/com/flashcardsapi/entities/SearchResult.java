package com.flashcardsapi.entities;

import com.flashcardsapi.entities.db.FlashcardsSet;
import com.flashcardsapi.entities.db.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchResult {
    private List<FlashcardsSet> sets;
    private List<Tag> tags;
}
