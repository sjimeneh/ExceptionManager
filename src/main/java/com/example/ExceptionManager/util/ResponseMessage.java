package com.example.ExceptionManager.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseMessage<T> {
    private boolean ok;
    private int codeStatus;
    private String message;
    private T body;
}
