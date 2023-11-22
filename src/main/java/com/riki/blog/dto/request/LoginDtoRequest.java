package com.riki.blog.dto.request;

import lombok.Data;

@Data
public class LoginDtoRequest {
    private String usernameOrEmail;
    private String password;
}
