package io.khasang.moika.controller;

import io.khasang.moika.model.CreateTable;
import io.khasang.moika.sometest.SomeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    private SomeTest someTest;
    @Autowired
    private CreateTable createTable;
    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", "Hello spring!!!");
        model.addAttribute("someStr",someTest.getStr());
        return "index";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("create",createTable.create());
        return "create";
    }
}
