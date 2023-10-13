package com.example.testapplication.controller;

import com.example.testapplication.request.UserRequest;
import com.example.testapplication.response.UserResponse;
import com.example.testapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping()
  public void createUser(@RequestBody UserRequest userRequest) {
    userService.createUser(userRequest);
  }

  @GetMapping("/{email}")
  public UserResponse getUserById(@PathVariable String email) {
    return userService.getUserByEmail(email);
  }

}

