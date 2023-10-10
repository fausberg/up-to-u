package com.example.testapplication.mapper;

import com.example.testapplication.entity.Role;
import com.example.testapplication.entity.request.RoleRequest;
import com.example.testapplication.entity.response.RoleResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

  public Role toRole(RoleRequest request) {
    return Role.builder()
        .name(request.getName())
        .createdAt(Timestamp.from(Instant.now()))
        .build();
  }

  public List<RoleResponse> toListRoleResponse(List<Role> roles) {
    List<RoleResponse> roleResponses = new ArrayList<>();
    for (Role role : roles) {
      roleResponses.add(RoleResponse.builder()
          .name(role.getName()).build());
    }
    return roleResponses;
  }
}
