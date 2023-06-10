package com.wallpaper.api.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
    
}
