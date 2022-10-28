package com.valet.infokray.service.content_service.facade;

import com.valet.infokray.model.Content;
import com.valet.infokray.model.Person;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ContentFacade {
    Content save(Person person, Content content) throws IllegalArgumentException;

    Content save(Person person, String content, List<MultipartFile> files)
            throws IllegalArgumentException;

    List<Content> getContent(String text, int size, int page) throws IllegalArgumentException;

    byte[] getFileByName(String fileName) throws IllegalArgumentException;
}
