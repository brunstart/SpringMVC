package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class WelcomeController {
    // Logger log = LoggerFactory.getLogger(WelcomeController.class);

    @GetMapping("/")        // WelcomeController
    public String index(/*HttpServletRequest req*/ @RequestParam("message") String msg) {

        log.info("로그 출력 test SLF4J");
        // System.out.println(req.getRequestURI());
        // System.out.println(req.getParameter("message"));
        System.out.println(msg);

        return "index";
    }
}
