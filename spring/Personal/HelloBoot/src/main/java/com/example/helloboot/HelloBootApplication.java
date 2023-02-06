package com.example.helloboot;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloBootApplication {


    public static void main(String[] args) {
        // 스프링 컨테이너 만들기
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh();
        // 서블릿 컨테이너 띄우기
        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        // 서블릿 생성
        WebServer webServer = serverFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(
                        HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {
                    // 인증, 보안, 다국어, 공통기능 수행 했다고 침
                    if (req.getRequestURI().equals("/hello")   // /hello && GET
                            && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        HelloController helloController = applicationContext.getBean(HelloController.class);
                        String ret = helloController.hello(name);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().print(ret);        // 바디
                    }else {
                        resp.setStatus((HttpStatus.NOT_FOUND.value()));  // 404
                    }
                }
            }).addMapping("/*");    // 모든 url을 받는다.
        });
        // 웹 서버 가동
        webServer.start();
    }
}

