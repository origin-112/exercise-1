package com.rui.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String fileUpload(MultipartFile file);
}
