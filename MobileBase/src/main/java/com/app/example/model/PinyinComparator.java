package com.app.example.model;

import java.util.Comparator;

import com.app.example.model.Content;

public class PinyinComparator implements Comparator<Content> {

    public int compare(Content o1, Content o2) {
        if (o1.getLetter().equals("@")
                || o2.getLetter().equals("#")) {
            return -1;
        } else if (o1.getLetter().equals("#")
                || o2.getLetter().equals("@")) {
            return 1;
        } else {
            return o1.getLetter().compareTo(o2.getLetter());
        }
    }

}
