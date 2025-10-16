package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.example.springmvc.domain.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class WelcomeController {
    // Logger log = LoggerFactory.getLogger(WelcomeController.class);

    // 리플렉션 기술이 있어서 어노테이션을 쓰면 상속 받은것처럼 동작함
    @GetMapping("/")        // WelcomeController
    public String index(/*HttpServletRequest req*/ @RequestParam("message") String msg, @RequestParam("name") String hmm, Model model) {

        log.info("로그 출력 test SLF4J");
        // System.out.println(req.getRequestURI());
        // System.out.println(req.getParameter("message"));
        // System.out.println(msg);

        model.addAttribute("message", msg); // model에게 message라는 이름으로 msg를 맡겨놓음
        model.addAttribute("name", hmm);

        return "index";
    }

    @GetMapping("/index")
    public String index2(@RequestParam(name = "name", required = false, defaultValue = "guest") String name,
                         @RequestParam(name = "message", required = false, defaultValue = "hi") String msg,
                         Model model) {
        model.addAttribute("name", name);
        model.addAttribute("message", msg);
        return "index";
    }

    // ModelAndView
    @GetMapping("/index2")
    public ModelAndView index3(@RequestParam(name = "name", required = false, defaultValue = "guest") String name,
                               @RequestParam(name = "message", required = false, defaultValue = "hi") String msg){
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", name);
        mv.addObject("message", msg);
        return mv;
    }

    // https://v.daum.net/v/20251016105541847
    // url을 통해서도 값을 받을 수 있다
    @GetMapping("/news/{newsid}")
    public String news(@PathVariable("newsid") String nid) {
        System.out.println(nid);
        return "welcome";
    }

}
