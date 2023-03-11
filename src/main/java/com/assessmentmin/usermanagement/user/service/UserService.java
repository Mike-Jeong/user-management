package com.assessmentmin.usermanagement.user.service;

import com.assessmentmin.usermanagement.common.dto.PageNumber;
import com.assessmentmin.usermanagement.user.dto.CreateUserDto;
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
}
