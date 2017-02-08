package io.khasang.moika.controller;

import io.khasang.moika.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @Autowired
    CreateTable createTable;

    @RequestMapping("/")
    public String hello() {
        return "index";
    }

    @RequestMapping("/create")
    public String create(Model model){
        model.addAttribute("create", createTable.createTableStatus());
        return "create";
    }
}
