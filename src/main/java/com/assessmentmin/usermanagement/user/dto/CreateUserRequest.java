package com.assessmentmin.usermanagement.user.dto;

import com.assessmentmin.usermanagement.user.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@NotNull
@AllArgsConstructor
public class CreateUserRequest {
    private final String userId;
    private final String userName;
    private final String password;
    private final Role role;

    @Builder
    public CreateUserRequest(String userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = Role.getRole(role);
    }

    public static CreateUserDto toCreateUserDto(CreateUserRequest createUserRequest) {
        return CreateUserDto.builder()
                .userId(createUserRequest.getUserId())
                .userName(createUserRequest.getUserName())
                .password(createUserRequest.getPassword())
                .role(createUserRequest.getRole())
                .build();
    }
}
