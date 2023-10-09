package com.example.testapplication.mapper;

import com.example.testapplication.entity.User;
import com.example.testapplication.entity.request.UserRequest;
import com.example.testapplication.entity.response.UserResponse;
import java.sql.Timestamp;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .name(user.getName())
        .id(user.getId())
        .email(user.getEmail())
        .createdAt(user.getCreatedAt())
        .roles(user.getRoles())
        .phones(user.getPhones())
        .build();
  }

  public User toUser(UserRequest userRequest) {
    return User.builder()
        .name(userRequest.getName())
        .email(userRequest.getEmail())
        .createdAt(Timestamp.from(Instant.now()))
        .phones(userRequest.getPhones())
        .roles(userRequest.getRoles())
        .build();
  }

}
