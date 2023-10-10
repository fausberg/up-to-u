package com.example.testapplication.entity.response;

import com.example.testapplication.entity.Phone;
import com.example.testapplication.entity.Role;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Timestamp createdAt;
    private List<RoleResponse> roles;
    private List<PhoneResponse> phones;
}
