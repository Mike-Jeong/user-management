package com.assessmentmin.usermanagement.user.service;

import com.assessmentmin.usermanagement.common.dto.PageNumber;
import com.assessmentmin.usermanagement.exception.UserException;
import com.assessmentmin.usermanagement.exception.type.ErrorCode;
import com.assessmentmin.usermanagement.user.dto.*;
import com.assessmentmin.usermanagement.user.entity.User;
import com.assessmentmin.usermanagement.user.repository.UserRepository;
import com.assessmentmin.usermanagement.user.type.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void getUsers_ReturnsUserList() {
        // Given
        PageNumber pageNumber = new PageNumber(1);
        List<User> userList = List.of(
                new User(1, "test", "password", "admin" ,Role.SYSTEM_ADMIN),
                new User(2, "test2", "password2","user" ,Role.SYSTEM_USER)
        );
        given(userRepository.findUserWithSearchConditions(any(), any(), any())).willReturn(userList);

        // When
        List<UserDto> result = userService.getUsers(pageNumber, null, null);

        // Then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(0).getUserId()).isEqualTo(userList.get(0).getUserId());
        assertThat(result.get(0).getName()).isEqualTo(userList.get(0).getName());
        assertThat(result.get(0).getAuth()).isEqualTo(userList.get(0).getAuth());
        assertThat(result.get(1).getUserId()).isEqualTo(userList.get(1).getUserId());
        assertThat(result.get(1).getName()).isEqualTo(userList.get(1).getName());
        assertThat(result.get(1).getAuth()).isEqualTo(userList.get(1).getAuth());
    }

    @Test
    void createUser_SavesUser() {
        // Given
        String userId = "123";
        CreateUserDto createUserDto = new CreateUserDto("123", "test", "password", Role.SYSTEM_USER);
        User user = new User(2, userId, "test", "password", Role.SYSTEM_USER);
        given(userRepository.existsByUserId(userId)).willReturn(false);
        given(userRepository.save(any())).willReturn(user);

        // When
        userService.createUser(createUserDto);

        // Then
        verify(userRepository).save(any());
    }

    @Test
    void updateUserName_UpdatesUserName() {
        // Given
        String userId = "123";
        String newUserName = "updatedName";
        UpdateUserNameRequest updateUserNameRequest = new UpdateUserNameRequest(userId, newUserName);
        User user = new User(2, userId, "test", "password", Role.SYSTEM_USER);
        given(userRepository.findByUserId(userId)).willReturn(Optional.of(user));

        // When
        Integer userIdx = userService.updateUserName(updateUserNameRequest);

        // Then
        assertThat(userIdx).isEqualTo(2);
    }

    @Test
    void updateUserName_ThrowsUserException_WhenUserNotFound() {
        // Given
        String userId = "123";
        String newUserName = "updatedName";
        UpdateUserNameRequest updateUserNameRequest = new UpdateUserNameRequest(userId, newUserName);
        given(userRepository.findByUserId(userId)).willReturn(Optional.empty());

        // When, Then
        assertThatThrownBy(() -> userService.updateUserName(updateUserNameRequest))
                .isInstanceOf(UserException.class)
                .hasMessage(ErrorCode.USER_NOT_FOUND.getErrorMessage());
    }
}
