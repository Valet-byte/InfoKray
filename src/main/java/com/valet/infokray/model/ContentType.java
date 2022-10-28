package com.valet.infokray.model;

import lombok.NonNull;

public enum ContentType {
    ADD(1L),
    EVENT(2L),
    INSTITUTION(3L);

    private final Long id;

    public static ContentType getContentTypeById(@NonNull Long id) {
        if (id == 1L) {
            return ADD;
        } else if (id == 2L) {
            return EVENT;
        } else if (id == 3L) {
            return INSTITUTION;
        }
        return null;
    }

    ContentType(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
