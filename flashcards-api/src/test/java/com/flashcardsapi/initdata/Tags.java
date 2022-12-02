package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.Tag;

import java.util.ArrayList;
import java.util.List;

public class Tags {
    public static List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1L, "English", 1L));
        tags.add(new Tag(1L, "Math", 2L));
        tags.add(new Tag(1L, "Space", 3L));
        return tags;
    }
}
