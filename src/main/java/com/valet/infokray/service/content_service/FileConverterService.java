package com.valet.infokray.service.content_service;

import com.valet.infokray.model.Content;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface FileConverterService {
    Content createContent(Content content, List<MultipartFile> files);
}
