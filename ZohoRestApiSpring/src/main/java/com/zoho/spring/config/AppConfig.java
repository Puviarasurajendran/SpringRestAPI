package com.zoho.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.zoho.spring.dao.CustomersDAO;
import com.zoho.spring.dao.CustomersInterface;
import com.zoho.spring.handler.v1.CustomerHandler;
import com.zoho.spring.proxy.VersionProxyHandlerUtils;
import com.zoho.spring.versionhandler.ApiVersionInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.zoho.spring.controller", "com.zoho.spring.errorhandler"})
public class AppConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new ApiVersionInterceptor()).addPathPatterns("/api/**");
    }

    @Bean
    public CustomersDAO customersDAO(){
        return new CustomersDAO();
    }

    @Bean
    public CustomersInterface customersInterface(){
        return new CustomersDAO();
    }

    @Bean
    public VersionProxyHandlerUtils versionProxyHandlerUtils(){
        return new VersionProxyHandlerUtils(customersInterface());
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(){
        return new MethodValidationPostProcessor();
    }

    @Bean
    public LocalValidatorFactoryBean validator(){
        return new LocalValidatorFactoryBean();
    }

     @Bean
     public CustomerHandler customerHandlerv1() {
     return new com.zoho.spring.handler.v1.CustomerHandler();
     }

     @Bean
     public CustomerHandler customerHandlerv2() {
     return new com.zoho.spring.handler.v2.CustomerHandler();
     }
     
     @Bean
     public CommonsMultipartResolver multipartResolver() {
         CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
         multipartResolver.setMaxUploadSize(2097152); // 2MB
         return multipartResolver;
     }

     @Bean
     public InternalResourceViewResolver viewResolver() {
         InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
         viewResolver.setPrefix("/WEB-INF/views/");
         viewResolver.setSuffix(".jsp");
         return viewResolver;
     }

}
