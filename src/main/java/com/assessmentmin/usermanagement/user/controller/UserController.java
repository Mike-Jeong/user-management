package com.assessmentmin.usermanagement.user.controller;

import com.assessmentmin.usermanagement.aop.LogUserHistory;
import com.assessmentmin.usermanagement.auth.dto.PrincipalDetails;
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
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PreAuthorize("hasAuthority('SYSTEM_USER')")
    @GetMapping("/home")
    public String userHome(Model model) {

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
    @LogUserHistory
    public ResponseEntity<Response> signIn(@RequestBody @Valid SignInRequest signInRequest,
                                           HttpServletRequest request) {

        Integer userIdx = userService.createUser(SignInRequest.toCreateUserDto(signInRequest));
        request.setAttribute("userIdx", userIdx);

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
    @LogUserHistory
    public ResponseEntity<Response> createUser(@RequestBody @Valid CreateUserRequest createUserRequest,
                                               HttpServletRequest request) {

        Integer userIdx = userService.createUser(CreateUserRequest.toCreateUserDto(createUserRequest));
        request.setAttribute("userIdx", userIdx);

        return ResponseEntity.ok().body(Response.ok());
    }

    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    @PutMapping
    @LogUserHistory
    public ResponseEntity<Response> updateUserName(@RequestBody @Valid UpdateUserNameRequest updateUserNameRequest,
                                                   HttpServletRequest request) {

        Integer userIdx = userService.updateUserName(updateUserNameRequest);
        request.setAttribute("userIdx", userIdx);

        return ResponseEntity.ok().body(Response.ok());
    }

    @PreAuthorize("hasAuthority('SYSTEM_ADMIN')")
    @DeleteMapping
    @LogUserHistory
    public ResponseEntity<Response> deleteUser(@RequestBody @Valid UserDeleteRequest userDeleteRequest,
                                               HttpServletRequest request) {

        Integer userIdx = userService.deleteUser(userDeleteRequest);
        request.setAttribute("userIdx", userIdx);

        return ResponseEntity.ok().body(Response.ok());
    }

}
