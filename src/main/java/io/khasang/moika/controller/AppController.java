package io.khasang.moika.controller;

import io.khasang.moika.sometest.SomeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    private SomeTest someTest;
    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", "Hello spring!!!");
        model.addAttribute("acName",someTest.getStr());

        return "index";
    }
}
