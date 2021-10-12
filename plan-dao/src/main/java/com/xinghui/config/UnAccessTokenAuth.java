package com.xinghui.config;

import java.lang.annotation.*;

/**
 * 不需要登入自定义注解
 *
 * @author ckx
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface UnAccessTokenAuth {
}