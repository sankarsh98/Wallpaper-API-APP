package com.wallpaper.api.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wallpaper.api.models.User;
import com.wallpaper.api.models.UserRepository;

@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    public RegistrationResponse signup(Registration registration) {
        RegistrationResponse registrationResponse = new RegistrationResponse();

        if (!(registration.getPassword().equals(registration.getConfirmPassword()))){
            registrationResponse.setMessage("Passwords does not match");
            registrationResponse.setStatus(false);
            return registrationResponse;
        }
        User user = new User(registration.getUsername(), registration.getPassword(), true, "ROLE_USER", registration.getEmail());
        
        if (userRepository.existsByUserName(registration.getUsername())){
            registrationResponse.setMessage("User name already exists");
            registrationResponse.setStatus(false);
            return registrationResponse;
        }

        if (userRepository.existsByEmail(registration.getEmail())){
            registrationResponse.setMessage("Email already exists");
            registrationResponse.setStatus(false);
            return registrationResponse;
        }

        userRepository.save(user);
        
        registrationResponse.setMessage("Registration successful");
        registrationResponse.setStatus(true);

        return registrationResponse;
    }
    

}
