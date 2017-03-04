package io.khasang.moika.controller;

import io.khasang.moika.dao.ClientDAO;
import io.khasang.moika.entity.Client;
import io.khasang.moika.validator.CarValidator;
import io.khasang.moika.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private CarValidator carValidator;

    @RequestMapping(method =  RequestMethod.GET)
    public String getClient(){
        return "client";
    }

    @RequestMapping("/list")
    public String getListClients(Model model){
        model.addAttribute("clients",clientDAO.getAllClients());
        model.addAttribute("contClientId",clientDAO.containClientById(2L));
        return "clientlist";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addClient(@RequestBody Client client, BindingResult result){
        clientValidator.validate(client,result);
        Map<String,String> msg = new HashMap<>();
        if(result.hasErrors()){
            return result.getFieldErrors();
            //result.getFieldErrors().forEach((error)-> msg.put(error.getField(),error.getCode()));
        }else{
            //clientDAO.addClient(client);
            msg.put("success","success");
        }
        return msg;
    }

    /*@RequestMapping(value = "/car", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addCar(@RequestBody Car car, BindingResult result){
        carValidator.validate(car,result);
        Map<String,String> msg = new HashMap<>();
        System.out.println(result.getClass().getName());
        System.out.println("objectName: "+result.getObjectName());
        System.out.println("target: "+result.getTarget().getClass().getName());
        if(result.hasErrors()){
            result.getFieldErrors().forEach((error)-> msg.put(error.getField(),error.getCode()));
        }else{
            msg.put("success","success");
        }
        return msg;
    }*/

}
