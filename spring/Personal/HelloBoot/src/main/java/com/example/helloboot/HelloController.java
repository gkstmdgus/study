package com.example.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

public class HelloController {
    private final HelloService helloService = new SimpleHelloService();

    public String hello(String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
