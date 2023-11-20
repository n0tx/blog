package com.riki.blog.dto.request;

import lombok.Data;

@Data
public class RegisterDtoRequest {
    private String name;
    private String username;
    private String email;
    private String password;
}
