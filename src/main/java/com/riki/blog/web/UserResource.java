package com.riki.blog.web;

import com.riki.blog.dto.RegisterDto;
import com.riki.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody RegisterDto registerDto) {
        String response = userService.createUser(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
