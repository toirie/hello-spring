package jp.co.chronos.hello.controller;

import jp.co.chronos.hello.domain.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hello")
    public void hello(Model model) {
        model.addAttribute("message", helloService.getMessage());
    }
}
