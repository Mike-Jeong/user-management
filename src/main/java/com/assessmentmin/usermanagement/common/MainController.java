package com.assessmentmin.usermanagement.common;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class MainController {
    @GetMapping("/")
    public String index(HttpServletRequest request) {
        if (request.getUserPrincipal() == null) { // 로그인 되어 있지 않으면 로그인 페이지로 이동
            return "redirect:/auth/login";
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("SYSTEM_ADMIN"));
        if (isAdmin) { // 권한에 따라 페이지 이동
            return "redirect:/admin/home";
        } else {
            return "redirect:/users/home";
        }
    }
}
