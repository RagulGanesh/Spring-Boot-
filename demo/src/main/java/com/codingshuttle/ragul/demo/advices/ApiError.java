package com.codingshuttle.ragul.demo.advices;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Setter
@Getter
@Builder
public class ApiError {
    private HttpStatus status;

    private String message;

    List<String> subErrors;
}
