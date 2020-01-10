package com.zqx.seckillProduct.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@Aspect
@Component
public class SeckillAspect {

    private static Lock lock = new ReentrantLock();

    //将切入点定位到自定义注解上
    @Pointcut(value = "@annotation(com.zqx.seckillProduct.aop.LockAnnotation)")
    public void seckillLock() {
    }

    @Around("seckillLock()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //被切方法执行前
        lock.lock();
        System.out.println("lock...");
        Object object = null;
        try {
            //执行被切入的方法的具体逻辑
            object = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            //被切方法执行完后操作释放锁
            System.out.println("unlock...");
            lock.unlock();
        }
        return object;
    }

}
