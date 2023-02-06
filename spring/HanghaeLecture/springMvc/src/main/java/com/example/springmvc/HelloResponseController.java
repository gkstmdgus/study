package com.example.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/response")    // 전체 url을 /response로 시작하게끔 설정
public class HelloResponseController {

    private static long visitCount = 0;

    @GetMapping("/html/redirect")
    public String htmlFile() {
        return "hello";
    }    // reddirect는 뭘까?

    @GetMapping("/html/templates")
    public String htmlTemplates() {
        return "hello";
    }

    @ResponseBody
    @GetMapping("/body/html")
    public String helloStringHTML() {
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset=\"UTF-8\"><title>By @ResponseBody</title></head>" +
                "<body> Hello, 정적 웹 페이지!!</body>" +
                "</html>";
    }

    @GetMapping("/html/dynamic")
    public String helloHtmlFile(Model model) {
        visitCount++;
        model.addAttribute("visits", visitCount);
        return "hello-visit";
    }

    @ResponseBody       // 비동기 처리를 위한 애너테이션
    @GetMapping("/json/string")
    public String helloStringJson() {
        return "{\"name\":\"르세라핌\",\"age\":20}";
    }

    @ResponseBody
    @GetMapping("/json/class")
    public Star helloJson() {
        return new Star("르세라핌", 20);
    }
}