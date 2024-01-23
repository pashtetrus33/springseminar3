package com.example.springseminar3.services.interfaces;

import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.domain.User;

import java.util.List;

public interface NotificationService {
    void notifyUserCreation(User user);

    void notifyGetAllUsers();

    void notifyAverageCalculate(double average);

    void notifySort(List<User> userListSorted);

    void notifySortByAge(int age);

    void notifyUserRegistration(User user);

    void notifyRegistrationServiceInit();

    void notifyRegistrationCreation(Registration registration);

    void notifyGetAllRegistrations();

    void notifyRegistrationDelete(Long id);

    void notifyUserDelete(Long id);
}
