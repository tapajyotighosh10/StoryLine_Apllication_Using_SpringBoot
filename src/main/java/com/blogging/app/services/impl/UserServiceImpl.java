package com.blogging.app.services.impl;

import com.blogging.app.entites.Users;
import com.blogging.app.payloads.UserDto;
import com.blogging.app.repositories.UserRepo;
import com.blogging.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto user) {

        this.userRepo.save();
        return null;
    }

    @Override
    public UserDto updateUser(UserDto user, Integer userId) {
        return null;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }

    @Override
    public void deleteUser(Integer userId) {

    }
    private Users dtoToUser(UserDto userDto){
        Users user = new Users();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;
    }
}
