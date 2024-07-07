package com.blogging.app.controllers;


import com.blogging.app.entites.Category;
import com.blogging.app.payloads.ApiResponse;
import com.blogging.app.payloads.CategoryDto;
import com.blogging.app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // create
    @PostMapping("/adding_category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }
    //update
    @PutMapping("/updating_category/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
        CategoryDto updateCategory = this.categoryService.updateCategory(categoryDto,catId);
        return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/deleting_category/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
       this.categoryService.deleteCategory(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully !!",true), HttpStatus.OK);
    }

    //get
    @GetMapping("/get_category/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto getcat = this.categoryService.getCategory(catId);
        return new ResponseEntity<CategoryDto>(getcat, HttpStatus.OK);
    }
    //get all

    @GetMapping("/get_all_category")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> getcat = this.categoryService.getAllCategory();
       return ResponseEntity.ok(getcat);
    }

}
