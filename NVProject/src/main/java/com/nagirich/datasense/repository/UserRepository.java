package com.nagirich.datasense.repository;

import java.util.Optional;
import com.nagirich.datasense.entity.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUsername(String username);
}
