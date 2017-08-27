package com.zong.pay.common.core.interceptor;

import com.zong.paycommon.exceptions.BizException;
import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * 异常的处理 拦截
 * @author 宗叶青 on 2017/8/21/23:40
 */
public class ExceptionInterceptorLog implements ThrowsAdvice {
    private static final Logger LOGGER = Logger.getLogger(ExceptionInterceptorLog.class);

    /**
     * 对未知异常的处理. <br>
     * Method method 执行的方法 Object[] args <br>
     * 方法参数 Object target <br>
     * 代理的目标对象 Throwable BizException 产生的异常 <br>
     */
    public void afterThrowing(Method method, Object[] args, Object target, BizException ex){
        LOGGER.info("==>ExceptionInterceptorLog.BizException");
        LOGGER.info("==>errCode:" + ex.getCode() + " errMsg:" + ex.getMsg());
        LOGGER.info("==>" + ex.fillInStackTrace());
    }

    public void afterThrowing(Method method, Object[] args, Object target, Exception ex){
        LOGGER.error("==>ExceptionInterceptorLog.Exception");

        LOGGER.error("==>Error class: " + target.getClass().getName());
        LOGGER.error("==>Error method: " + method.getName());

        for (int i = 0; i < args.length; i++) {
            LOGGER.error("==>args[" + i + "]: " + args[i]);
        }

        LOGGER.error("==>Exception class: " + ex.getClass().getName());
        LOGGER.error("==>" + ex.fillInStackTrace());
        ex.printStackTrace();
    }
}
