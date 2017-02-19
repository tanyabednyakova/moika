package io.khasang.moika.controller;

import io.khasang.moika.entity.Company;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.CompanyService;
import io.khasang.moika.service.PskvorDataAccessService;
import io.khasang.moika.service.RostislavDataAccessService;
import io.khasang.moika.service.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Controller
public class AppController {
    @Autowired
    CreateTable createTable;
    @Autowired
    PskvorDataAccessService pskvorDataAccessService;
    @Autowired
    RostislavDataAccessService rostislavDataAccessService;
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

    @RequestMapping(value = {"hello/{name}"}, method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable("name") String name) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("encode");
        modelAndView.addObject("crypt", new BCryptPasswordEncoder().encode(name));
        return modelAndView;
    }

    @RequestMapping(value = "/queries", method = RequestMethod.GET)
    public String SelectAll(Model model) {
        String res = pskvorDataAccessService.select("public.box_status");
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/queries2", method = RequestMethod.GET)
    public String SelectWhere(Model model) {
        String res = pskvorDataAccessService.select("public.box_status", " id_status = ?", new Object[]{2});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/create2", method = RequestMethod.GET)
    public String createTable(Model model) {
        String res = pskvorDataAccessService.createData("public.test", new String[]{"id", "Name1", "Name2"},
                new String[]{"serial", "varchar (20)", "varchar(255)"}, " id_pkey PRIMARY KEY (id)");
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/change", method = RequestMethod.GET)
    public String updateTable(Model model) {
        String res = pskvorDataAccessService.changeData("public.test", new String[]{"Name1"}, " id = ?", new Object[]{"DDDDD", 1});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insertIntoTable(Model model) {
        String res = pskvorDataAccessService.addData("public.test", new String[]{"AAAAAA", "BBBBB"});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/selectjoin", method = RequestMethod.GET)
    public String selectJoin(Model model) {
        String res = pskvorDataAccessService.joinSelect("public.cars", "public.car_types", "id_type", "carnum = ?", new Object[]{"DDDDD"}); // поменять на № машины
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteData(Model model) {
        String res = pskvorDataAccessService.deleteData("public.test", " id = ?", new Object[]{1});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/backup", method = RequestMethod.GET)
    public String backupData(Model model) {
        String res = pskvorDataAccessService.backupData("c:\\backup\\carwash.sql");
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
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

    @RequestMapping(value = "company/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return company;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String getCompanyList(Model model){
        model.addAttribute("companies", companyService.getCompanyGazpromList());
        return "companies";
    }

    @RequestMapping(value = "company/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateCompany(@RequestBody Company company){
//        companyService.updateCompany(company);
        return company;
    }

    @RequestMapping(value = "/deleteMessage/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCompany(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
//        companyService.deleteCompany(company);
        return "redirect:ya.ru";
    }
}
