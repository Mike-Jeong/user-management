package com.assessmentmin.usermanagement.user.repository;

import com.assessmentmin.usermanagement.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, QuerydslUserRepository {
    Optional<User> findByUserId(String userId);

    boolean existsByUserId(String userId);
}
