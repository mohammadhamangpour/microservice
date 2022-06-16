package com.test.microservice.download.controller;

import com.test.microservice.download.dto.Url;
import com.test.microservice.download.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DownloadController {

    private DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @GetMapping("/images")
    public Url getImages() {
        return downloadService.downloadImages();
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable("fileName") String fileName){
        Resource resource = downloadService.loadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/test")
    public String test(){
        return "a string for test";
    }

}
