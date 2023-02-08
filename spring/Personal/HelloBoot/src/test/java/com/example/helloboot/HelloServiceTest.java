package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloServiceTest {
    @Test
    void simpleHelloService() {
        SimpleHelloService helloService = new SimpleHelloService();

        String test = helloService.sayHello("test");
        assertThat(test).isEqualTo("Hello test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        Assertions.assertThat(decorator.sayHello("test")).isEqualTo("*test*");
    }
}
