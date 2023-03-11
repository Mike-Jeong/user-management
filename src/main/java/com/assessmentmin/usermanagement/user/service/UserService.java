package com.assessmentmin.usermanagement.user.service;

import com.assessmentmin.usermanagement.common.dto.PageNumber;
import com.assessmentmin.usermanagement.exception.UserException;
import com.assessmentmin.usermanagement.exception.type.ErrorCode;
import com.assessmentmin.usermanagement.user.dto.CreateUserDto;
import com.assessmentmin.usermanagement.user.dto.DeleteUserRequest;
import com.assessmentmin.usermanagement.user.dto.UpdateUserNameRequest;
import com.assessmentmin.usermanagement.user.dto.UserDto;
import com.assessmentmin.usermanagement.user.entity.User;
import com.assessmentmin.usermanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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

    @Transactional
    public void createUser(CreateUserDto createUserDto) {

        User user = User.builder()
                .userId(createUserDto.getUserId())
                .name(createUserDto.getUserName())
                .password(createUserDto.getPassword())
                .auth(createUserDto.getRole())
                .build();

        userRepository.save(user);

    }

    @Transactional
    public void updateUserName(UpdateUserNameRequest updateUserNameRequest) {

        User user = findUser(updateUserNameRequest.getUserId());

        user.updateUserName(updateUserNameRequest.getNewUserName());
    }

    @Transactional
    public void deleteUser(DeleteUserRequest deleteUserRequest) {

        User user = findUser(deleteUserRequest.getUserId());

        userRepository.delete(user);

    }

    private User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
    }
}
