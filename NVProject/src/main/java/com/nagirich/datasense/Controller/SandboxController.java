package com.nagirich.datasense.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sandbox")
public class SandboxController {
  @GetMapping
  public String test(
      @RequestParam(name="name", required = false, defaultValue = "World") String name){
    return "Hello " + name;
  }

}
