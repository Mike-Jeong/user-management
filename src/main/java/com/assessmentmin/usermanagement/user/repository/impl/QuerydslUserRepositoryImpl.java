package com.assessmentmin.usermanagement.user.repository.impl;

import com.assessmentmin.usermanagement.user.entity.User;
import com.assessmentmin.usermanagement.user.repository.QuerydslUserRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.assessmentmin.usermanagement.user.entity.QUser.user;

@Repository
@RequiredArgsConstructor
public class QuerydslUserRepositoryImpl implements QuerydslUserRepository {

    private final JPAQueryFactory queryFactory;


    @Override
    public List<User> findUserWithSearchConditions(Pageable pageable, String userId, String userName) {
        return queryFactory.selectFrom(user)
                .where(
                        toContainsUserId(userId),
                        toContainsUserName(userName)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();
    }

    private BooleanExpression toContainsUserId(final String userId) {
        if (isStringBlank(userId)) {
            return null;
        }
        return user.userId.contains(userId);
    }

    private BooleanExpression toContainsUserName(final String userName) {
        if (isStringBlank(userName)) {
            return null;
        }
        return user.userId.contains(userName);
    }

    private boolean isStringBlank(String string) {
        return StringUtils.isBlank(string);
    }

}
