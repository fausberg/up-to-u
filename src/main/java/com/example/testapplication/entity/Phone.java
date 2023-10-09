package com.example.testapplication.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phones")
public class Phone {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;

  @Column(name = "phone")
  private String phone;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @ManyToOne()
  @JoinColumn(name = "user_id")
  private User user;
}
