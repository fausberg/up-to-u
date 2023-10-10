package com.example.testapplication.mapper;

import com.example.testapplication.entity.Phone;
import com.example.testapplication.entity.User;
import com.example.testapplication.entity.request.PhoneRequest;
import com.example.testapplication.entity.request.UserRequest;
import com.example.testapplication.entity.response.UserResponse;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {

  private final RoleMapper roleMapper;
  private final PhoneMapper phoneMapper;

  public UserResponse toUserResponse(User user) {
    return UserResponse.builder()
        .name(user.getName())
        .id(user.getId())
        .email(user.getEmail())
        .createdAt(user.getCreatedAt())
        .roles(roleMapper.toListRoleResponse(user.getRoles()))
        .phones(phoneMapper.toListPhoneResponse(user.getPhones()))
        .build();
  }

  public User toUser(UserRequest userRequest) {
    return User.builder()
        .name(userRequest.getName())
        .email(userRequest.getEmail())
        .createdAt(Timestamp.from(Instant.now()))
        .createdAt(Timestamp.from(Instant.now()))
        .build();
  }

}
