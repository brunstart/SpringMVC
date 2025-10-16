package org.example.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @GetMapping("/hi")      // url과 연결, Handler Mapping이 @Controller의 @GetMapping을 찾아서 url을 저장함, 2번과정
                                // hi = HelloController의 GetMapping이라고 저장, 2번과정
    public String hello() {
        // return "hello";      // templates에서 hello를 찾음
        return "welcome";       // templates에서 welcome을 찾음, 5번과정 : view이름을 반환함
    }

    @GetMapping("/hello")
    public String hello2() {
        return "hello";
    }
}
