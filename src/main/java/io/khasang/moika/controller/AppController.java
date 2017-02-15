package io.khasang.moika.controller;

import io.khasang.moika.dao.SomeRowDAO;
import io.khasang.moika.entity.SomeRow;
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

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private ApplicationContext appContext;
    @Autowired
    private SomeTest someTest;
    @Autowired
    private CreateTable createTable;
    @RequestMapping("/")
    public String hello() {
        return "index";
    }

    @RequestMapping("/db/create")
    public String create(Model model){
        model.addAttribute("create",createTable.createTableStatus());
        return "create";
    }
    @RequestMapping("/hello/{name}")
    public ModelAndView encode(@PathVariable("name") String name){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("encode",new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }
    @RequestMapping("/test/entity")
    public ModelAndView testEntity(){
        SomeRowDAO someRowDAO = appContext.getBean(SomeRowDAO.class);
        long rows = someRowDAO.countRow();
        System.out.println("rows: "+rows);
        SomeRow someRow;
        if (rows<1){
            someRow = new SomeRow("FirstRow","I am first row creating by Hibernate =)");
            someRowDAO.save(someRow);
        }
        someRow = someRowDAO.get("FirstRow");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("entity");
        modelAndView.addObject("someRow",someRow);
        return modelAndView;
    }
}
