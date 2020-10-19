package com.riviewz.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ServiceAdvices {
  
  @Pointcut("execution (* com.riviewz.users.service.*.*(..) ) ")
  public void allServiceMethods() {}
  
  @Around("allServiceMethods()" )
  public Object logCommonServiceEntryExit(ProceedingJoinPoint method) throws Throwable {

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
