package com.example.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {
    public static void run(Class<?> application, String[] args) {
        // 스프링 컨테이너 만들기
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
            @Override
            protected void onRefresh() {
                super.onRefresh();

                // Spring Container에서 받아오기
                ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
                DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
                // 서블릿 생성
                WebServer webServer = serverFactory.getWebServer(servletContext -> {
                    servletContext.addServlet("dispatcherServlet",dispatcherServlet
                    ).addMapping("/*");    // 모든 url을 받는다.
                });
                // 웹 서버 가동
                webServer.start();
            }
        };
        applicationContext.register(application);
        applicationContext.refresh();
    }
}
