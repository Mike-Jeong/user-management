package com.assessmentmin.usermanagement.user.entity;

import com.assessmentmin.usermanagement.user.type.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "SYSTEM_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx")
    private Integer idx;
    @Column(name = "user_id")
    @Length(max = 30)
    @NotNull
    private String userId;
    @Column(name = "user_pw")
    @Length(max = 100)
    @NotNull
    private String password;
    @Column(name = "user_nm")
    @Length(max = 100)
    private String name;
    @Column(name = "user_auth")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role auth;

    @Builder
    public User(Integer idx, String userId, String password, String name, Role auth) {
        this.idx = idx;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.auth = auth;
    }

    public void updateUserName(String name) {
        this.name = name;
    }

}
