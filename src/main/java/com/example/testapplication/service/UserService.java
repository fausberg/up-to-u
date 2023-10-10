package com.example.testapplication.service;

import static com.example.testapplication.exception.massage.UserExceptionMassage.USER_IS_EXIST;
import static com.example.testapplication.exception.massage.UserExceptionMassage.USER_IS_PRESENT;
import static com.example.testapplication.exception.massage.UserExceptionMassage.USER_NOT_FOUND_EXCEPTION;
import static java.lang.String.format;

import com.example.testapplication.entity.User;
import com.example.testapplication.entity.request.RoleRequest;
import com.example.testapplication.entity.request.UserRequest;
import com.example.testapplication.entity.response.UserResponse;
import com.example.testapplication.mapper.UserMapper;
import com.example.testapplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
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
    IfUserByEmailIsPresentThrowException(userRequest.getEmail());
    User user = userMapper.toUser(userRequest);
    userRepository.save(user);
    roleService.createRole(userRequest.getRoles());
    phoneService.createPhone(userRequest.getPhones(), findUserByEmailOrThrowException(userRequest.getEmail()));
    createUserRole(userRequest.getRoles(), user.getId());
  }

  public UserResponse getUserById(Long id) {
    return userMapper.toUserResponse(findUserByIdOrThrowException(id));
  }

  public void createUserRole(List<RoleRequest> roleRequests, Long id) {
    for (RoleRequest roleRequest : roleRequests) {
      userRepository.insertIntoUserRoleTable(id ,roleService.findByName(roleRequest.getName()).getId());
    }
  }

  public User findUserByIdOrThrowException(Long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException(format(USER_NOT_FOUND_EXCEPTION, id)));
  }

  public void IfUserByEmailIsPresentThrowException(String email) {
    if (userRepository.findByEmail(email).isPresent()) {
        throw new EntityNotFoundException(format(USER_IS_PRESENT, email));
    }
  }

  public User findUserByEmailOrThrowException(String email) {
    return userRepository.findByEmail(email).orElseThrow(
        () -> new EntityNotFoundException(format(USER_IS_EXIST, email)));
  }
}
