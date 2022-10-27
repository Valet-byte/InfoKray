package com.valet.infokray.service.file_service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileService {
    File save(MultipartFile multipartFile);
    File getFileByName(String fileName);
}
