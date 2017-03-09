package io.khasang.moika.controller;

import io.khasang.moika.service.NilichevDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NilichevController {
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    NilichevDataAccessService nilichevDataAccessService;


    @RequestMapping(value = "nilichev/delete/{id}", method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String deleteCar(@PathVariable("id") String id){
        nilichevDataAccessService.deleteCar(Long.parseLong(id));
        return "redirect:/cars";
    }

}
