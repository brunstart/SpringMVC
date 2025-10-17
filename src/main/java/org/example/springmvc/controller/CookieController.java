package org.example.springmvc.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CookieController {
    // 상태정보로 유지하고 싶은 값을 받아오는 화면
    @GetMapping("/cookieForm")
    public String cookieForm() {

        return "cookie_Form";
    }

    @GetMapping("/addcookie")
    public String addcookie(@RequestParam("cookieName") String name,
                            @RequestParam("cookieValue") String value,
                            HttpServletResponse response) {
        // 사용자가 보낸 쿠키 이름과 값을 꺼내서 쿠키를 생성
        // 생성된 쿠키를 "반드시" 다시 클라이어언트에 보내야함
        System.out.println("cookie name : " + name);
        System.out.println("cookie value : " + value);

        // 유지할 정보를 쿠키객체로 생성
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24);   // 브라우저가 없어지면 쿠키도 없어지는 설정값 : -1, 기준은 초, 60*60*24 = 하루(24시간)

        response.addCookie(cookie);

        return "redirect:/cookieView";
    }


    @GetMapping("/cookieView")
    public String cookieView(HttpServletRequest req, Model model) {
        // 클라이언트가 가지고 있는 모든 쿠키를 화면에 출력
        Cookie[] cookies = req.getCookies();
        List<String> cookieList = new ArrayList<>();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName() + "::::::" + cookie.getValue());
                cookieList.add(cookie.getName() + "::::::" + cookie.getValue());
            }
            System.out.println();
            model.addAttribute("cookieList", cookieList);
        }

        return "cookie_view";
    }

    // 관리자전용페이지가 있다.
    // 이 페이지를 어떻게 관리자에게만 보여주게 할 수 있을까?
    @GetMapping("/adminPage")
    public String adminPage(@CookieValue(name="admin", defaultValue="false") String admin) {    // request한테 cookie name, value 요청 필요없는 어노테이션

        // 관리자인지 확인
        if (!admin.equals("false")) {     // 관리자라면
            return "admin_page";
        }else{
            return "welcome";
        }
    }

    // 쿠키삭제
    // 브라우저(클라이언트)는 같은 이름의 여러 쿠키를 가질 수 없다. 같은 이름의 쿠키가 전송되어오면 기존 값이 갱신된다.
    @GetMapping("/cookieDel")
    public String cookieDel(HttpServletResponse response) {

        Cookie cookie = new Cookie("admin", "");    // name이 중요, 값은 상관없음
        cookie.setMaxAge(0);
        cookie.setPath("/");    // 쿠키 최초 생성 시 설정된 경로와 동일해야함
        response.addCookie(cookie);

        return "redirect:/cookieView";
    }
}
