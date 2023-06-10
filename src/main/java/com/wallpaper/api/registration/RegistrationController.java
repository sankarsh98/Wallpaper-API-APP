package com.wallpaper.api.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin
@RestController
public class RegistrationController {
    
    @Autowired
    RegistrationService registrationService;

    @PostMapping("/signup/")
    public RegistrationResponse signup(@RequestBody Registration registration){
        return registrationService.signup(registration);
    }

    
}
