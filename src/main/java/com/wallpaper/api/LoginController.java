package com.wallpaper.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wallpaper.api.models.User;
import com.wallpaper.api.models.UserRepository;

@Controller
// @RestController
public class LoginController {

    @Autowired
    UserRepository userRepository;

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

    @GetMapping("/favorites")
    public String favorites(Model model) {       
        return "favorites";
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

    @GetMapping("/userid")
    @ResponseBody
    public int currentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByUserName(auth.getName()).get();
        return user.getId();
    }   

}
