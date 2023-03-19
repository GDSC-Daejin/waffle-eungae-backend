package com.example.waffleeungaebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://waffle-eungae-frontend.vercel.app/","http://localhost:3000")
                .allowedMethods("GET","POST","PATCH","DELETE");

    }
}
