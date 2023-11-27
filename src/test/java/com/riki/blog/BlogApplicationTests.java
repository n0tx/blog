package com.riki.blog;

import com.riki.blog.domain.Post;
import com.riki.blog.domain.constant.PaginationSorting;
import com.riki.blog.dto.request.PostDtoRequest;
import com.riki.blog.dto.response.ListPostDtoResponse;
import com.riki.blog.dto.response.PostDtoResponse;
import com.riki.blog.repository.PostRepository;
import com.riki.blog.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogApplicationTests {

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostService postService;

	@Test
	void testCreate() {
		PostDtoRequest postDtoRequest = generateInsertData();
		PostDtoResponse postDtoResponse = postService.createPost(postDtoRequest);
		assertNotNull(postDtoResponse.getId());
	}

	@Test
	void testGetAllPosts() {
		postService.createPost(generateInsertData());
		ListPostDtoResponse postDtoResponses = postService.getAllPosts(
				Integer.parseInt(PaginationSorting.DEFAULT_PAGE_NUMBER),
				Integer.parseInt(PaginationSorting.DEFAULT_PAGE_SIZE),
				PaginationSorting.DEFAULT_SORT_BY,
				PaginationSorting.DEFAULT_SORT_DIRECTION);
		assertTrue(postDtoResponses.getTotalElements() > 0);
	}

	@Test
	void testGetSinglePost() {
		PostDtoRequest postDtoRequest = generateInsertData();
		PostDtoResponse postDtoResponse = postService.createPost(postDtoRequest);

		assertNotNull(postService.getPostById(postDtoResponse.getId()));
	}

	@Test
	void testUpdatePost() {
		// insert
		PostDtoRequest postDtoRequest = generateInsertData();
		PostDtoResponse postDtoResponse = postService.createPost(postDtoRequest);

		// update
		PostDtoRequest postDtoRequestUpdate = generateUpdateData(postDtoResponse, postDtoResponse.getId());
		PostDtoResponse updatePostDtoResponse = postService.updatePost(
				postDtoRequestUpdate, postDtoRequestUpdate.getId());

		assertEquals(postDtoRequestUpdate.getTitle(), updatePostDtoResponse.getTitle());
	}

	@Test
	public void testDeletePost() {
		PostDtoRequest postDtoRequest = generateInsertData();
		PostDtoResponse postDtoResponse = postService.createPost(postDtoRequest);
		Long postId = postDtoResponse.getId();
		postService.deletePostById(postId);
		Optional<Post> optionalPost = postRepository.findById(postId);
		assertFalse(optionalPost.isPresent());
	}

	private PostDtoRequest generateInsertData() {
		Long id = (long) postRepository.findAll().size();
		id++;

		PostDtoRequest postDtoRequest = new PostDtoRequest();
		postDtoRequest.setId(id);
		postDtoRequest.setTitle("Test Post Title " + id);
		postDtoRequest.setBody("Test Post Body " + id);
		postDtoRequest.setUserId(1L);
		return postDtoRequest;
	}

	private PostDtoRequest generateUpdateData(PostDtoResponse postDtoResponse, Long id) {
		PostDtoRequest postDtoRequestUpdate = new PostDtoRequest();
		postDtoRequestUpdate.setId(id);
		postDtoRequestUpdate.setTitle(postDtoResponse.getTitle() + " Update");
		postDtoRequestUpdate.setBody(postDtoResponse.getBody());
		postDtoRequestUpdate.setUserId(postDtoResponse.getUserId());
		return postDtoRequestUpdate;
	}
}
