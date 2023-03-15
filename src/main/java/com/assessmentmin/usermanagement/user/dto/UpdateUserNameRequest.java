package com.assessmentmin.usermanagement.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NotNull
public class UpdateUserNameRequest {
    private final String userId;
    private final String newUserName;
}
