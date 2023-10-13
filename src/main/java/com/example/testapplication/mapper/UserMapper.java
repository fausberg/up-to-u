package com.example.testapplication.mapper;

import com.example.testapplication.entity.Role;
import com.example.testapplication.entity.User;
import com.example.testapplication.request.UserRequest;
import com.example.testapplication.response.UserResponse;
import java.time.LocalDateTime;
import java.util.List;
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

  public User toUser(UserRequest userRequest, List<Role> roles) {
    return User.builder()
        .name(userRequest.getName())
        .email(userRequest.getEmail())
        .roles(roles)
        .createdAt(LocalDateTime.now())
        .build();
  }

}
