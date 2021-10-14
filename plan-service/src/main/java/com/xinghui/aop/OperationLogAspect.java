package com.xinghui.aop;

import cn.hutool.core.thread.threadlocal.NamedThreadLocal;
import com.xinghui.config.OperationLog;
import com.xinghui.entity.OperationLogDO;
import com.xinghui.service.OperationLogService;
import com.xinghui.utils.RequestContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

    @Resource
    private OperationLogService operationLogService;

    //Controller层切点
    @Pointcut("@annotation(com.xinghui.config.OperationLog)")
    public void controllerAspect() {

    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作 保存操作时间
     *
     * @param joinPoint 切点
     */
    @SuppressWarnings("rawtypes")
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException {
        //线程绑定变量（该数据只有当前请求的线程可见）
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
    }

    /**
     * 后置通知(在方法执行之后并返回数据) 用于拦截Controller层无异常的操作
     *
     * @param joinPoint 切点
     */
    @AfterReturning("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        try {
            String content = getControllerMethodInfo(joinPoint).get("content").toString();
            Integer type = Integer.valueOf(getControllerMethodInfo(joinPoint).get("type").toString());

            //请求开始时间
            long beginTime = beginTimeThreadLocal.get().getTime();
            long endTime = System.currentTimeMillis();
            //请求耗时
            Long logElapsedTime = endTime - beginTime;

            LocalDateTime operationTime = LocalDateTime.ofEpochSecond(beginTime / 1000, 0, ZoneOffset.ofHours(8));

            OperationLogDO operationLogDO = new OperationLogDO();
            operationLogDO.setOperationBy(RequestContextUtil.userId());
            operationLogDO.setOperationTime(operationTime);
            operationLogDO.setOperationName(RequestContextUtil.nameCn());
            operationLogDO.setRemark(content);
            operationLogDO.setType(type);
            operationLogDO.setElapsedTime(logElapsedTime / 1000);

            //持久化(存储到数据或者ES，可以考虑用线程池)
            operationLogService.save(operationLogDO);
        } catch (Exception e) {
            log.error("AOP后置通知异常", e);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Map<String, Object> getControllerMethodInfo(JoinPoint joinPoint) throws Exception {
        Map<String, Object> map = new HashMap<>(16);
        //获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        //获取方法名
        String methodName = joinPoint.getSignature().getName();
        //获取相关参数
        Object[] arguments = joinPoint.getArgs();
        //生成类对象
        Class targetClass = Class.forName(targetName);
        //获取该类中的方法
        Method[] methods = targetClass.getMethods();
        String content = "";
        Integer type = null;
        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                //比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载哦
                continue;
            }
            content = method.getAnnotation(OperationLog.class).content();
            type = method.getAnnotation(OperationLog.class).type().ordinal();
            map.put("content", content);
            map.put("type", type);
        }
        return map;
    }

}
