package com.test.microservice.upload.service;

import com.test.microservice.upload.dto.SuccessDto;
import com.test.microservice.upload.exception.TestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {

    @Value(value = "${application.media-base}")
    private String directory;

    public SuccessDto saveFile(MultipartFile file) {
            Path path = Paths.get(directory);
            try {
                Files.createDirectories(path);
            } catch (Exception e) {
                throw new TestException("could not create directory", 400, HttpStatus.BAD_REQUEST);
            }
            try {
                Path dFile = path.resolve(file.getName());
                Files.copy(file.getInputStream(), dFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new TestException(e.getMessage(), 400, HttpStatus.BAD_REQUEST);
            }
            return new SuccessDto();
    }
}
