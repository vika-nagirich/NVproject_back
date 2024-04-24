package com.nagirich.datasense.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import com.nagirich.datasense.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

  private UserEntity user;

  public MyUserDetails(UserEntity user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // возвращает коллекцию ролей пользователя
    // разделяет строку с ролями пользователя на отдельные роли
    // затем преобразует массив строк в поток строк
    return Arrays.stream(user.getRoles().split(", "))
        // каждую строку преобразуем в объект SimpleGrantedAuthority,
        // который представляет роль в виде реализации интерфейса GrantedAuthority.
        .map(SimpleGrantedAuthority::new)
        // Собирает полученные объекты GrantedAuthority в список коллекции.
        .collect(Collectors.toList());
  }


  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    // Возвращает true, что означает, что учетная запись пользователя не истекла.
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // Возвращает true, что означает, что учетная запись пользователя не заблокирована.
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // Возвращает true, что означает, что учетные данные пользователя не истекли.
    return true;
  }

  @Override
  public boolean isEnabled() {
    // Возвращает true, что означает, что учетная запись пользователя включена и активна.
    return true;
  }

}
