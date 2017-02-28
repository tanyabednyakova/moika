package io.khasang.moika.controller;

import io.khasang.moika.model.TatyanaDataAccessImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TatyanaController {

    @Autowired
    TatyanaDataAccessImp tatyanaDataAccessImp;


    @RequestMapping("/exampleBD")
    public String test(Model model) {
        model.addAttribute("bd", tatyanaDataAccessImp.selectIn());
        return "ExampleBD";
    }
}




