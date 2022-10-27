package com.valet.infokray.service.content_service.facade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valet.infokray.model.Content;
import com.valet.infokray.model.Person;
import com.valet.infokray.service.content_service.ContentService;
import com.valet.infokray.service.content_service.FileConverterService;
import com.valet.infokray.service.file_service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentFacadeImpl implements ContentFacade {

    private final ContentService contentService;
    private final FileConverterService fileConverterService;
    private final FileService fileService;
    private final ObjectMapper mapper;

    @Override
    public Content save(Person person, Content content) throws IllegalArgumentException {
        return contentService.save(person, content);
    }

    @SneakyThrows
    @Override
    public Content save(Person person, String content, List<MultipartFile> files) throws IllegalArgumentException {
        Content content1 = mapper.readValue(content, Content.class);
        content1 = fileConverterService.createContent(content1, files);
        return contentService.save(person, content1);
    }

    @Override
    public List<Content> getContent(String text, int size, int page) throws IllegalArgumentException {
        return contentService.getContent(text, size, page);
    }

    @SneakyThrows
    @Override
    public byte[] getFileByName(String fileName) throws IllegalArgumentException {
        return Files.readAllBytes(fileService.getFileByName(fileName).toPath());
    }
}
