package com.assessmentmin.usermanagement.common.dto;

import lombok.Getter;

@Getter
public class PageNumber {
    private final Integer page;

    public PageNumber(Integer page) {
        this.page = validateRequestPage(page);
    }

    private int validateRequestPage(Integer page) {
        return page == null ? 0 : Math.max(page - 1, 0);
    }
}
