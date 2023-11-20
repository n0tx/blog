package com.riki.blog.dto.response;

import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorDetailsResponse {
    private final Date timestamp;
    private final String message;
    private final String details;


    public ErrorDetailsResponse(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

}
