package com.blog.newsangblog2.config.web;

import com.blog.newsangblog2.interceptor.ManagerMenuInterceptor;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final ManagerMenuRepository managerMenuRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ManagerMenuInterceptor(managerMenuRepository))
                .addPathPatterns("/manager/**");
    }
}
