package com.lcm.factorybean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


/***
 * 这里演示了一个环绕通知，方法前后自动执行的通知称为环绕通知MethodInterceptor，可以应用于日志、事务管理等功能。
 * 需要实现接口MethodInterceptor，及告诉Spring应该执行哪个方法
 * invoke() 方法用于确定目标方法 mi，并告诉 Spring 要在目标方法前后执行哪些方法，这里为了演示效果在目标方法前后分别向控制台输出了相应语句。
 */
public class SpringAspect implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("方法执行之前");
        Object obj = methodInvocation.proceed();
        System.out.println("方法执行之后");
        return obj;
    }
}
