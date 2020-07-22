package com.blog.newsangblog2.config.web;

import com.blog.newsangblog2.common.annotation.login.LoginUserArgumentResolver;
import com.blog.newsangblog2.interceptor.ManagerMenuInterceptor;
import com.blog.newsangblog2.web.manager.menu.repository.ManagerMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final ManagerMenuRepository managerMenuRepository;
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ManagerMenuInterceptor(managerMenuRepository))
                .excludePathPatterns(
                        "/manager/user/create",
                        "/manager/user/edit",
                        "/manager/user/duplicate-info-check"
                )
                .addPathPatterns("/manager/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(loginUserArgumentResolver);
    }
}
