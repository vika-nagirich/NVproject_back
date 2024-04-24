package com.nagirich.datasense.services;

import java.util.Optional;
import com.nagirich.datasense.config.MyUserDetails;
import com.nagirich.datasense.entity.UserEntity;

import com.nagirich.datasense.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

  @Autowired // обновить память по внедрениям зависимостям
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    return user.map(MyUserDetails::new) // Применяет функцию MyUserDetails::new к объекту user, если он присутствует и создается обьект myUserDetails
        .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

  }
}
