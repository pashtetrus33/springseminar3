package com.example.springseminar3.services.interfaces;

import com.example.springseminar3.domain.User;

import java.util.List;

public interface DataProcessingService {
    String getGreeting();

    List<User> sortUsersByAge(List<User> users);

    List<User> filterUsersByAge(List<User> users, int age);


    double calculateAverageAge(List<User> users);
}
