package io.khasang.moika.controller;

import io.khasang.moika.dao.ClientDAO;
import io.khasang.moika.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientDAO clientDAO;

    @RequestMapping("/list")
    public String getListClients(Model model){
        model.addAttribute("clients",clientDAO.getAllClients());
        model.addAttribute("contClientId",clientDAO.containClientById(2L));
        return "client";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addClient(@RequestBody Client client){
        clientDAO.addClient(client);
        return client;
    }
}
