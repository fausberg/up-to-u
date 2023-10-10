package com.example.testapplication.service;

import static com.example.testapplication.exception.massage.RoleExceptionMassage.ROLE_WITH_NAME_IS_EXIST;
import static java.lang.String.format;

import com.example.testapplication.entity.Role;
import com.example.testapplication.entity.request.RoleRequest;
import com.example.testapplication.mapper.RoleMapper;
import com.example.testapplication.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {

  private final RoleRepository roleRepository;
  private final RoleMapper roleMapper;

  public void createRole(List<RoleRequest> roleRequests) {
    for (RoleRequest roleRequest : roleRequests) {
      roleRepository.save(roleMapper.toRole(roleRequest));
    }
  }

  public Role findByName(String name) {
    return roleRepository.findByName(name).orElseThrow(
        () -> new EntityNotFoundException(format(ROLE_WITH_NAME_IS_EXIST, name)));
  }
}
