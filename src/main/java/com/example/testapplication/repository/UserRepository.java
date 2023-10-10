package com.example.testapplication.repository;

import com.example.testapplication.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  public Optional<User> findByEmail(String email);

  @Transactional
  @Modifying(clearAutomatically = true)
  @Query(nativeQuery = true, value = "insert into user_role(user_id, role_id) values (:userId, :roleId)")
  public void insertIntoUserRoleTable(Long userId, Long roleId);
}
