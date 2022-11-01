package com.valet.infokray.service.file_service;

import com.valet.infokray.config.annotation.WithValidation;
import com.valet.infokray.config.annotation.WithoutValidation;
import java.io.File;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@WithValidation
public class FileServiceImplWithValidation implements FileService {

    @WithoutValidation private final FileService fileService;

    @Value("${save-file-path}")
    private String path;

    @Override
    public File save(MultipartFile multipartFile) {
        return fileService.save(multipartFile);
    }

    @Override
    public File getFileByName(String fileName) {
        File file = new File(path + fileName);
        if (file.exists()) {
            return fileService.getFileByName(fileName);
        } else {
            throw new IllegalArgumentException("File: " + fileName + " not exists!");
        }
    }
}
