package io.khasang.moika.controller;

import io.khasang.moika.dao.CompanyDao;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    CreateTable createTable;
    @Autowired
    RostislavDataAccessService rostislavDataAccessService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyDao companyDao;

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

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }


    @RequestMapping("/rostislav/listAllCars")
    public String listAllCars(Model model) {
        model.addAttribute("name", "Хороший человек");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        model.addAttribute("information", "Вот все тачки, что у нас зарегистрированы: " + rostislavDataAccessService.getAllCars().toString());
        return "index";
    }

    @RequestMapping("/rostislav/listCarsOfType")
    public String listCarsOfType(Model model) {
        model.addAttribute("name", "Хороший человек");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        model.addAttribute("information", "Вот все тачки типа LADA, что у нас зарегистрированы: "
                + rostislavDataAccessService.getCars("lada").toString());
        return "index";
    }

    @RequestMapping(value = "/company/add/{id}", method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company addCompany(@RequestBody Company company, @PathVariable("id") String id) {
        company.setAmount(BigDecimal.valueOf(Long.parseLong(id)));
        companyService.addCompany(company);
        return company;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String getCompanyList(Model model) {
        model.addAttribute("companies", companyService.getCompanyGazpromList());
        return "companies";
    }

    @RequestMapping(value = "company/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateCompany(@RequestBody Company company) {
//        companyService.updateCompany(company);
        return company;
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCompany(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
//        companyService.deleteCompany(company);
        return "redirect:ya.ru";
    }

    @RequestMapping("/restHql")
    public String testHql() {
        List<Company> companyList = companyDao.getCompanyHqlList();
        return "redirect:yandex.ru";
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company company(@PathVariable(value = "id") String id){
        return companyService.getCompanyById(Long.parseLong(id));
    }
}
