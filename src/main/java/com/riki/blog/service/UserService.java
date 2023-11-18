package com.riki.blog.service;

import com.riki.blog.dto.RegisterDto;


public interface UserService {
    String createUser(RegisterDto registerDto);
}
