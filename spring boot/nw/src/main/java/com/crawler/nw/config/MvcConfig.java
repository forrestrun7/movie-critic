package com.crawler.nw.config;

//import com.crawler.nw.component.LoginHandlerInterceptor;
import com.crawler.nw.component.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/new","/user/{userid}");

    }

    //重定向
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/log").setViewName("login");
        registry.addViewController("/reg").setViewName("register");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/class").setViewName("class");
        registry.addViewController("/search").setViewName("search_page");
    }
}
