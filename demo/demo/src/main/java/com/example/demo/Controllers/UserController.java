package com.example.demo.Controllers;

import com.example.demo.Dtos.ResponseStatus;
import com.example.demo.Dtos.SignUpRequestDto;
import com.example.demo.Dtos.SignUpResponseDto;
import com.example.demo.Services.UserService;
import com.example.demo.models.User;

public class UserController {
    private UserService userService;

    public SignUpResponseDto signUp(SignUpRequestDto request){
        User user = userService.signUp(request.getEmail(), request.getPassword());
        return new SignUpResponseDto(user.getId(), ResponseStatus.SUCCESS);
    }
    public boolean login(String email, String password){
        return userService.login(email, password);
    }
}
