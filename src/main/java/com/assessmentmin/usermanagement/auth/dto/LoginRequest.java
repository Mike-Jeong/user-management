package com.assessmentmin.usermanagement.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class LoginRequest {
    @NotBlank
    private String userId;
    @NotNull
    private String password;
}
