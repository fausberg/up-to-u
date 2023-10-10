package com.example.testapplication.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

  @Value("${spring.datasource.database.port}")
  private int containerPort;
  @Value("${spring.datasource.password}")
  private String password;
  @Value("${spring.datasource.username}")
  private String username;
  @Value("${spring.datasource.database.name}")
  private String databaseName;

  @Bean
  public DatabaseConfig databaseConfig() {
    return new DatabaseConfig(containerPort, username, password, databaseName);
  }

  @Data
  public class DatabaseConfig {
    private int containerPort;
    private String username;
    private String password;
    private String databaseName;

    public DatabaseConfig(int containerPort, String username, String password, String databaseName) {
      this.containerPort = containerPort;
      this.username = username;
      this.password = password;
      this.databaseName = databaseName;
    }


  }
}


