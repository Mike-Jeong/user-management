package com.assessmentmin.usermanagement.auth.controller;

import com.assessmentmin.usermanagement.auth.dto.LoginRequest;
import com.assessmentmin.usermanagement.auth.service.AuthService;
import com.assessmentmin.usermanagement.common.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Response> login(@Valid @RequestBody LoginRequest request) {

        authService.login(request);

        return ResponseEntity.ok(Response.ok());
    }

}
