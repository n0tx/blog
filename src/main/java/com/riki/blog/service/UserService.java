package com.riki.blog.service;

import com.riki.blog.dto.request.RegisterDtoRequest;


public interface UserService {
    String createUser(RegisterDtoRequest registerDto);
}
