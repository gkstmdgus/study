package com.example.springmvc;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController     // @Controller + @ResponseBody ?
@RequestMapping("/rest")
public class HelloRestController {

    @GetMapping("/json/string")
    public String helloHtmlString() {
        return "<html><body>Hello @ResponseBody</body></html>";
    }

    @GetMapping("/json/list")
    public List<String> helloJson() {
        List<String> words = Arrays.asList("Hello","Controller","And","JSON");
        return words;
    }
}