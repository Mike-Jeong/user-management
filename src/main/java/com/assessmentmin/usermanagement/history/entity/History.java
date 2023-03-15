package com.assessmentmin.usermanagement.history.entity;

import com.assessmentmin.usermanagement.history.type.Action;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "USER_HISTORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_idx")
    private Integer idx;
    @Column(name = "url")
    @NotNull
    private String url;
    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private Action action;
    @Column(name = "reg_user_idx")
    private Integer userIdx;
    @Column(name = "reg_ip")
    @NotNull
    private String ip;
    @Column(name = "reg_dt")
    @NotNull
    private LocalDateTime registerDt;

    @Builder
    public History(Integer idx, String url, Action action, Integer userIdx, String ip, LocalDateTime registerDt) {
        this.idx = idx;
        this.url = url;
        this.action = action;
        this.userIdx = userIdx;
        this.ip = ip;
        this.registerDt = registerDt;
    }
}
