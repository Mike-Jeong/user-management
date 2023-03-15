package com.assessmentmin.usermanagement.auth.service;

import com.assessmentmin.usermanagement.auth.dto.PrincipalDetails;
import com.assessmentmin.usermanagement.exception.UserException;
import com.assessmentmin.usermanagement.exception.type.ErrorCode;
import com.assessmentmin.usermanagement.user.entity.User;
import com.assessmentmin.usermanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        PrincipalDetails principalDetails = new PrincipalDetails(user);
        principalDetails.encodePassword(passwordEncoder);

        return principalDetails;
    }
}
