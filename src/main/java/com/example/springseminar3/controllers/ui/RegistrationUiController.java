package com.example.springseminar3.controllers.ui;


import com.example.springseminar3.domain.Registration;
import com.example.springseminar3.services.interfaces.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/api/v1/ui/registrations")
public class RegistrationUiController {
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationUiController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String getAllRegistrations(Model model) {
        model.addAttribute("registrations", registrationService.getAll());
        return "registrations/allRegistrations";
    }

    @RequestMapping(path = "save", method = RequestMethod.GET)
    public String saveRegistration(Model model) {
        model.addAttribute("registration", new Registration());
        return "registrations/saveRegistration";
    }

    @RequestMapping(path = "save", method = RequestMethod.POST)
    public String save(@Valid Registration registration, BindingResult bindingResult, Model model) {
        if (registration.getUser().getName().length() < 2) {
            bindingResult.addError(new FieldError("registration", "user.name", "Имя должно состоять из символов от 2 до 50!"));
            model.addAttribute("registrations", registrationService.getAll());
            return "registrations/saveRegistration";
        } else if (registration.getUser().getAge() > 100 || registration.getUser().getAge() < 18) {
            bindingResult.addError(new FieldError("registration", "user.age", "Возраст от 18 до 100 лет"));
            model.addAttribute("registrations", registrationService.getAll());
            return "registrations/saveRegistration";
        }
        registrationService.registerUser(registration.getUser().getName(), registration.getUser().getAge(), registration.getUser().getEmail());
        model.addAttribute("registrations", registrationService.getAll());
        return "registrations/allRegistrations";
    }
}
