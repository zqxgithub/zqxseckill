package com.zqx.seckillProduct.aop;

import java.lang.annotation.*;

/**
 * 自定义注解，将aop实现的锁机制切入点设置到该注释，这样只需在要使用的方法上加上该注解，而不用频繁设置切入点。
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LockAnnotation {
}
