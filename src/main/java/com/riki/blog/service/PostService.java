package com.riki.blog.service;

import com.riki.blog.domain.Post;
import com.riki.blog.domain.User;
import com.riki.blog.dto.request.PostDtoRequest;
import com.riki.blog.dto.response.ListPostDtoResponse;
import com.riki.blog.dto.response.PostDtoResponse;


public interface PostService {
    PostDtoResponse createPost(PostDtoRequest postDto);

    PostDtoResponse getPostById(Long id);

    ListPostDtoResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDtoResponse updatePost(PostDtoRequest postDtoRequest, Long id);

    void deletePostById(Long id);

    Post findPostByIdWithExceptionMessage(Long id);

    User findUserByIdWithExceptionMessage(Long id);
}
