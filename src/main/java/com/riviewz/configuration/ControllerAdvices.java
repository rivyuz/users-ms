package com.riviewz.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ControllerAdvices {

  /* for index controller classes*/
  @Pointcut("execution (* com.riviewz.users.controller.*.*(..) ) ")
  public void allControllerMethods() {}
  
  @Around("allControllerMethods()" )
  public Object logControllerEntryExit(ProceedingJoinPoint method) throws Throwable {

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
  
  /*-------------------------------------------------- for util classes --------------------------------------------------*/
  @Pointcut("execution (* com.riviewz.users.util.*.*(..) ) ")
  public void allUtilMethods() {}
  
  @Around("allUtilMethods()" )
  public Object logUtilEntryExit(ProceedingJoinPoint method) throws Throwable {

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
