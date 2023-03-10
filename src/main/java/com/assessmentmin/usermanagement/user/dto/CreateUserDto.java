package com.assessmentmin.usermanagement.user.dto;

import com.assessmentmin.usermanagement.user.type.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDto {
    String userId;
    String userName;
    String password;
    Role role;

    @Builder
    public CreateUserDto (String userId, String userName, String password, String role){
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = Role.getRole(role);
    }
}
