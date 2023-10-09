package com.example.testapplication.service;

import static com.example.testapplication.exception.massage.UserExceptionMassage.USER_NOT_FOUND_EXCEPTION;
import static java.lang.String.format;

import com.example.testapplication.entity.User;
import com.example.testapplication.entity.request.UserRequest;
import com.example.testapplication.entity.response.UserResponse;
import com.example.testapplication.mapper.UserMapper;
import com.example.testapplication.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public void createUser(UserRequest userRequest) {
    User user = userMapper.toUser(userRequest);
  }

  public UserResponse getUserById(Long id) {
    return userMapper.toUserResponse(findUserOrThrowException(id));
  }

  public User findUserOrThrowException(Long id) {
    return userRepository.findById(id).orElseThrow(
        () -> new EntityNotFoundException(format(USER_NOT_FOUND_EXCEPTION, id)));
  }
}
