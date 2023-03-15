package com.assessmentmin.usermanagement.user.dto;

import com.assessmentmin.usermanagement.user.type.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateUserDto {

    private final String userId;
    private final String userName;
    private final String password;
    private final Role role;

    @Builder
    public CreateUserDto(String userId, String userName, String password, Role role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
