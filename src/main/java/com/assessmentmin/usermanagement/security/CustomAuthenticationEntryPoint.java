package com.assessmentmin.usermanagement.security;

import com.assessmentmin.usermanagement.exception.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.assessmentmin.usermanagement.exception.type.ErrorCode.LOGIN_NEEDED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        setResponse(response);

        response.getWriter().write(objectMapper.writeValueAsString(ErrorResponse.builder()
                .status(LOGIN_NEEDED.getHttpStatus().value())
                .errorCode(LOGIN_NEEDED)
                .errorMessage(LOGIN_NEEDED.getErrorMessage())
                .build()));
        response.getWriter().flush();
    }

    private void setResponse(HttpServletResponse response) {
        response.setStatus(LOGIN_NEEDED.getHttpStatus().value());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
    }

}
