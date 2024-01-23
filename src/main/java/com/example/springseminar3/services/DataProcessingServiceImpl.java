package com.example.springseminar3.services;

import com.example.springseminar3.domain.User;
import com.example.springseminar3.services.interfaces.DataProcessingService;
import com.example.springseminar3.services.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DataProcessingServiceImpl implements DataProcessingService {
    private final NotificationService notificationService;

    @Autowired
    public DataProcessingServiceImpl(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public String getGreeting() {
        return "!!Hello, world!";
    }

    @Override
    public List<User> sortUsersByAge(List<User> users) {
        List<User> userListSorted = users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .toList();
        notificationService.notifySort(userListSorted);
        return userListSorted;
    }

    @Override
    public List<User> filterUsersByAge(List<User> users, int age) {
        notificationService.notifySortByAge(age);
        return users.stream()
                .filter(user -> user.getAge() > age)
                .toList();
    }

    @Override
    public double calculateAverageAge(List<User> users) {
        double average = users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
        notificationService.notifyAverageCalculate(average);
        return average;
    }
}
