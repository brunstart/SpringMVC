package org.example.springmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.springmvc.domain.User;
import org.example.springmvc.domain.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/abc")
    public String abc(HttpServletRequest request) {
        request.setAttribute("name", "kang");
        return "forward:/user/registe";     // 에러 발생용 오타
    }


    // user 정보 입력 폼
    @GetMapping("/register")
    public String registerForm(Model model, HttpServletRequest request) {
        System.out.println(request.getAttribute("name"));
        model.addAttribute("user", new User()); // validation할 정보
        return "registerForm";
    }

    // 회원가입
    @PostMapping("/register")   // 메소드가 다르면 같은 url 쓸 수 있음
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) { // validation 결과값을 BindingResult에 넣어서 가져옴
        if (bindingResult.hasErrors()) {
            return "registerForm"; // 오류가 발생했다면 여기로 이동
        }

        return "welcome";
    }

    // 로그인

}
