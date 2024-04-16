package com.nagirich.datasense.config;

import com.nagirich.datasense.services.MyUserDetailsService;
import lombok.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Bean
  public UserDetailsService userDetailsService(){ //это интерфейс позволяющий предоставлять сведения пользователя в контексте безопасности
//    UserDetails admin = User.builder()
//        .username("admin")
//        .password(encoder.encode("admin"))
//        .roles("ADMIN") // префикс "ROLE_" подставляеться автоматически
//        .build();
//
//    UserDetails user = User.builder()
//        .username("user")
//        .password(encoder.encode("user"))
//        .roles("USER")
//        .build();
//
//    return new InMemoryUserDetailsManager(admin, user);
    return new MyUserDetailsService();
  }
  @Bean
  public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService());
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    // Создание объекта SecurityFilterChain, который будет обрабатывать запросы безопасности

    return http.csrf(AbstractHttpConfigurer::disable) // Отключение защиты CSRF (Cross-Site Request Forgery)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("api/v1/apps/welcome",
                "api/v1/apps/new-user",
                "registration",
                "/").permitAll() // Разрешение доступа к конкретному URL без аутентификации
            .requestMatchers("api/v1/apps/**").authenticated()) // Запросы по определенному шаблону должны быть аутентифицированы
        .formLogin(AbstractAuthenticationFilterConfigurer::permitAll) // Разрешение доступа к странице входа (форме входа) без аутентификации
        .build(); // Создание и возврат объекта SecurityFilterChain
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
