package com.assessmentmin.usermanagement.user.service;

import com.assessmentmin.usermanagement.auth.dto.PrincipalDetails;
import com.assessmentmin.usermanagement.common.dto.PageNumber;
import com.assessmentmin.usermanagement.exception.UserException;
import com.assessmentmin.usermanagement.exception.type.ErrorCode;
import com.assessmentmin.usermanagement.user.dto.*;
import com.assessmentmin.usermanagement.user.entity.User;
import com.assessmentmin.usermanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final int PAGE_SIZE = 10;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto> getUsers(PageNumber pageNumber, String userId, String userName) {

        PageRequest pageRequest = PageRequest.of(pageNumber.getPage(), PAGE_SIZE);

        List<User> userList = userRepository.findUserWithSearchConditions(pageRequest, userId, userName);

        return userList.stream()
                .map(UserDto::fromUser)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto getUser(String userId) {

        User user = findUser(userId);

        return UserDto.fromUser(user);
    }

    @Transactional
    public Integer createUser(CreateUserDto createUserDto) {

        userIdValidation(createUserDto.getUserId());

        User user = User.builder()
                .userId(createUserDto.getUserId())
                .name(createUserDto.getUserName())
                .password(createUserDto.getPassword())
                .auth(createUserDto.getRole())
                .build();

        Integer userIdx = userRepository.save(user).getIdx();

        return userIdx;

    }

    @Transactional
    public Integer updateUserName(UpdateUserNameRequest updateUserNameRequest) {

        User user = findUser(updateUserNameRequest.getUserId());

        user.updateUserName(updateUserNameRequest.getNewUserName());

        return user.getIdx();
    }

    @Transactional
    public Integer deleteUser(UserDeleteRequest userDeleteRequest) {

        preventDeleteUserItself(userDeleteRequest.getUserDeleteId());

        User user = findUser(userDeleteRequest.getUserDeleteId());

        Integer userIdx = user.getIdx();

        userRepository.delete(user);

        return userIdx;

    }

    private void preventDeleteUserItself(String userDeleteId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails principalDetails = (PrincipalDetails) auth.getPrincipal();

        if (principalDetails.getUsername().equals(userDeleteId)) {
            throw new UserException(ErrorCode.UNAUTHORIZED_USER);
        }

    }

    private User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
    }

    private void userIdValidation(String userId) {

        if (userRepository.existsByUserId(userId)) {
            throw new UserException(ErrorCode.USER_ID_ALREADY_USED);
        }

    }
}
