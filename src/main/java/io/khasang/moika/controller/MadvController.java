package io.khasang.moika.controller;

import io.khasang.moika.model.MadvDataAcces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MadvController {
    @Autowired
    MadvDataAcces madvDataAcces;


    @RequestMapping("/index1")
    public String hello() {
        return "index1";
    }

    @RequestMapping("/madv/truncate")
    public String create(Model model) {
        model.addAttribute("truncate", madvDataAcces.truncate("cars"));
        return "truncate";
    }
}
