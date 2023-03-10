package com.assessmentmin.usermanagement.user.type;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Role {

    SYSTEM_ADMIN("관리자"),
    SYSTEM_USER("회원");

    private final String roleString;

    public static Role getRole(String role) {
        return Arrays.stream(values())
                .filter(e -> e.roleString.equals(role))
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }
}
