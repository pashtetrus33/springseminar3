package com.example.springseminar3.controllers.ui;

import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.domain.User;
import com.example.springseminar3.services.interfaces.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/v1/ui/users")
public class UserUiController {
    private final UserService userService;

    @Autowired
    public UserUiController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users/allUsers";
    }

    @RequestMapping(path = "save", method = RequestMethod.GET)
    public String saveUser(Model model) {
        model.addAttribute("user", new User());
        return "users/saveUser";
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("users", userService.getAll());
            return "users/saveUser";
        }
        userService.createUser(user.getName(), user.getAge(), user.getEmail());
        model.addAttribute("users", userService.getAll());
        return "users/allUsers";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUserById(@PathVariable Long id, Model model) {
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAll());
        return "users/allUsers";
    }
}
