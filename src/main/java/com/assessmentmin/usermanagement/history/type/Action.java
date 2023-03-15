package com.assessmentmin.usermanagement.history.type;

import com.assessmentmin.usermanagement.exception.UserException;
import com.assessmentmin.usermanagement.exception.type.ErrorCode;
import com.assessmentmin.usermanagement.user.type.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum Action {

    C("POST"),
    U("PUT"),
    D("DELETE");

    private final String actionString;

    public static Action getAction(String action) {
        return Arrays.stream(values())
                .filter(e -> e.actionString.equals(action))
                .findFirst()
                .orElseThrow(() -> new UserException(ErrorCode.UNSUPPORTED_ACTION_TYPE));
    }
}
