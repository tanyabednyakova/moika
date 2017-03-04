package io.khasang.moika.controller;

import io.khasang.moika.entity.Company;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.CompanyService;
import io.khasang.moika.service.RostislavDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AppController {
    @Autowired
    CreateTable createTable;
//    @Autowired
//    RostislavDataAccessService rostislavDataAccessService;
    @Autowired
    CompanyService companyService;

    @RequestMapping("/")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "Car washer") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));

        return "index";
    }

    @RequestMapping("/db/create")
    public String create(Model model) {
        model.addAttribute("create", createTable.createTableStatus());
        return "create";
    }

    @RequestMapping(value = {"/hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @RequestMapping(value = "/company/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return company;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String getCompanyList(Model model){
        model.addAttribute("companies", companyService.getCompanyList());
        return "companies";
    }

    @RequestMapping(value = "/company/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company updateCompany(@RequestBody Company company){
//        companyService.updateCompany(company);
        return company;
    }

    @RequestMapping(value = "/company/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCompany(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
     //   companyService.deleteCompany(inputId);
        return "redirect:ya.ru";
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Company getCompanybyId(@PathVariable(value = "id") String inputId, Model model){
        Company company = companyService.getCompanyById(Integer.valueOf(inputId));
        return company;
    }
}
