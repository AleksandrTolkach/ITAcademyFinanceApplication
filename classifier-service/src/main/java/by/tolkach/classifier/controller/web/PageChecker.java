package by.tolkach.classifier.controller.web;

import by.tolkach.classifier.dto.SimplePageable;

public class PageChecker {

    private PageChecker() {
    }

    public static SimplePageable checkParameters(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        return new SimplePageable(page, size);
    }
}
