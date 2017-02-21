package io.khasang.moika.controller;

import io.khasang.moika.dao.ClientDAO;
import io.khasang.moika.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientDAO clientDAO;

    @RequestMapping("/list")
    public String getListClients(Model model){
        model.addAttribute("clients",clientDAO.getAllClients());
        model.addAttribute("contClientId",clientDAO.containClientById(2L));
        Client client = new Client();
        client.setId(4L);
        model.addAttribute("contClient",clientDAO.containClient(client));
        return "client";
    }
}
