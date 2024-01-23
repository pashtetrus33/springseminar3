package com.example.springseminar3.services.interfaces;

import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.domain.User;

import java.util.List;


public interface RegistrationService {
    Registration registerUser(String name, int age, String email);

    List<Registration> getAll();

    List<User> sortUsers();

    double averageCalculate();

    List<User> sortUsersByAge(int age);
}