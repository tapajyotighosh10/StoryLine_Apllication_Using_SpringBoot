package com.blogging.app.services.impl;

import com.blogging.app.entites.Post;
import com.blogging.app.exceptions.ResourceNotFoundException;
import com.blogging.app.payloads.PostDto;
import com.blogging.app.repositories.PostRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepo postRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PostServiceImpl postServiceImpl;

    private Post post;
    private Post updatedPost;
    private PostDto postDto;
    private PostDto updatedPostDto;

    @BeforeEach
    void setUp() {
        post = new Post();

        post.setTitle("Old Title");
        post.setContent("Old Content");
        post.setImageName("old_image.jpg");

        updatedPost = new Post();
        updatedPost.setTitle("New Title");
        updatedPost.setContent("New Content");
        updatedPost.setImageName("new_image.jpg");

        postDto = new PostDto();
        postDto.setTitle("New Title");
        postDto.setContent("New Content");
        postDto.setImageName("new_image.jpg");

        updatedPostDto = new PostDto();
        updatedPostDto.setTitle("New Title");
        updatedPostDto.setContent("New Content");
        updatedPostDto.setImageName("new_image.jpg");
    }

    @Test
    void updatePost() {
        // Arrange
        when(postRepo.findById(1)).thenReturn(Optional.of(post));
        when(postRepo.save(any(Post.class))).thenReturn(updatedPost);
        when(modelMapper.map(updatedPost, PostDto.class)).thenReturn(updatedPostDto);

        // Act
        PostDto result = postServiceImpl.updatePost(postDto, 1);

        // Assert
        assertEquals(updatedPostDto, result);
    }

    @Test
    void updatePost_ResourceNotFound() {
        // Arrange
        when(postRepo.findById(1)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            postServiceImpl.updatePost(postDto, 1);
        });
    }
}
