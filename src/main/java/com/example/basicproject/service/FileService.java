package com.example.basicproject.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadImg(MultipartFile file,String storageLocation) throws IOException;

    void showImg(String id, HttpServletResponse response) throws IOException;
}
