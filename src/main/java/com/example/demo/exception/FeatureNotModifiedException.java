package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class FeatureNotModifiedException  extends RuntimeException{

    public FeatureNotModifiedException(String exception) {
        super(exception);
    }
}
