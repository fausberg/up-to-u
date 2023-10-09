package com.example.testapplication.controller;

import static org.springframework.http.HttpStatus.OK;

import com.example.testapplication.entity.request.UserRequest;
import com.example.testapplication.entity.response.UserResponse;
import com.example.testapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @PostMapping()
  @ResponseStatus(OK)
  public void createUser(@RequestBody UserRequest userRequest) {
    userService.createUser(userRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(OK)
  public UserResponse getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

}

