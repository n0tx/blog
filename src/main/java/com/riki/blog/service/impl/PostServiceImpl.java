package com.riki.blog.service.impl;

import com.riki.blog.domain.Post;
import com.riki.blog.domain.User;
import com.riki.blog.dto.request.PostDtoRequest;
import com.riki.blog.dto.response.ListPostDtoResponse;
import com.riki.blog.dto.response.PostDtoResponse;
import com.riki.blog.exception.ResourceNotFoundException;
import com.riki.blog.repository.PostRepository;
import com.riki.blog.repository.UserRepository;
import com.riki.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public PostDtoResponse createPost(PostDtoRequest postDto) {
        Long userId = postDto.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Invalid User Id: " + userId));

        Post post = mapToEntity(postDto);
        post.setUser(user);

        Post savedPost = postRepository.save(post);

        return mapToDto(savedPost);
    }

    @Override
    public PostDtoResponse getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public ListPostDtoResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Post> posts = postRepository.findAll(pageable);

        List<Post> listOfPosts = posts.getContent();

        List<PostDtoResponse> content = listOfPosts.stream().map(this::mapToDto).collect(Collectors.toList());

        ListPostDtoResponse postDtoResponse = new ListPostDtoResponse();
        postDtoResponse.setContent(content);
        postDtoResponse.setPageNo(posts.getNumber());
        postDtoResponse.setPageSize(posts.getSize());
        postDtoResponse.setTotalElements(posts.getTotalElements());
        postDtoResponse.setTotalPages(posts.getTotalPages());
        postDtoResponse.setLastPage(posts.isLast());

        return postDtoResponse;
    }

    private Post mapToEntity(PostDtoRequest postDto) {
        return modelMapper.map(postDto, Post.class);
    }

    private PostDtoResponse mapToDto(Post post) {
        return modelMapper.map(post, PostDtoResponse.class);
    }
}
