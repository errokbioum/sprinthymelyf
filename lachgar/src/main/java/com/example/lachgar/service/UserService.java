package com.example.lachgar.service;

import com.example.lachgar.entities.User;
import com.example.lachgar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;  // On injecte le repository

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        return userRepository.save(user);  // Utilisation du repository
    }

    public List<User> getUsersByName(String name) {
        return userRepository.findUsersByName(name);  // Appel de la méthode du repository
    }

}

