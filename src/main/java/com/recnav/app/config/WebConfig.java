package com.recnav.app.config;

import com.recnav.app.filters.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getSessionManager())
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/**");
        // assuming you put your serve your static files with /resources/ mapping
        // and the pre login page is served with /login mapping
    }

    @Bean
    AuthorizationFilter getSessionManager() {
        return new AuthorizationFilter();
    }
}
