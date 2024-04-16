package com.nagirich.datasense.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@RequiredArgsConstructor
public class HttpException extends RuntimeException {
  private final HttpStatus httpStatus;

  public HttpException(String message, HttpStatus httpStatus){
    super(message);
    this.httpStatus = httpStatus;

  }
}
