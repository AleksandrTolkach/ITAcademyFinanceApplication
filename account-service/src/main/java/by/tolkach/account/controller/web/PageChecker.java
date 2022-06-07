package by.tolkach.account.controller.web;

import by.tolkach.account.dto.SimplePageable;

public class PageChecker {

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
