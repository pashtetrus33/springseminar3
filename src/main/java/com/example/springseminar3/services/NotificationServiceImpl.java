package com.example.springseminar3.services;

import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.domain.User;
import com.example.springseminar3.services.interfaces.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyUserCreation(User user) {

        log.info("A new user has been created: " + user.getName());
    }

    @Override
    public void notifyGetAllUsers() {
        log.info("Get all users request");
    }

    @Override
    public void notifyAverageCalculate(double average) {
        log.info("Average age calculation request result is " + average);
    }

    @Override
    public void notifySort(List<User> userListSorted) {
        log.info("Sort age request result: " + userListSorted);
    }

    @Override
    public void notifySortByAge(int age) {
        log.info("Sort by age: " + age + " request");
    }

    @Override
    public void notifyUserRegistration(User user) {
        log.info("User " + user.getName() + " has been registered");
    }

    @Override
    public void notifyRegistrationServiceInit() {
        log.info("Registration service init method invoked");
    }

    @Override
    public void notifyRegistrationCreation(Registration registration) {
        log.info("A new registration has been created: " + registration.getId());
    }

    @Override
    public void notifyGetAllRegistrations() {
        log.info("Get all registrations request");
    }

    @Override
    public void notifyRegistrationDelete(Long id) {
        log.info("Registration deletion by user id " + id);
    }

    @Override
    public void notifyUserDelete(Long id) {
        log.info("User deletion by id " + id);
    }
}
