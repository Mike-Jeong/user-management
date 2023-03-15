package com.assessmentmin.usermanagement.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class AdminController {

    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    @GetMapping("/admin/home")
    public String adminHome(HttpServletRequest request) {

        return "/admin/home";
    }
}
