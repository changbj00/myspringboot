package com.myspringboot.aspect;

import com.myspringboot.dao.SysLogDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private SysLogDao sysLogDao;
    @Pointcut("@annotation(com.myspringboot.annotation.Log)")
    public void pointcut(){

    }
    @Around("pointcut()")
    public void around(ProceedingJoinPoint point){

    }
    private void saveLog(ProceedingJoinPoint joinPoint,long time){

    }
}
