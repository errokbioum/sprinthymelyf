package com.example.lachgar.controller;




import com.example.lachgar.entities.User;
import com.example.lachgar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.lachgar.repository.UserRepository;

import java.util.List;

@Controller
public class UserrController  {
    @Autowired
    private UserRepository userRepository;
    private UserService userService;
    @GetMapping("/")
    public String racine(Model model ) {
        model.addAttribute("oumaima", userRepository.findAll());

        return"index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user ) {

        return"add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", new User());
            return"add-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return"index";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id")long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return"update-user";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return"update-user";
        }

        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return"index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() ->new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        model.addAttribute("users", userRepository.findAll());
        return"index";
    }

    @GetMapping("/find/{name}")
    public String showEmailForm(@PathVariable("name")String name, Model model) {
        if (userRepository.findUsersByName(name).isEmpty()) {
            throw new IllegalArgumentException("No user found with name: " + name);
        }
        model.addAttribute("name", userRepository.findUsersByName(name));
        return"name-user";
    }

}




