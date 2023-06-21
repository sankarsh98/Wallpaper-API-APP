package com.wallpaper.api;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@CrossOrigin
// @RestController
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {       
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {       
        return "signup";
    }

    @GetMapping("/index")
    public String index(Model model) {       
        return "index";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "logged_out";
    }

    @GetMapping("/user")
    @ResponseBody
    public String currentUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }



}
