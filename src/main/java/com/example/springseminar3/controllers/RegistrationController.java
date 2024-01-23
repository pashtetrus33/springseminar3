package com.example.springseminar3.controllers;

import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.domain.User;
import com.example.springseminar3.services.interfaces.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //Для отображения результатов в браузере использую вместо GET, так как с POST - 405 method not allowed (Request method 'GET' is not supported)
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<Registration> registerUser(@RequestParam String name, @RequestParam int age, @RequestParam String email) {
        return new ResponseEntity<>(registrationService.registerUser(name, age, email), HttpStatus.CREATED);
    }

    //Для отображения результатов в браузере использую вместо GET, так как с POST - 405 method not allowed (Request method 'GET' is not supported)
    @RequestMapping(path = "sort", method = RequestMethod.GET)
    ResponseEntity<List<User>> sortRegisteredUsers() {
        return new ResponseEntity<>(registrationService.sortUsers(), HttpStatus.OK);
    }

    //Для отображения результатов в браузере использую вместо GET, так как с POST - 405 method not allowed (Request method 'GET' is not supported)
    @RequestMapping(path = "average", method = RequestMethod.GET)
    ResponseEntity<Double> averageCalculate() {
        return new ResponseEntity<>(registrationService.averageCalculate(), HttpStatus.OK);
    }

    //Для отображения результатов в браузере использую вместо GET, так как с POST - 405 method not allowed (Request method 'GET' is not supported)
    @RequestMapping(path = "sort/{age}", method = RequestMethod.GET)
    ResponseEntity<List<User>> averageCalculate(@PathVariable int age) {
        return new ResponseEntity<>(registrationService.sortUsersByAge(age), HttpStatus.OK);
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Registration>> getAllRegistrations() {
        return new ResponseEntity<>(registrationService.getAll(), HttpStatus.OK);
    }
}