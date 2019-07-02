package com.popova.fix.jobtest.config;

import com.popova.fix.jobtest.servlet.KnightServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Arrays;

@Configuration
public class WebConfig {

    @Value("${spring.mvc.view.prefix}")
    private String prefix;
    @Value("${spring.mvc.view.suffix}")
    private String suffix;
    private AutowireCapableBeanFactory beanFactory;

    @Bean
    public ViewResolver setJspViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix(prefix);
        viewResolver.setSuffix(suffix);
        return viewResolver;
    }

    @Autowired
    public void setBeanFactory(AutowireCapableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Bean
    public ServletRegistrationBean myServletRegistration() {
        ServletRegistrationBean srb = new ServletRegistrationBean();
        final KnightServlet servlet = new KnightServlet();
        beanFactory.autowireBean(servlet);
        srb.setServlet(servlet);
        srb.setUrlMappings(Arrays.asList("/hourse/servlet/count"));
        srb.setLoadOnStartup(1);
        return srb;
    }
}
