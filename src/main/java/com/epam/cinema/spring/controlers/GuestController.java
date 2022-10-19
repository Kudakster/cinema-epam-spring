package com.epam.cinema.spring.controlers;

import com.epam.cinema.spring.enity.User;
import com.epam.cinema.spring.enity.enumeration.UserRole;
import com.epam.cinema.spring.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/guest")
public class GuestController {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/registration")
    public String getRegistrationPage(@ModelAttribute("user") User user) {
        return Pages.Guest.REGISTRATION_PAGE;
    }

    @PostMapping(value = "/sign-up")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return getRegistrationPage(user);
        }
        user.setUserRole(UserRole.USER.toString());
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        if (userService.addUser(user) == null) {
            ObjectError error = new ObjectError("global", "user.alreadyExist");
            result.addError(error);
            return Pages.Guest.REGISTRATION_PAGE;
        }
        return "redirect:/login";
    }
}
