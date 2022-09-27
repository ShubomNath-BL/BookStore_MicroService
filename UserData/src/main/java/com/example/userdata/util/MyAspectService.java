package com.example.userdata.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MyAspectService {

    @Before(value = "execution(* com.example.userdata.service.UserService.*(..))")
    public void Before(JoinPoint joinPoint){
        System.out.println("Before: "+joinPoint.getSignature().getName());
    }

    @After(value = "execution(* com.example.userdata.service.UserService.*(..))")
    public void After(JoinPoint joinPoint){
        System.out.println("After: "+ Arrays.toString(joinPoint.getArgs()));
    }
}
