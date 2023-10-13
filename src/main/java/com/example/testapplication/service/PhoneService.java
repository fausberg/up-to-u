package com.example.testapplication.service;

import com.example.testapplication.entity.User;
import com.example.testapplication.request.PhoneRequest;
import com.example.testapplication.mapper.PhoneMapper;
import com.example.testapplication.repository.PhoneRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PhoneService {

  private final PhoneRepository phoneRepository;

  private final PhoneMapper phoneMapper;

  public void createPhone(List<PhoneRequest> phoneRequestList, User user) {
    for (PhoneRequest phoneRequest : phoneRequestList) {
      phoneRepository.save(phoneMapper.toPhone(phoneRequest, user));
    }
  }
}
