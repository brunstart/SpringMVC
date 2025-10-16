package org.example.springmvc.controller;

import org.example.springmvc.domain.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {

    @GetMapping("/form")    // 리퀘스트 매핑으로 /item을 해놨기 때문에 앞에 /item을 붙일 필요가 없음
    public String form() {
        System.out.println("form called");
        return "item_form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Item item) {  // @ModelAttribute는 값을 꺼내서 담는것까지 Springframework가 알아서 해줌 --> 자동으로 하려면 지켜야 할 약속(규칙)이 있다
    // public String add(@RequestParam("name2") String name, @RequestParam("price2") int price) {  // 값을 직접 받는 경우 @RequestParam 사용
        System.out.println(item.getName());     // Item에서 설정된 값과 html에서 쓰는 필드의 이름이 다를 경우 오류 발생
        System.out.println(item.getPrice());

        // System.out.println(name);        // 값을 직접 쓰는경우
        // System.out.println(price);

        return "redirect:/item/form";
        // return "item_form";  // /item/form 으로 이동하는게 아니라 url은 item/add인 채로 item_form.html을 보여주기만 함
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("welcomeMessage", "안녕하세요, 반갑습니다.");

        // 나중에는 서비스를 통해서 얻어오게 될 것
        List<Item> items = Arrays.asList(
                new Item("사과", 2000),
                new Item("바나나", 1000),
                new Item("망고", 3000),
                new Item("수박", 30000)
        );

        model.addAttribute("items", items);

        return "item_list";
    }
}
