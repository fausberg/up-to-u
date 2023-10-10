package com.example.testapplication.mapper;

import com.example.testapplication.entity.Phone;
import com.example.testapplication.entity.User;
import com.example.testapplication.entity.request.PhoneRequest;
import com.example.testapplication.entity.response.PhoneResponse;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {

  public Phone toPhone(PhoneRequest request, User user) {
    return Phone.builder()
        .phone(request.getPhone())
        .user(user)
        .createdAt(Timestamp.from(Instant.now()))
        .build();
  }

  public List<PhoneResponse> toListPhoneResponse(List<Phone> phones) {
    List<PhoneResponse> responses = new ArrayList<>();
    for (Phone phone : phones) {
      responses.add(PhoneResponse.builder()
          .phone(phone.getPhone()).build());
    }
    return responses;
  }
}
