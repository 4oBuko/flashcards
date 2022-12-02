package com.flashcardsapi.initdata;

import com.flashcardsapi.entities.Color;

import java.util.ArrayList;
import java.util.List;

public class Colors {

    public static List<Color> getColors() {
        List<Color> colors = new ArrayList<>();
        colors.add(new Color("111", "test1"));
        colors.add(new Color("222", "test2"));
        colors.add(new Color("333", "test3"));
//        todo: add more colors and change codes and names in already added
        return colors;
    }
}
