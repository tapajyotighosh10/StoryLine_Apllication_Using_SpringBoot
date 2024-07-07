package com.blogging.app.controllers;


import com.blogging.app.payloads.ApiResponse;
import com.blogging.app.payloads.UserDto;
import com.blogging.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // post users
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    //update user
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") int id ){
    UserDto updatedUser = this.userService.updateUser(userDto,id);
    return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int id){
        this.userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
    }

    // get users
    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @GetMapping("/getuser/{id}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable("id") int id){
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
}
