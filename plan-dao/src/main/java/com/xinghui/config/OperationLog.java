package com.xinghui.config;

import com.xinghui.enums.OperationLogTypeEnum;

import java.lang.annotation.*;

/**
 * 操作日志注解
 *
 * @author ChenKuanXing
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLog {

    /**
     * 操作类型（默认登入类型）
     *
     * @return
     */
    OperationLogTypeEnum type() default OperationLogTypeEnum.LOGIN;

    /**
     * 操作内容
     *
     * @return
     */
    String content() default "";

}
