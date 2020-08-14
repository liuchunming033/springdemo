package com.lcm.aspectj.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AnnotationAspectJ {
    /**
     * 配置切入点，通知最后增强哪些方法
     * 用于取代：<aop:pointcut expression="execution(*com.mengma.dao..*.*(..))" id="myPointCut"/>
     * 要求：方法必须是private，没有值，名称自定义，没有参数
     * 其中，切入点表达式的使用规则：
     *
     *  1、execution(): 表达式主体。
     *
     *  2、第一个*号：表示返回类型，*号表示所有的类型。
     *
     *  3、包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.sample.service.impl包、子孙包下所有类的方法。
     *
     *  4、第二个*号：表示类名，*号表示所有的类。
     *
     *  5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     */
    @Pointcut("execution(* com.lcm.aspectj..*.*(..))")
    private void myPointCut() {
    }

    /**
     * 前置通知, 在方法之前自动执行的通知称为前置通知，可以应用于权限管理等功能。
     * @param joinPoint: 切入点
     */
    @Before("myPointCut()")
    public void myBefore(JoinPoint joinPoint) {
        System.out.print("前置通知，目标：" + joinPoint);
        System.out.print(joinPoint.getTarget() + "方法名称:");
        System.out.println(joinPoint.getSignature().getName());
    }


    /**
     * 后置通知, 在方法之后自动执行的通知称为后置通知，可以应用于关闭流、上传文件、删除临时文件等功能。
     * @param joinPoint
     */
    @AfterReturning(value = "myPointCut()")
    public void myAfterReturning(JoinPoint joinPoint) {
        System.out.println("后置通知，方法名称：" + joinPoint.getSignature().getName());
    }

    /**
     * 在方法前后自动执行的通知称为环绕通知，可以应用于日志、事务管理等功能。
     * 环绕通知, 必须接收一个类型为 ProceedingJoinPoint 的参数，返回值必须是 Object 类型，且必须抛出异常
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("myPointCut()")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        System.out.println("环绕开始"); // 开始
        Object obj = proceedingJoinPoint.proceed(); // 执行当前目标方法
        System.out.println("环绕结束"); // 结束
        return obj;
    }


    /**
     * 在方法抛出异常时自动执行的通知称为异常通知，可以应用于处理异常记录日志等功能。
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "myPointCut()", throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("异常通知" + "出错了" + e.getMessage());
    }

    /**
     * 是在目标方法执行之后执行的通知。
     *
     * 和后置通知不同之处在于，后置通知是在方法正常返回后执行的通知，如果方法没有正常返-例如抛出异常，则后置通知不会执行。
     *
     * 而最终通知无论如何都会在目标方法调用过后执行，即使目标方法没有正常的执行完成。
     *
     * 另外，后置通知可以通过配置得到返回值，而最终通知无法得到。
     *
     * 最终通知也可以额外接收一个JoinPoint参数，来获取目标对象和目标方法相关信息，但一定要保证必须是第一个参数。
     */
    @After("myPointCut()")
    public void myAfter() {
        System.out.println("最终通知");
    }
}
