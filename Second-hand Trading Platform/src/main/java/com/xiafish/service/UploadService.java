package com.xiafish.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface UploadService {
    public List<String> upload(MultipartFile[] photos) throws IOException;
}
