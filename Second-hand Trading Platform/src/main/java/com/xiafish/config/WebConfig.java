package com.xiafish.config;

import com.xiafish.interceptor.AdminCheckInterceptor;
import com.xiafish.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;




@Configuration
//用于禁用Spring Boot的安全自动配置
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class })
public class WebConfig implements WebMvcConfigurer {

    //该函数用于配置拦截器，对所有的请求路径进行拦截
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Autowired
    private AdminCheckInterceptor adminCheckInterceptor;
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").order(1);
//        registry.addInterceptor(adminCheckInterceptor).addPathPatterns("/admin/**").order(2);
//    }


    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration() {
            @Override
            public String checkOrigin(String requestOrigin) {
                return requestOrigin;
            }
        };
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
