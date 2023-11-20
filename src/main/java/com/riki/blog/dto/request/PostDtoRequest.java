package com.riki.blog.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostDtoRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String body;
    @NotNull
    private Long userId;
}
