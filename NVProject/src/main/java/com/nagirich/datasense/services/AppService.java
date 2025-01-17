package com.nagirich.datasense.services;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import com.github.javafaker.Faker;
import com.nagirich.datasense.entity.UserEntity;
import com.nagirich.datasense.models.Application;
import com.nagirich.datasense.models.User;
import com.nagirich.datasense.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppService {
  private List<Application> applications;
  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

@PostConstruct
  public void loadAppInDB(){
    Faker faker = new Faker();
    applications = IntStream.rangeClosed(1, 100)
        .mapToObj(i -> Application.builder()
          .id(i)
          .name(faker.app().name())
          .author(faker.app().author())
          .version(faker.app().version())
          .build())
        .collect(Collectors.toList());
  }

  public List<Application> allApplications(){
    return applications;
  }

  public Application applicationByID(int id){
    return applications.stream()
        .filter(app -> app.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public void addUser(UserEntity user){
  user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

}
