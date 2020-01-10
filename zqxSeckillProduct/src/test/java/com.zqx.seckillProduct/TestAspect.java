package com.zqx.seckillProduct;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class TestAspect {

    //使用org.slf4j.Logger,这是Spring实现日志的方法
    private final static Logger logger = LoggerFactory.getLogger(TestAspect.class);

    /**
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)throws-pattern?)
     * 括号中各个pattern分别表示：
     * <p>
     * 修饰符匹配（modifier-pattern?）
     * 返回值匹配（ret-type-pattern）可以为*表示任何返回值,全路径的类名等
     * 类路径匹配（declaring-type-pattern?）
     * 方法名匹配（name-pattern）可以指定方法名 或者 *代表所有, set* 代表以set开头的所有方法
     * 参数匹配（(param-pattern)）可以指定具体的参数类型，多个参数间用“,”隔开，各个参数也可以用“*”来表示匹配任意类型的参数，
     * 如(String)表示匹配一个String参数的方法；(*,String) 表示匹配有两个参数的方法，第一个参数可以是任意类型，而第二个参数
     * 是String类型；可以用(..)表示零个或多个任意参数
     * 异常类型匹配（throws-pattern?）其中后面跟着“?”的是可选项
     */

    @Pointcut("execution(public * com.zqx.seckillProduct.TestController.*())")
    public void log() {
    }

    /**
     * 记录HTTP请求开始时的日志
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //URL
        logger.info("url={}", request.getRequestURI());
        //method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //类方法
        logger.info("class={} and method name = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        //参数
        logger.info("参数={}", joinPoint.getArgs());
    }

    /**
     * 记录HTTP请求结束时的日志
     */
    @After("log()")
    public void doAfter() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url = {} end of execution", request.getRequestURL());
    }

    /**
     * 获取返回内容
     *
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturn(Object object) {
        logger.info("response={}", object.toString());
    }
}
