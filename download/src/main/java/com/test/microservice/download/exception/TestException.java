package com.test.microservice.download.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TestException extends RuntimeException{
    private String description;
    private int code;
    private HttpStatus status;

}
