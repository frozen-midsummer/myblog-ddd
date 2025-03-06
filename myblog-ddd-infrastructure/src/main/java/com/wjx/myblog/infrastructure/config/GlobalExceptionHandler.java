package com.wjx.myblog.infrastructure.config;

import com.wjx.common.exception.BaseExceptionHandler;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
    @Pointcut("execution(* com.wjx.myblog..service.impl.*.*(..))")
    public void serviceMethods() {
    }
}
