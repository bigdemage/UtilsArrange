package com.main;

import com.main.intercept.HelloIntercept;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ServletComponentScan //使用@ServletComponentScan 注解后，Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码
public class BootMain {

    public static void main(String[] args) {

        SpringApplication.run(BootMain.class,args);
    }


    /**
     * 拦截器需要添加
     */
    @Configuration
    static class HelloWebIntercepter extends WebMvcConfigurerAdapter {
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new HelloIntercept()).addPathPatterns("/hello");
        }
    }
}
