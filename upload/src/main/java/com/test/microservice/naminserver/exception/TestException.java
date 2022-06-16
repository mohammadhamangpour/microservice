package com.test.microservice.naminserver.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class TestException extends RuntimeException{
    private String description;
    private int code;
    private HttpStatus status;

}
