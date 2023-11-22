package com.riki.blog.service.impl;

import com.riki.blog.domain.Post;
import com.riki.blog.domain.Role;
import com.riki.blog.domain.User;
import com.riki.blog.dto.request.LoginDtoRequest;
import com.riki.blog.dto.request.RegisterDtoRequest;
import com.riki.blog.exception.ApiException;
import com.riki.blog.repository.PostRepository;
import com.riki.blog.repository.RoleRepository;
import com.riki.blog.repository.UserRepository;
import com.riki.blog.security.JwtTokenProvider;
import com.riki.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public String register(RegisterDtoRequest registerDto) {

        // check exists by username
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // check exists by email
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = mapToEntity(registerDto);

        List<Post> posts = new ArrayList<>();
        Optional<Post> post = postRepository.findByUserId(user.getId());

        post.ifPresent(posts::add);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").orElse(null);
        roles.add(userRole);


        user.setPosts(posts);
        user.setRoles(roles);

        for (int i = 0; i <= userRepository.count(); i++) {
            try {
                userRepository.save(user);
            } catch (DataIntegrityViolationException ignored) {
            }
        }

        return "User registered successfully!";
    }

    @Override
    public String login(LoginDtoRequest loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    private User mapToEntity(RegisterDtoRequest registerDto) {
        User user = modelMapper.map(registerDto, User.class);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        return user;
    }

}
