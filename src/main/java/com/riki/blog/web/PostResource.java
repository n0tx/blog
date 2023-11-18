package com.riki.blog.web;

import com.riki.blog.dto.PostDto;
import com.riki.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.createPost(postDto));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }
}
