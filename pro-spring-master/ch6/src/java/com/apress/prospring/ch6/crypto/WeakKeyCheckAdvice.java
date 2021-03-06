/*
 * Created on Aug 12, 2004
 */
package com.apress.prospring.ch6.crypto;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

/**
 * @author robh
 */
public class WeakKeyCheckAdvice implements AfterReturningAdvice {

    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {

        if ((target instanceof KeyGenerator)
                && ("getKey".equals(method.getName()))) {
            long key = ((Long) returnValue).longValue();

            if (key == KeyGenerator.WEAK_KEY) {
                throw new SecurityException(
                        "Key Generator generated a weak key. Try again");
            }
        }
    }

}