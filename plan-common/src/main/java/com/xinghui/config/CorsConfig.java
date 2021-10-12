package com.xinghui.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author ckx
 * @since 2021/9/14
 * 跨域处理类
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("POST", "GET", "PATCH", "DELETE", "PUT")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }

}
