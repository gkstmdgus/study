package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {
    @Test
    void helloControllerTest() {
        HelloController helloController = new HelloController(name -> name);

        String ret = helloController.hello("Test");

        Assertions.assertThat(ret).isEqualTo("Test");
    }

    @Test
    void failHelloController() {
        HelloController helloController = new HelloController(name -> name);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello(null);
        }).isInstanceOf(IllegalStateException.class);

        Assertions.assertThatThrownBy(() -> {
            helloController.hello("");
        }).isInstanceOf(IllegalStateException.class);
    }
}
