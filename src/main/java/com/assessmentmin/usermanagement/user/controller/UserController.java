package com.assessmentmin.usermanagement.user.controller;

import com.assessmentmin.usermanagement.common.dto.PageNumber;
import com.assessmentmin.usermanagement.common.dto.Response;
import com.assessmentmin.usermanagement.user.dto.CreateUserDto;
import com.assessmentmin.usermanagement.user.dto.UpdateUserNameRequest;
import com.assessmentmin.usermanagement.user.dto.UserDto;
import com.assessmentmin.usermanagement.user.dto.UserInformation;
import com.assessmentmin.usermanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserInformation>> getUsers(@ModelAttribute PageNumber page,
                                                            @ModelAttribute String userId,
                                                            @ModelAttribute String userName) {

        List<UserDto> userDtoList = userService.getUsers(page, userId, userName);

        List<UserInformation> userInformationList = userDtoList.stream()
                .map(UserInformation::fromUserDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(userInformationList);
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody @Valid CreateUserDto createUserDto){

        userService.createUser(createUserDto);

        return ResponseEntity.ok().body(Response.ok());
    }

    @PostMapping
    public ResponseEntity<Response> updateUserName(@RequestBody @Valid UpdateUserNameRequest updateUserNameRequest){

        userService.updateUserName(updateUserNameRequest);

        return ResponseEntity.ok().body(Response.ok());
    }

}
