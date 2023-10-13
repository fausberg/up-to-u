package com.example.testapplication.service;

import static com.example.testapplication.exception.massage.UserExceptionMassage.USER_IS_EXIST;
import static java.lang.String.format;

import com.example.testapplication.entity.Role;
import com.example.testapplication.entity.User;
import com.example.testapplication.request.RoleRequest;
import com.example.testapplication.request.UserRequest;
import com.example.testapplication.response.UserResponse;
import com.example.testapplication.mapper.UserMapper;
import com.example.testapplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PhoneService phoneService;
  private final RoleService roleService;

  public void createUser(UserRequest userRequest) {
    List<Role> roles = new ArrayList();
    roleService.createRole(userRequest.getRoles());
    for (RoleRequest roleRequest : userRequest.getRoles()) {
      roles.add(roleService.findByName(roleRequest.getName()));
    }
    User user = userMapper.toUser(userRequest, roles);
    userRepository.save(user);
    phoneService.createPhone(userRequest.getPhones(), findUserByEmailOrThrowException(userRequest.getEmail()));
  }

  public UserResponse getUserByEmail(String email) {
    return userMapper.toUserResponse(findUserByEmailOrThrowException(email));
  }

  private User findUserByEmailOrThrowException(String email) {
    return userRepository.findByEmail(email).orElseThrow(
        () -> new EntityNotFoundException(format(USER_IS_EXIST, email)));
  }
}
