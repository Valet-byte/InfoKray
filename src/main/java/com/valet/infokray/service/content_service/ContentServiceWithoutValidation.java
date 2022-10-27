package com.valet.infokray.service.content_service;

import com.valet.infokray.config.annotation.WithoutValidation;
import com.valet.infokray.model.Content;
import com.valet.infokray.model.Person;
import com.valet.infokray.repo.ContentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@WithoutValidation
@RequiredArgsConstructor
public class ContentServiceWithoutValidation implements ContentService {

    private final ContentRepo contentRepo;

    @Override
    public Content save(Person person, Content content) {
        content.setCreatorId(person.getId());
        return contentRepo.saveContent(content);
    }

    @Override
    public List<Content> getContent(String text, int size, int page) {
        Pageable pageable = PageRequest.of(page, size);
        return contentRepo.search(text, pageable);
    }
}
