package com.yoyu.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
    public ScriptInterceptor getScriptInterceptor() {
        return new ScriptInterceptor();
    }

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(getScriptInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/**");
        super.addResourceHandlers(registry);
    }   
}
