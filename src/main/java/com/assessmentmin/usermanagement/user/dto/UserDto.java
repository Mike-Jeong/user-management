package com.assessmentmin.usermanagement.user.dto;

import com.assessmentmin.usermanagement.user.entity.User;
import com.assessmentmin.usermanagement.user.type.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserDto {

    private final Integer idx;
    private final String userId;
    private final String name;
    private final Role auth;

    @Builder
    public UserDto(Integer idx, String userId, String name, Role auth) {
        this.idx = idx;
        this.userId = userId;
        this.name = name;
        this.auth = auth;
    }

    public static UserDto fromUser(User user){
        return UserDto.builder()
                .idx(user.getIdx())
                .userId(user.getUserId())
                .name(user.getName())
                .auth(user.getAuth())
                .build();
    }
}
