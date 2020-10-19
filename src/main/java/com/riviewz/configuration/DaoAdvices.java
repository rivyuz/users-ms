package com.riviewz.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DaoAdvices {
  
  @Pointcut("execution (* com.riviewz.users.dao.*.*(..) ) ")
  public void allDaoMethods() {}
  
  @Around("allDaoMethods()" )
  public Object logDaoEntryExit(ProceedingJoinPoint method) throws Throwable {

    // Before
    System.out.println("Entering Class = " + method.getTarget().getClass().getName() + "....Method = " + method.getSignature().getName());

    try {

      Object returnValue = method.proceed();

      return returnValue;
    } finally {

      // After
      System.out.println("Exiting Class = " + method.getTarget().getClass().getName() + "....Method = " + method.getSignature().getName());
    }
  } 
  
}
