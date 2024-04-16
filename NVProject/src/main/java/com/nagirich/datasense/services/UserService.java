package com.nagirich.datasense.services;

import java.util.Optional;
import com.nagirich.datasense.converter.UserConverter;
import com.nagirich.datasense.dto.UserDto;
import com.nagirich.datasense.entity.UserEntity;
import com.nagirich.datasense.exception.HttpException;
import com.nagirich.datasense.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public void addUser(UserDto userDto) {
    userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
    userRepository.save(UserConverter.toUserEntity(userDto));

  }

  public boolean isUserExist(UserDto userDto) {
    Optional<UserEntity> optional = userRepository.findByUsername(userDto.getUsername());
    if(optional.isPresent())
      return true;
    else
      return false;
  }
}
