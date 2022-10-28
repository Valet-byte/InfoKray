package com.valet.infokray.service.content_service;

import com.valet.infokray.model.Content;
import com.valet.infokray.model.Person;
import java.util.List;

public interface ContentService {
    Content save(Person person, Content content) throws IllegalArgumentException;

    List<Content> getContent(String text, int size, int page) throws IllegalArgumentException;
}
