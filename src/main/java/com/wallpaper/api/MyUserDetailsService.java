package com.wallpaper.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wallpaper.api.models.MyUserDetails;
import com.wallpaper.api.models.User;
import com.wallpaper.api.models.UserRepository;



@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByUserName(userName);
        user.orElseThrow(()-> new UsernameNotFoundException("Not found: "+userName));
        return user.map(MyUserDetails::new).get();
    }
    
}
