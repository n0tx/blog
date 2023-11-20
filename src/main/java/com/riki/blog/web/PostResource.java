package com.riki.blog.web;

import com.riki.blog.domain.constant.PaginationSorting;
import com.riki.blog.dto.request.PostDtoRequest;
import com.riki.blog.dto.response.ListPostDtoResponse;
import com.riki.blog.dto.response.PostDtoResponse;
import com.riki.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostResource {

    @Autowired
    PostService postService;

    @GetMapping(value = {"/hello", "/hello-blog"})
    public String helloBlog() {
        return "Hello-Blog";
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDtoResponse> createPost(@RequestBody PostDtoRequest postDto) {
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDtoResponse> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @GetMapping("/posts")
    public ListPostDtoResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = PaginationSorting.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationSorting.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationSorting.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationSorting.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }
}
