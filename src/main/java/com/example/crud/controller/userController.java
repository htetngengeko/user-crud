package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class userController {
    @Autowired
    private UserRepo userRepo;

    @RequestMapping("/register")
    public String registerPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("error", false);
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @ModelAttribute("user") User user) {
        User db_user = userRepo.checkEmail(user.getEmail());
        if(db_user == null) {
            userRepo.save(user);
            model.addAttribute("user", new User());
            return "login";
        }else{
            model.addAttribute("error", true);
        }
        model.addAttribute("user", user);

        return "register";
    }

    @RequestMapping("/login")
    public String login(Model model, @ModelAttribute("user")User user) {
        model.addAttribute("user", user);
        model.addAttribute("error", false);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLoginPage(Model model, @ModelAttribute("user")User user) {
        User existUser = userRepo.checkLogin(user.getEmail(), user.getPassword());

        if (existUser != null) {
            return "main";
        } else {
            model.addAttribute("error", true);
        }

        model.addAttribute("user", user);

        return "login";
    }
}
