package com.assessmentmin.usermanagement.user.dto;

import com.assessmentmin.usermanagement.user.type.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInformation {

    private final Integer userIndex;
    private final String userId;
    private final String name;
    private final String auth;

    @Builder
    public UserInformation(Integer idx, String userId, String name, Role auth) {
        this.userIndex = idx;
        this.userId = userId;
        this.name = name;
        this.auth = auth.getRoleString();
    }

    public static UserInformation fromUserDto(UserDto userDto) {
        return UserInformation.builder()
                .idx(userDto.getIdx())
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .auth(userDto.getAuth())
                .build();
    }
}
