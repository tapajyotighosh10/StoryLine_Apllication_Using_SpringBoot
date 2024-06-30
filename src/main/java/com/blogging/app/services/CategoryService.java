package com.blogging.app.services;

import com.blogging.app.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    //create
     CategoryDto createCategory(CategoryDto categoryDto);
    //update
    CategoryDto updateCategory(CategoryDto categoryDto,int categoryId);
    //delete
    void deleteCategory(int categoryId);
    //get
    CategoryDto getCategory(int categoryId);
    //get all
    List<CategoryDto> getAllCategory();
}
