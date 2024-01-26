package com.example.springseminar3.services;

import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.domain.User;
import com.example.springseminar3.repositories.RegistrationRepository;
import com.example.springseminar3.repositories.UserRepository;
import com.example.springseminar3.services.interfaces.NotificationService;
import com.example.springseminar3.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final NotificationService notificationService;
    private final RegistrationRepository registrationRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(NotificationService notificationService, RegistrationRepository registrationRepository, UserRepository userRepository) {
        this.notificationService = notificationService;
        this.registrationRepository = registrationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String name, int age, String email) {
        User user = User.builder()
                .name(name)
                .age(age)
                .email(email)
                .build();

        userRepository.save(user);
        notificationService.notifyUserCreation(user);

        return user;
    }

    @Override
    public List<User> getAll() {
        notificationService.notifyGetAllUsers();
        return userRepository.findAll();
    }

    @Override
    public String deleteUser(Long id) {
        String response = "";
        Optional<Registration> registration = registrationRepository.findAll().stream().filter(it -> it.getUser().getId().equals(id)).findFirst();
        if (registration.isPresent()) {
            response += "Registration is deleted successfully.";
            registrationRepository.delete(registration.get());
            notificationService.notifyRegistrationDelete(id);
        }

        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            response += " User is deleted successfully";
        } else {
            response = "User is not found!";
        }

        notificationService.notifyUserDelete(id);
        return response;
    }
}
