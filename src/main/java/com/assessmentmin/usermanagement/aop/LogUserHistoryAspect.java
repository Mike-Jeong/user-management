package com.assessmentmin.usermanagement.aop;

import com.assessmentmin.usermanagement.history.entity.History;
import com.assessmentmin.usermanagement.history.repository.HistoryRepository;
import com.assessmentmin.usermanagement.history.type.Action;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Component
public class LogUserHistoryAspect {

    @Autowired
    HistoryRepository historyRepository;

    @Around("@annotation(LogUserHistory)")
    public Object logUserHistory(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            throw e;
        }

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        Integer userIdx = (Integer) request.getAttribute("userIdx");
        String ipAddress = request.getRemoteAddr();

        if (result.toString().startsWith("<200")) {
            historyRepository.save(
                    History.builder()
                            .url(url)
                            .action(Action.getAction(method))
                            .userIdx(userIdx)
                            .ip(ipAddress)
                            .registerDt(LocalDateTime.now())
                            .build()
            );
        }

        return result;
    }
}
