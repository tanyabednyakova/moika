package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.Client;
import io.khasang.moika.util.BindingResultToMapParser;
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

@Controller
@RequestMapping("/client")
public class ClientController {
//    @Autowired
//    private ClientDao clientDAO;
    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private CarValidator carValidator;

    @RequestMapping(method =  RequestMethod.GET)
    public String getClient(){
        return "client";
    }

//    @RequestMapping("/list")
//    public String getListClients(Model model){
//        try {
//            model.addAttribute("clients",clientDAO.getAllEntities());
//            model.addAttribute("contClientId",clientDAO.getEntityById(2));
//        } catch (MoikaDaoException e) {
//            e.printStackTrace();
//        }
//        return "clientlist";
//    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addClient(@RequestBody Client client, BindingResult result){
        clientValidator.validate(client,result);
        if(result.hasErrors()){
            return BindingResultToMapParser.getMap(result);
        }else{
            //TODO
            //clientDAO.addClient(client);
        }
        return BindingResultToMapParser.getSuccess("success");
    }

}
