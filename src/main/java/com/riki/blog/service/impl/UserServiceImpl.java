package com.riki.blog.service.impl;

import com.riki.blog.domain.User;
import com.riki.blog.dto.RegisterDto;
import com.riki.blog.repository.UserRepository;
import com.riki.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String createUser(RegisterDto registerDto) {
        User user = mapToEntity(registerDto);
        userRepository.save(user);
        return "User registered successfully!";
    }

    private User mapToEntity(RegisterDto registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return user;
    }

}
