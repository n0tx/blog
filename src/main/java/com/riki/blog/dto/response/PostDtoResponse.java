package com.riki.blog.dto.response;

import lombok.Data;

@Data
public class PostDtoResponse {
    private Long id;
    private String title;
    private String body;
    private Long userId;
}
