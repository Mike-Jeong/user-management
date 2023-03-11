package com.assessmentmin.usermanagement.security;

import com.assessmentmin.usermanagement.exception.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.assessmentmin.usermanagement.exception.type.ErrorCode.UNAUTHORIZED_USER;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        setResponse(response);

        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.builder()
                .status(UNAUTHORIZED_USER.getHttpStatus().value())
                .errorCode(UNAUTHORIZED_USER)
                .errorMessage(UNAUTHORIZED_USER.getErrorMessage())
                .build()));
        response.getWriter().flush();
    }

    private void setResponse(HttpServletResponse response) {
        response.setStatus(UNAUTHORIZED_USER.getHttpStatus().value());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
    }

}