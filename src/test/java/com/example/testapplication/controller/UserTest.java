package com.example.testapplication.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.testapplication.config.TestConfig;
import com.example.testapplication.entity.User;
import com.example.testapplication.entity.request.PhoneRequest;
import com.example.testapplication.entity.request.RoleRequest;
import com.example.testapplication.entity.request.UserRequest;
import com.example.testapplication.repository.UserRepository;
import com.example.testapplication.service.PhoneService;
import com.example.testapplication.service.RoleService;
import com.example.testapplication.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = TestConfig.class)
@AutoConfigureMockMvc(printOnlyOnFailure = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestPropertySource("/application.properties")
public class UserTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserService userService;

  @Autowired
  private PhoneService phoneService;

  @Autowired
  private RoleService roleService;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  User user;

  UserRequest userRequest;

  @BeforeAll
  void init() {
    List<RoleRequest> roleRequests = new ArrayList<>();
    roleRequests.add(new RoleRequest("USER"));
    List<PhoneRequest> phoneRequestList = new ArrayList<>();
    phoneRequestList.add(new PhoneRequest("123"));
    userRequest = UserRequest.builder()
        .email("ivanfadeev2003@mail.ru")
        .name("Ivan")
        .phones(phoneRequestList)
        .roles(roleRequests)
        .build();
    user = User.builder()
        .id(100L)
        .createdAt(Timestamp.from(Instant.now()))
        .email("qwe")
        .name("name")
        .build();
    userRepository.save(user);
  }

  @Test
  public void shouldGetUserWithId100() throws Exception {
    mockMvc.perform(get("/user/{id}", 1)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("{\"id\":1,\"name\":\"name\",\"email\":\"qwe\",\"createdAt\":\"2023-10-10T12:54:57.384+00:00\",\"roles\":[],\"phones\":[]}"));
  }

  @Test
  public void testCreateUser() throws Exception {
    String jsonRequest = objectMapper.writeValueAsString(userRequest);
    mockMvc.perform(post("/user")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isOk());
  }



}
