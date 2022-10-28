package com.valet.infokray.controller;

import com.valet.infokray.model.Content;
import com.valet.infokray.model.Person;
import com.valet.infokray.service.content_service.facade.ContentFacade;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ContentController {

    private final ContentFacade contentService;

    @PostMapping("/addContent")
    public ResponseEntity<?> content(
            @AuthenticationPrincipal Person person, @RequestBody Content content) {
        try {
            return ResponseEntity.ok(contentService.save(person, content));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getContent")
    public ResponseEntity<?> getContent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam String search) {
        try {
            return ResponseEntity.ok(contentService.getContent(search, size, page));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addContentWithFiles")
    public ResponseEntity<?> content(
            @AuthenticationPrincipal Person person,
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("content") String content) {
        try {
            return ResponseEntity.ok(contentService.save(person, content, files));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> getImage(@RequestParam String fileName) {
        try {
            return ResponseEntity.ok(contentService.getFileByName(fileName));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
