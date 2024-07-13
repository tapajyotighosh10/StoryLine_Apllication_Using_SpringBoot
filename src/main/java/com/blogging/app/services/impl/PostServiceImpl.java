package com.blogging.app.services.impl;

import com.blogging.app.entites.Category;
import com.blogging.app.entites.Post;
import com.blogging.app.entites.Users;
import com.blogging.app.exceptions.ResourceNotFoundException;
import com.blogging.app.payloads.PostDto;
import com.blogging.app.payloads.PostResponse;
import com.blogging.app.repositories.CategoryRepo;
import com.blogging.app.repositories.PostRepo;
import com.blogging.app.repositories.UserRepo;
import com.blogging.app.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,int userId,int categoryId) {
        Users user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User ","User id",userId));
        Category category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ","Category id",categoryId));

        Post post =  this.modelMapper.map(postDto,Post.class);
      post.setImageName("default.png");
      post.setAddedDate(new Date());
      post.setCategory(category);
      post.setUser(user);

      Post newPost = this.postRepo.save(post);
      return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

        // Update post fields with values from postDto
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(int pageNumber, int pageSize,String sortBy,String sortDir) {

        Sort sort = null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort = sort.by(sortBy).ascending();
        } else {
            sort = sort.by(sortBy).descending();
        }
        Pageable p = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost =this.postRepo.findAll(p);
       List<Post> allpost= this.postRepo.findAll();
      List<PostDto> pd = allpost.stream().map((post -> this.modelMapper.map(post,PostDto.class))).collect(Collectors.toList());
    PostResponse postResponse = new PostResponse();
        postResponse.setContent(pd);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setTotalElement(pagePost.getNumberOfElements());
        return postResponse;
    }

    @Override
    public PostDto getPostById(int postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        return this.modelMapper.map(post,PostDto.class);
    }
    @Override
    public List<PostDto> getPostsByCategory(int categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category","category id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(cat);
       List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(int userId) {
        Users us = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user id",userId));
        List<Post> posts = this.postRepo.findByUser(us);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
