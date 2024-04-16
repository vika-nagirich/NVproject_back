package com.nagirich.datasense.converter;

import com.nagirich.datasense.dto.UserDto;
import com.nagirich.datasense.entity.UserEntity;
import com.nagirich.datasense.models.User;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;


public class UserConverter {

  public static UserEntity toUserEntity(UserDto userDto){
    return UserEntity.builder()
        .username(userDto.getUsername())
        .password(userDto.getPassword())
        .roles("ROLE_USER")
        .build();
  }
}
