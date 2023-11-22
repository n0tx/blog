package com.riki.blog.service;

import com.riki.blog.dto.request.LoginDtoRequest;
import com.riki.blog.dto.request.RegisterDtoRequest;


public interface UserService {
    String register(RegisterDtoRequest registerDto);

    String login(LoginDtoRequest loginDto);
}
