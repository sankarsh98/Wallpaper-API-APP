package com.wallpaper.api.models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer>{

    Optional<User> findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    // @Query("SELECT c FROM user c WHERE c.email = ?1")
    @Query(nativeQuery = true,value = "select * from user where email = ?1")
    User findByEmail(String email); 

    User findByResetPasswordToken(String token);
    
}
