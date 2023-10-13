package com.example.testapplication.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testapplication.TestApplication;
import com.example.testapplication.entity.User;
import com.example.testapplication.mapper.UserMapper;
import com.example.testapplication.repository.RoleRepository;
import com.example.testapplication.repository.UserRepository;
import com.example.testapplication.request.PhoneRequest;
import com.example.testapplication.request.RoleRequest;
import com.example.testapplication.request.UserRequest;
import com.example.testapplication.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TestApplication.class)
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private ObjectMapper objectMapper;

  UserResponse userResponse;

  User user;

  UserRequest userRequest;

  @BeforeAll
  void init() {
    List<RoleRequest> roleRequests = new ArrayList<>();
    roleRequests.add(new RoleRequest("USER"));
    List<PhoneRequest> phoneRequestList = new ArrayList<>();
    phoneRequestList.add(new PhoneRequest("123"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.S");
    userRequest = UserRequest.builder()
        .email("ivanfadeev2003@mail.ru")
        .name("Ivan")
        .phones(phoneRequestList)
        .roles(roleRequests)
        .build();
    user = User.builder()
        .createdAt(LocalDateTime.parse("2023-10-13T16:01:01.9", formatter))
        .email("qwe")
        .name("name")
        .phones(Collections.emptyList())
        .roles(Collections.emptyList())
        .build();
    userRepository.save(user);
  }

  @AfterAll
  void delete() {
    userRepository.delete(user);
    userRepository.delete(userRepository.findByEmail(userRequest.getEmail()).get());
    for (RoleRequest role : userRequest.getRoles()) {
      roleRepository.delete(roleRepository.findByName(role.getName()).get());
    }
  }

  @Test
  public void testCreateUser() throws Exception {
    mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userRequest)))
        .andExpect(status().isOk());
  }

  @Test
  public void shouldGetUserWithId100() throws Exception {
    mockMvc.perform(get("/users/{email}", user.getEmail())
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(user)));
  }

}