package com.test.microservice.download.service;

import com.test.microservice.download.exception.TestException;
import com.test.microservice.download.dto.Url;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DownloadService {

    @Value(value = "${application.media-base}")
    private String directory;

    public Url downloadImages() {
        List<String> urls = new ArrayList<>();
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .toUriString();

        File folder = new File(directory);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < Objects.requireNonNull(listOfFiles).length; i++) {
            if (listOfFiles[i].isFile()) {
                urls.add(ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/download/")
                        .path(listOfFiles[i].getName())
                        .toUriString());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }
        return new Url(urls);
    }

    public Resource loadFile(String fileName) {
        Path path = Paths.get(directory);
        try {
            Resource file = new UrlResource(path.resolve(fileName).normalize().toUri());
            if (file.exists() || file.isReadable()) {
                return file;
            } else {
                throw new TestException("Could not found file", 404, HttpStatus.NOT_FOUND);
            }
        } catch (MalformedURLException e) {
            throw new TestException("Could not download file", 500, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
