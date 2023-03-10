package com.assessmentmin.usermanagement.user.repository;

import com.assessmentmin.usermanagement.user.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuerydslUserRepository {
    List<User> findUserWithSearchConditions(Pageable pageable,
                                            String userId,
                                            String userName);
}
