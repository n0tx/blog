package com.riki.blog.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostResource {
    @GetMapping(value = {"/hello", "/hello-blog"})
    public String helloBlog() {
        return "Hello-Blog";
    }
}
