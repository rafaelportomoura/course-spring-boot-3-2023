package com.example.springboot.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatusCode;

public class ApiException extends Exception {
  private final HttpStatusCode statusCode;
  private final HashMap<String, String> body;

  public ApiException(String message, HttpStatusCode statusCode) {
    super(message);
    this.body = new HashMap();
    this.body.put("message", message);
    this.statusCode = statusCode;
  }

  public HttpStatusCode getStatusCode() {
    return statusCode;
  }

  public HashMap<String, String> getBody() {
    return body;
  }
}
