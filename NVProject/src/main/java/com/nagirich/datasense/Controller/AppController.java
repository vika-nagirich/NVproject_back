package com.nagirich.datasense.Controller;

import java.util.List;
import com.nagirich.datasense.models.Application;
import com.nagirich.datasense.models.User;
import com.nagirich.datasense.services.AppService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/apps")
public class AppController {

  private final AppService appService;

  @GetMapping("/welcome")
  public String welcome(){
    return "Welcome to the unprotected page";
  }

  @GetMapping("/all-app")
  @PreAuthorize("hasAuthority('ROLE_USER')")
  public List<Application> allApplications(){
    return appService.allApplications();
  }

  @GetMapping("/{id}")
  public Application applicationsByID(@PathVariable int id){
    return appService.applicationByID(id);
  }

}
