package org.example.springmvc.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/session")
@Controller
@SessionAttributes("visitCount")    // 이 어노테이션은 저장되면 세션 스코프로 저장하라는 약속
public class SessionController {

    @ModelAttribute("visitCount")   // 속성을 초기화 해주는 부분, visitCount 속성으로 저장될 때 초기값을 0으로 저장하라는 뜻
    public Integer initVisitCount() {   // int 보다 Integer가 나음
        System.out.println("getVisitCount");
        return 0;
    }

    @GetMapping("/visit")
    // public String visit(@RequestParam(name="visitCount", defaultValue="0") int visitCount, Model model) {        // @SessionAttributes 생략하면 리퀘스트 스코프로 저장돼서 새로고침하면 계속 1임
    public String visit(@ModelAttribute("visitCount") Integer visitCount, Model model) {        // 세션 스코프로 저장이 돼서 새로고침 할 때 마다 count 올라감

        visitCount++;
        model.addAttribute("visitCount", visitCount);   // Session Scope에 저장돼야함

        return "visit";
    }


    // 상태정보로 유지하고 싶은 값을 받아오는 화면
    @GetMapping("/form")
    public String sessionForm() {

        return "session_form";
    }

    @GetMapping("/add")
    public String addSession(@RequestParam("sessionKey") String key,
                            @RequestParam("sessionValue") String value,
                            HttpSession session) {

        System.out.println("session name : " + key);
        System.out.println("session value : " + value);

        // 유지할 정보를 세션에 저장
        // 원래는 Request 객체에 getSession()을 해서 생성함 -> 세션 ID 쿠키랑 비교해서 이미 있는 세션이면 해당 세션 객체 반환,
        //                                                없으면 새로 세션 생성, 세션 ID 발급, Response에 세션 ID가 담긴 쿠키를 담아서 보냄
        session.setAttribute(key, value);
        return "redirect:/session/view";
    }


    @GetMapping("/view")
    public String cookieView(HttpSession session, Model model) {
        // 세션이 가진 모든 값을 출력, 실제로 이러는 경우는 많지 않음
        Map<String, Object> map = new HashMap<>();

        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            Object attribute = session.getAttribute(attributeName);
            map.put(attributeName, attribute);
        }

        model.addAttribute("session", map);
        System.out.println(map.size());

        return "session_view";
    }

    // 관리자전용페이지가 있다.
    // 이 페이지를 어떻게 관리자에게만 보여주게 할 수 있을까?
    @GetMapping("/adminPage")
    public String adminPage(@SessionAttribute(name="admin", required=false) Object admin) {    // request한테 cookie name, value 요청 필요없는 어노테이션
        // 세션에 admin이라는 속성이 있다면, 관리자라고 판단하고 admin_page로 이동

        if (admin != null) {    // key가 admin인 session을 admin이라는 오브젝트에 받는다 -> null이 아니면 admin이다
            return "admin_page";
        }else{
            return "welcome";
        }
    }

    // 세션삭제(세션 전체를 삭제할지 혹은 세션의 속성만 삭제할지 결정)
    @GetMapping("/del")
    public String sessionDel(HttpSession session) {

        // 특정 속성만 삭제
        // session.removeAttribute("admin");

        // 세션 전체를 삭제 (모든 속성들을 제거하고, 세션 무효화)
        session.invalidate();

        return "redirect:/session/view";
    }
}
