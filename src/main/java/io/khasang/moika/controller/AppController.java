package io.khasang.moika.controller;

import io.khasang.moika.model.CreateTable;
import io.khasang.moika.sometest.SomeTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private SomeTest someTest;
    @Autowired
    private CreateTable createTable;
    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("hello", "Hello spring!!!");
        model.addAttribute("someStr",someTest.getStr());
        model.addAttribute("beansList",appContext.getBeanDefinitionNames());
        return "index";
    }

    @RequestMapping("/db/create")
    public String create(Model model){
        model.addAttribute("create",createTable.create());
        return "create";
    }
    @RequestMapping("/hello/{name}")
    public ModelAndView encode(@PathVariable("name") String name){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("encode",new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }
}
