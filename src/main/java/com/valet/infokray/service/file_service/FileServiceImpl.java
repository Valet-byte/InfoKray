package com.valet.infokray.service.file_service;

import com.valet.infokray.config.annotation.WithoutValidation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
@WithoutValidation
public class FileServiceImpl implements FileService {

    @Value("${save-file-path}")
    private String path;

    @SneakyThrows
    @Override
    public File save(MultipartFile multipartFile) {
        Path filePath = Paths.get(path, System.nanoTime() + multipartFile.getOriginalFilename());
        Path res = Files.write(filePath, multipartFile.getBytes());
        return new File(String.valueOf(res));
    }

    @Override
    public File getFileByName(String fileName) {
        return new File(path + fileName);
    }
}
