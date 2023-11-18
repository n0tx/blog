package com.riki.blog.service;

import com.riki.blog.dto.PostDto;


public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(Long id);
}
