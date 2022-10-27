package com.valet.infokray.service.content_service;

import com.valet.infokray.model.Content;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileConverterService {
    Content createContent(Content content, List<MultipartFile> files);
}
