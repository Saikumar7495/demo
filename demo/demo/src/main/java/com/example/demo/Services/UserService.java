package com.example.demo.Services;

import com.example.demo.Repositories.UserRepository;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User signUp(String email, String password){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            throw new RuntimeException("User is already present");
        }
        User newUser = new User();
        newUser.setEmail(email);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        newUser.setPassword(encoder.encode(password));
        return userRepository.save(newUser);
    }

    public boolean login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new RuntimeException("Invalid credentials");
        }
        User oldUser = user.get();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, oldUser.getPassword());
    }


}
