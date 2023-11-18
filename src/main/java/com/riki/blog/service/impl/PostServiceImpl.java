package com.riki.blog.service.impl;

import com.riki.blog.domain.Post;
import com.riki.blog.domain.User;
import com.riki.blog.dto.PostDto;
import com.riki.blog.repository.PostRepository;
import com.riki.blog.repository.UserRepository;
import com.riki.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto) {
        Long userId = postDto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Invalid User Id: " + userId));

        Post post = mapToEntity(postDto);
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        return mapToDto(savedPost);
    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new RuntimeException("Invalid Post Id" + id));
        return mapToDto(post);
    }

    private Post mapToEntity(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    private PostDto mapToDto(Post post) {
        return modelMapper.map(post, PostDto.class);
    }
}
