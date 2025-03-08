package com.example.basicproject.controller.file;

import com.example.basicproject.dto.ResultDto;
import com.example.basicproject.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
public class FileController {


    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/img/{id}")
    public void showImg(@PathVariable(name = "id") String id, HttpServletResponse response) throws IOException {
        fileService.showImg(id,response);
    }

    @PostMapping("/uploadImg")
    public ResultDto<String> uploadImg(@RequestParam("file") MultipartFile file) throws IOException {

        return ResultDto.createSuccess(fileService.uploadImg(file));
    }
}
