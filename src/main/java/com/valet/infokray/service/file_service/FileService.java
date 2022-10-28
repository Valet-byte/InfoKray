package com.valet.infokray.service.file_service;

import java.io.File;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    File save(MultipartFile multipartFile);

    File getFileByName(String fileName);
}
