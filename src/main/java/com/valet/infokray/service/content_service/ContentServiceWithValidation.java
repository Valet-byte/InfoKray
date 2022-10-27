package com.valet.infokray.service.content_service;

import com.valet.infokray.config.annotation.WithValidation;
import com.valet.infokray.config.annotation.WithoutValidation;
import com.valet.infokray.model.Content;
import com.valet.infokray.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@WithValidation
public class ContentServiceWithValidation implements ContentService {

    @WithoutValidation
    private final ContentService contentService;

    @Override
    public Content save(Person person, Content content) throws IllegalArgumentException{
        if (content.getName().length() > 7 && content.getName().length() < 50){
            if (content.getType().getId() >= 0){
                return contentService.save(person, content);
            } else throw new IllegalArgumentException("Not valid type!");
        } else throw new IllegalArgumentException("name.length() must be greater than 7 and less than or equal to 50!");
    }

    @Override
    public List<Content> getContent(String text, int size, int page)throws IllegalArgumentException {
        if (size >= 1){
            if (page >= 0){
                return contentService.getContent(text, size, page);
            } else throw new IllegalArgumentException("page must be greater or equal to 0!");
        } else throw new IllegalArgumentException("size must be greater or equal to 1!");
    }
}
