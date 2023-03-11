package com.assessmentmin.usermanagement.common.dto;

import com.assessmentmin.usermanagement.common.type.ResponseType;
import lombok.Getter;

@Getter
public class Response {

    private final String code;
    private final String message;

    public Response(ResponseType responseType) {
        this.code = responseType.getHttpStatus().toString();
        this.message = responseType.getResponseMessage();
    }

    public static Response ok() {
        return new Response(ResponseType.OK);
    }
}
