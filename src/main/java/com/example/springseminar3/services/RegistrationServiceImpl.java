package com.example.springseminar3.services;

import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.domain.User;
import com.example.springseminar3.repositories.RegistrationRepository;
import com.example.springseminar3.services.interfaces.DataProcessingService;
import com.example.springseminar3.services.interfaces.NotificationService;
import com.example.springseminar3.services.interfaces.RegistrationService;
import com.example.springseminar3.services.interfaces.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    private List<User> registeredUsers;
    private final NotificationService notificationService;
    private final UserService userService;
    private final DataProcessingService dataProcessingService;
    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(NotificationService notificationService, UserService userService, DataProcessingService dataProcessingService, RegistrationRepository registrationRepository) {
        this.notificationService = notificationService;
        this.userService = userService;
        this.dataProcessingService = dataProcessingService;
        this.registrationRepository = registrationRepository;
    }

    @PostConstruct
    public void init() {
        registeredUsers = new ArrayList<>();
        notificationService.notifyRegistrationServiceInit();
        registeredUsers = userService.getAll();
    }

    @Override
    public Registration registerUser(String name, int age, String email) {
        User user = userService.createUser(name, age, email);
        Registration registration = Registration.builder()
                .localDateTime(LocalDateTime.now())
                .user(user)
                .build();

        registrationRepository.save(registration);
        notificationService.notifyRegistrationCreation(registration);
        registeredUsers.add(user);
        notificationService.notifyUserRegistration(user);
        return registration;
    }


    @Override
    public List<Registration> getAll() {
        notificationService.notifyGetAllRegistrations();
        return registrationRepository.findAll();
    }

    @Override
    public List<User> sortUsers() {
        return dataProcessingService.sortUsersByAge(registeredUsers);
    }

    @Override
    public double averageCalculate() {
        return dataProcessingService.calculateAverageAge(registeredUsers);
    }

    @Override
    public List<User> sortUsersByAge(int age) {
        return dataProcessingService.filterUsersByAge(registeredUsers, age);
    }
}
