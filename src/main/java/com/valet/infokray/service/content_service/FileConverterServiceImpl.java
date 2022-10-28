package com.valet.infokray.service.content_service;

import com.valet.infokray.config.annotation.WithoutValidation;
import com.valet.infokray.model.Content;
import com.valet.infokray.service.file_service.FileService;
import java.io.File;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@WithoutValidation
@RequiredArgsConstructor
public class FileConverterServiceImpl implements FileConverterService {

    private final FileService fileService;

    @Override
    public Content createContent(Content content, List<MultipartFile> files) {
        List<String> images = files.stream().map(fileService::save).map(File::getName).toList();
        content.setImages(images);
        return content;
    }
}
