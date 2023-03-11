package com.assessmentmin.usermanagement.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NotNull
public class DeleteUserRequest {
    String userId;
}
