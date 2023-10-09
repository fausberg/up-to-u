package com.example.testapplication.entity.request;

import com.example.testapplication.entity.Phone;
import com.example.testapplication.entity.Role;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
  private String name;
  private String email;
  private List<Role> roles;
  private List<Phone> phones;
}
