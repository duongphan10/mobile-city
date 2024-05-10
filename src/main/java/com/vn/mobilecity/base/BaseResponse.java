package com.vn.mobilecity.base;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class BaseResponse {

    public static ResponseEntity<RestData<?>> success(Object data) {
        return success(HttpStatus.OK, data);
    }

    public static ResponseEntity<RestData<?>> success(Object message, Object data) {
        return success(HttpStatus.OK, message, data);
    }

    public static ResponseEntity<RestData<?>> success(MultiValueMap<String, String> header, Object data) {
        return success(HttpStatus.OK, header, data);
    }

    public static ResponseEntity<RestData<?>> success(HttpStatus status, Object data) {
        RestData<?> response = RestData.success(data);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<RestData<?>> success(HttpStatus status, Object message, Object data) {
        RestData<?> response = RestData.success(message, data);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<RestData<?>> success(HttpStatus status, MultiValueMap<String, String> header, Object data) {
        RestData<?> response = RestData.success(data);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.addAll(header);
        return ResponseEntity.status(status).headers(responseHeaders).body(response);
    }

    public static ResponseEntity<RestData<?>> error(HttpStatus status, Object message) {
        RestData<?> response = RestData.error(message);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<RestData<?>> error(HttpStatus status, Object message, Object data) {
        RestData<?> response = RestData.error(message, data);
        return new ResponseEntity<>(response, status);
    }

}
