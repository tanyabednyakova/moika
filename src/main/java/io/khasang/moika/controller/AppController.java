package io.khasang.moika.controller;

import io.khasang.moika.entity.Company;
import io.khasang.moika.entity.Queue;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.CompanyService;
import io.khasang.moika.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


@Controller
public class AppController {
    @Autowired
    private CreateTable createTable;

    @Autowired
    CompanyService companyService;

    @Autowired
    QueueService queueService;

    @RequestMapping("/")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "Car washer") String name, Model model) {
        model.addAttribute("name", name);
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

    @RequestMapping(value = "company/add/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company addCompany(@RequestBody Company company, @PathVariable("id") String id) {
        company.setAmount(BigDecimal.valueOf(Long.parseLong(id)));
        companyService.addCompany(company);
        return company;
    }

    @RequestMapping(value = "queue/add/{id}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Queue addQueue(@RequestBody Queue queue, @PathVariable("id") String id) {
        queueService.addQueue(queue);
        return queue;
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public String getCompanyList(Model model) {
        model.addAttribute("companies", companyService.getCompanyGazpromList());
        return "companies";
    }

    @RequestMapping(value = "/company/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
        return company;
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteCompany(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
      companyService.deleteCompany(Long.parseLong(inputId));
      return "redirect:/company";
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Company company(@PathVariable(value = "id") String id){
        return companyService.getCompanyById(Long.parseLong(id));
    }
}
