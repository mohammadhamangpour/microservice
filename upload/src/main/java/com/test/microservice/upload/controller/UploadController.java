package com.test.microservice.upload.controller;

import com.test.microservice.upload.dto.SuccessDto;
import com.test.microservice.upload.service.UploadService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadController {

    private UploadService uploadService;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/upload")
    public SuccessDto uploadFile(@RequestPart("file") MultipartFile file) {

        return uploadService.saveFile(file);
    }

    @GetMapping("/test")
    public String test(){
        return "return a string for test";
    }
}
