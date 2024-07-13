package com.blogging.app.services;

import com.blogging.app.entites.Post;
import com.blogging.app.payloads.PostDto;
import com.blogging.app.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto,int userId, int categoryId);

    //update
    PostDto updatePost(PostDto postDto, int postId);

    // delete
    void deletePost(int postId);

    //get all post
    PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir);

    // get single post

    PostDto getPostById(int postId);

        //get all post by category
    List<PostDto> getPostsByCategory(int categoryId);

    // get all posts by user
    List<PostDto> getPostsByUser(int userId);
    // search posts
    List<Post> searchPosts(String keyword);

}
