package ru.khamit.spring.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMVCDispatcherServletInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}
//    <?xml version="1.0" encoding="UTF-8"?>
//<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
//        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
//        xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
//        http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd" id="WebApp_ID" version="4.0">
//
//<display-name>spring-mvc-app1</display-name>
//
//<absolute-ordering/>
//
//<servlet>
//<servlet-name>dispatcher</servlet-name>
//<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
//<init-param>
//<param-name>contextConfigLocation</param-name>
//<param-value>/WEB-INF/applicationContextMVC.xml</param-value>
//</init-param>
//<load-on-startup>1</load-on-startup>
//</servlet>
//
//<servlet-mapping>
//<servlet-name>dispatcher</servlet-name>
//<url-pattern>/</url-pattern>
//</servlet-mapping>
//
//</web-app>

