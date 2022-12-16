package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.Color;
import com.flashcardsapi.entities.Tag;
import com.flashcardsapi.entities.User;

import java.util.ArrayList;
import java.util.List;

public class Tags {
    public static List<Tag> getTags() {
        List<Tag> tags = new ArrayList<>();
        User user = new User();
        user.setId(1L);
        tags.add(new Tag(user, "English", new Color(1L)));
        tags.add(new Tag(user, "Math", new Color(2L)));
        tags.add(new Tag(user, "Space", new Color(3L)));
        return tags;
    }
}
