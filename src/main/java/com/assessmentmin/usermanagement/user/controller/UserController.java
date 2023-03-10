package com.assessmentmin.usermanagement.user.controller;

import com.assessmentmin.usermanagement.common.dto.PageNumber;
import com.assessmentmin.usermanagement.user.dto.UserDto;
import com.assessmentmin.usermanagement.user.dto.UserInformation;
import com.assessmentmin.usermanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserInformation>> getMembers(@ModelAttribute PageNumber page,
                                                            @ModelAttribute String userId,
                                                            @ModelAttribute String userName) {

        List<UserDto> userDtoList = userService.getUsers(page, userId, userName);

        List<UserInformation> userInformationList = userDtoList.stream()
                .map(UserInformation::fromUserDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userInformationList);
    }
}
