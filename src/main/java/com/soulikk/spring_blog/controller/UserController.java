package com.soulikk.spring_blog.controller;

import com.soulikk.spring_blog.dto.SignupRequestDto;
import com.soulikk.spring_blog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @PostMapping("/join")
    public String registerUser(SignupRequestDto signupRequestDto) {
        userService.registerUser(signupRequestDto);
        return "redirect:/";
    }



}
