package io.khasang.moika.controller;

import io.khasang.moika.model.TatyanaDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TatyanaController {

    @Autowired
    TatyanaDataAccess tatyanaDataAccessImp;


    @RequestMapping("/exampleBD")
    public String test(Model model) {
        model.addAttribute("bd", tatyanaDataAccessImp.selectIn());
        return "ExampleBD";
    }
}




