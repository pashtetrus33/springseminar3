package com.example.springseminar3.services.interfaces;

import com.example.springseminar3.domain.User;

import java.util.List;

public interface UserService {
    User createUser(String name, int age, String email);

    List<User> getAll();

    String deleteUser(Long id);
}
