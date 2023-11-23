package com.riki.blog.web;

import com.riki.blog.domain.constant.PaginationSorting;
import com.riki.blog.dto.request.PostDtoRequest;
import com.riki.blog.dto.response.ListPostDtoResponse;
import com.riki.blog.dto.response.PostDtoResponse;
import com.riki.blog.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(
        name = "Posts Resource"
)
public class PostResource {

    @Autowired
    PostService postService;

    @Operation(
            summary = "Hello Test REST API",
            description = "Get Hello Test REST API is used to test for the first time in Development"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping(value = {"/hello", "/hello-blog"})
    public String helloBlog() {
        return "Hello-Blog";
    }

    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/posts")
    public ResponseEntity<PostDtoResponse> createPost(@Valid @RequestBody PostDtoRequest postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Post By Id REST API",
            description = "Get Post By Id REST API is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDtoResponse> getPostById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(
            summary = "Get All Posts REST API",
            description = "Get All Posts REST API is used to fetch all the posts from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )

    @GetMapping("/posts")
    public ListPostDtoResponse getAllPost(
            @RequestParam(value = "pageNo", defaultValue = PaginationSorting.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PaginationSorting.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationSorting.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationSorting.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    @Operation(
            summary = "update Post REST API",
            description = "Update Post REST API is used to update a particular post in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDtoResponse> updatePost(@Valid @RequestBody PostDtoRequest postDtoRequest,
                                                      @PathVariable(name = "id") Long id) {
        PostDtoResponse postDtoResponse = postService.updatePost(postDtoRequest, id);
        return new ResponseEntity<>(postDtoResponse, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete Post REST API",
            description = "Delete Post REST API is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bearer Authentication")

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") Long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully", HttpStatus.OK);
    }
}
