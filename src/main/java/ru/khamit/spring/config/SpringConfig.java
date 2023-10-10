package ru.khamit.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.sql.DriverManager;

@Configuration
@ComponentScan("ru.khamit.spring")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
    public static final String URL="jdbc:postgresql://localhost:5432/spring_practice";
    public static final String USER="postgres";
    public static final String PASSWORD="root";
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
        dataSource.setDriverClassName("org.postgresql.Driver");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}

//    <?xml version="1.0" encoding="UTF-8"?>
//<beans xmlns="http://www.springframework.org/schema/beans"
//        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//        xmlns:context="http://www.springframework.org/schema/context"
//        xmlns:mvc="http://www.springframework.org/schema/mvc"
//        xsi:schemaLocation="
//        http://www.springframework.org/schema/beans
//        http://www.springframework.org/schema/beans/spring-beans.xsd
//        http://www.springframework.org/schema/context
//        http://www.springframework.org/schema/context/spring-context.xsd
//        http://www.springframework.org/schema/mvc
//        http://www.springframework.org/schema/mvc/spring-mvc.xsd">
//
//<context:component-scan base-package="ru.khamit.spring"/>
//
//<mvc:annotation-driven/>
//
//<bean id="templateResolver" class="org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver">
//<property name="prefix" value="/WEB-INF/views/"/>
//<property name="suffix" value=".html"/>
//</bean>
//
//<bean id="templateEngine" class="org.thymeleaf.spring6.SpringTemplateEngine">
//<property name="templateResolver" ref="templateResolver"/>
//<property name="enableSpringELCompiler" value="true"/>
//</bean>
//
//<bean class="org.thymeleaf.spring6.view.ThymeleafViewResolver">
//<property name="templateEngine" ref="templateEngine"/>
//<property name="order" value="1"/>
//<property name="viewNames" value="*"/>
//</bean>
//</beans>
