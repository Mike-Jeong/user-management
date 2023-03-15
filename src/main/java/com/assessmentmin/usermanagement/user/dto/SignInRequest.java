package com.assessmentmin.usermanagement.user.dto;

import com.assessmentmin.usermanagement.user.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class SignInRequest {
    @NotBlank
    private final String userId;
    @NotBlank
    private final String userName;
    @NotBlank
    private final String password;

    @Builder
    public SignInRequest(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public static CreateUserDto toCreateUserDto(SignInRequest signInRequest) {
        return CreateUserDto.builder()
                .userId(signInRequest.getUserId())
                .userName(signInRequest.getUserName())
                .password(signInRequest.getPassword())
                .role(Role.SYSTEM_USER)
                .build();
    }
}
