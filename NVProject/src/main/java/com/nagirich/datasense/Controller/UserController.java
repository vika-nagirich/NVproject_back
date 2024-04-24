package com.nagirich.datasense.Controller;

import com.nagirich.datasense.dto.UserDto;
import com.nagirich.datasense.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public Resource index(){
    return new ClassPathResource("static/main.html");
  }

  @GetMapping("/registration")
  public Resource registrationPage(){

    return new ClassPathResource("static/registration.html"); // Путь к файлу HTML
  }
  @GetMapping("/about")
  public Resource portfolioPage(){
    return new ClassPathResource("static/about/cv.html");
  }


  @PostMapping("/registration")
  public ResponseEntity addNewUser(@RequestBody UserDto userDto){
    if(userService.isUserExist(userDto))
      return ResponseEntity.ok().body("{\"success\": false}");
    else {
      userService.addUser(userDto);
      return ResponseEntity.ok().body("{\"success\": true}");
    }
  }
}
