package io.khasang.moika.controller;

import io.khasang.moika.dao.ClientDAO;
import io.khasang.moika.entity.Client;
import io.khasang.moika.parser.BindingResultToMapParser;
import io.khasang.moika.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientDAO clientDAO;
    @Autowired
    private ClientValidator clientValidator;

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
        if(result.hasErrors()){
            return result.getFieldErrors();
            //result.getFieldErrors().forEach((error)-> msg.put(error.getField(),error.getCode()));
        }else{
            //TODO
            //clientDAO.addClient(client);
        }
        return BindingResultToMapParser.getSuccess("success");
    }

}
