package com.riki.blog.web;

import com.riki.blog.dto.request.LoginDtoRequest;
import com.riki.blog.dto.request.RegisterDtoRequest;
import com.riki.blog.dto.response.JwtAuthResponse;
import com.riki.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(
        name = "Users Resource"
)
public class UserResource {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create User REST API",
            description = "Create User REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RegisterDtoRequest registerDto) {
        String response = userService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Login User REST API",
            description = "Login User REST API is used to get token authentication"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDtoRequest loginDto) {
        String token = userService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return ResponseEntity.ok(jwtAuthResponse);
    }


}
