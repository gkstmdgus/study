package com.example.helloboot;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping
@Component
public class HelloController {
    HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(String name) {
        if (name == null || name.trim().length() == 0 ) throw new IllegalStateException();
        return helloService.sayHello(name);
    }
}
