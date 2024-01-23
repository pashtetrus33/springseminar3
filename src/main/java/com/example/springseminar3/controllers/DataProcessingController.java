package com.example.springseminar3.controllers;

import com.example.springseminar3.domain.User;
import com.example.springseminar3.services.interfaces.DataProcessingService;
import com.example.springseminar3.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class DataProcessingController {
    private final DataProcessingService dataProcessingService;
    private final UserService userService;

    @Autowired
    public DataProcessingController(DataProcessingService helloService, UserService userService) {
        this.dataProcessingService = helloService;
        this.userService = userService;
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ResponseEntity<String> hello() {
        String response = this.dataProcessingService.getGreeting();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.POST)
    public ResponseEntity<List<User>> sortUsers(@RequestBody List<User> users) {
        return new ResponseEntity<>(dataProcessingService.sortUsersByAge(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/dbsort", method = RequestMethod.GET)
    public ResponseEntity<List<User>> sortUsersFromDb() {
        return new ResponseEntity<>(dataProcessingService.sortUsersByAge(userService.getAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/filter/{age}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> filterUsersByAge(@PathVariable("age") Integer age, @RequestBody List<User> users) {
        return new ResponseEntity<>(dataProcessingService.filterUsersByAge(users, age), HttpStatus.OK);
    }

    @RequestMapping(value = "/dbfilter/{age}", method = RequestMethod.GET)
    public ResponseEntity<List<User>> filterUsersByAgeFromDb(@PathVariable("age") Integer age) {
        return new ResponseEntity<>(dataProcessingService.filterUsersByAge(userService.getAll(), age), HttpStatus.OK);
    }

    @RequestMapping(value = "/average", method = RequestMethod.POST)
    public ResponseEntity<Double> average(@RequestBody List<User> users) {
        return new ResponseEntity<>(dataProcessingService.calculateAverageAge(users), HttpStatus.OK);
    }

    @RequestMapping(value = "/dbaverage", method = RequestMethod.GET)
    public ResponseEntity<Double> average() {
        return new ResponseEntity<>(dataProcessingService.calculateAverageAge(userService.getAll()), HttpStatus.OK);
    }
}
