package com.valet.infokray.repo;

import static org.junit.jupiter.api.Assertions.*;

import com.valet.infokray.model.Content;
import com.valet.infokray.model.ContentType;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class ContentRepoImplTest {

    @Autowired ContentRepoImpl contentRepo;

    @AfterEach
    void tearDown() {
        contentRepo.deleteAll();
    }

    @Test
    void saveContent() {
        Content content =
                new Content(null, "Test", "Body", List.of("123", "321"), ContentType.EVENT, 5L);
        Content c = contentRepo.saveContent(content);

        assertEquals(content, c);
    }

    @Test
    void search() {
        List<String> images = List.of("123", "312");
        List<Content> contents =
                List.of(
                        Content.builder()
                                .creatorId(5L)
                                .type(ContentType.EVENT)
                                .images(images)
                                .name("Test 1")
                                .build(),
                        Content.builder()
                                .creatorId(5L)
                                .type(ContentType.EVENT)
                                .images(images)
                                .name("Test 2")
                                .build(),
                        Content.builder()
                                .creatorId(5L)
                                .type(ContentType.EVENT)
                                .images(images)
                                .name("Test 3")
                                .build(),
                        Content.builder()
                                .creatorId(5L)
                                .type(ContentType.EVENT)
                                .images(images)
                                .name("111")
                                .build(),
                        Content.builder()
                                .creatorId(5L)
                                .type(ContentType.EVENT)
                                .images(images)
                                .name("444")
                                .build(),
                        Content.builder()
                                .creatorId(5L)
                                .type(ContentType.EVENT)
                                .images(images)
                                .name("Content6")
                                .build());

        contents.forEach(c -> contentRepo.saveContent(c));

        assertAll(
                () -> {
                    assertEquals(contentRepo.search("Test", PageRequest.of(0, 6)).size(), 3);
                    assertEquals(contentRepo.search("Test", PageRequest.of(0, 2)).size(), 2);
                    assertEquals(contentRepo.search("Test", PageRequest.of(1, 2)).size(), 1);
                    assertEquals(contentRepo.search("Test", PageRequest.of(2, 2)).size(), 0);
                    assertEquals(contentRepo.search("Content6", PageRequest.of(0, 2)).size(), 1);
                    assertEquals(contentRepo.search("1", PageRequest.of(0, 10)).size(), 2);
                    assertEquals(contentRepo.search("11", PageRequest.of(0, 10)).size(), 1);
                    assertEquals(contentRepo.search("4", PageRequest.of(0, 10)).size(), 1);
                });
    }
}
