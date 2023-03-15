package com.assessmentmin.usermanagement.user.controller;

import com.assessmentmin.usermanagement.auth.dto.PrincipalDetails;
import com.assessmentmin.usermanagement.exception.UserException;
import com.assessmentmin.usermanagement.exception.type.ErrorCode;
import com.assessmentmin.usermanagement.user.dto.SignInRequest;
import com.assessmentmin.usermanagement.common.dto.PageNumber;
import com.assessmentmin.usermanagement.common.dto.Response;
import com.assessmentmin.usermanagement.user.dto.*;
import com.assessmentmin.usermanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('SYSTEM_USER')")
    @GetMapping("/home")
    public String userHome(Model model,
                           HttpServletRequest request) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();

        model.addAttribute("userName", principalDetails.getUserNickName());
        return "/user/home";
    }

    @RequestMapping("/signIn")
    public String signInForm() {
        return "/user/signIn";
    }

    @PostMapping("/signIn")
    public ResponseEntity<Response> signIn(@RequestBody @Valid SignInRequest signInRequest) {

        userService.createUser(SignInRequest.toCreateUserDto(signInRequest));

        return ResponseEntity.ok().body(Response.ok());

    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public String getUser(Model model,
                          @PathVariable("userId") String userId) {

        UserDto userDto = userService.getUser(userId);

        UserInformation userInformation = UserInformation.fromUserDto(userDto);

        model.addAttribute("userInformation", userInformation);

        return "user/detail";
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    public String getUsers(Model model,
                           @ModelAttribute PageNumber page,
                           @ModelAttribute String userId,
                           @ModelAttribute String userName) {

        List<UserDto> userDtoList = userService.getUsers(page, userId, userName);

        List<UserInformation> userInformationList = userDtoList.stream()
                .map(UserInformation::fromUserDto)
                .collect(Collectors.toList());

        model.addAttribute("userInformationList", userInformationList);

        return "user/list";
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {

        userService.createUser(CreateUserRequest.toCreateUserDto(createUserRequest));

        return ResponseEntity.ok().body(Response.ok());
    }

    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    @PutMapping
    public ResponseEntity<Response> updateUserName(@RequestBody @Valid UpdateUserNameRequest updateUserNameRequest) {

        userService.updateUserName(updateUserNameRequest);

        String redirectUrl = "/user/" + updateUserNameRequest.getUserId();

        return ResponseEntity.ok().body(Response.ok());
    }

    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    @DeleteMapping
    public ResponseEntity<Response> deleteUser(@RequestBody @Valid String userDeleteId) {

        userService.deleteUser(userDeleteId);

        return ResponseEntity.ok().body(Response.ok());
    }

}
