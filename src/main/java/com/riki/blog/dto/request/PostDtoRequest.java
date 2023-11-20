package com.riki.blog.dto.request;

import lombok.Data;

@Data
public class PostDtoRequest {
    private Long id;
    private String title;
    private String body;
    private Long userId;
}
