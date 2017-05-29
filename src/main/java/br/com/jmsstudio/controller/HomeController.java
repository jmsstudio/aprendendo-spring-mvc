package br.com.jmsstudio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        System.out.println("Acessando a home");

        return "home";
    }
}
