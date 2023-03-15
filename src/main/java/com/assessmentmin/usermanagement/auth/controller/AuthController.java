package com.assessmentmin.usermanagement.auth.controller;

import com.assessmentmin.usermanagement.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("password", "");
        return "/login";
    }
}
