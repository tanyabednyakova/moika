package io.khasang.moika.controller;

import io.khasang.moika.entity.Orderm;
import io.khasang.moika.service.OrdermAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
@RequestMapping("/orderm")
public class OrdermController {
    @Autowired
    OrdermAccessService ordermAccessService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String List(Model model) {
        List<Orderm> listOrderm = ordermAccessService.getAllOrderm();
        model.addAttribute("listOrderm", listOrderm);
        model.addAttribute("nrows", "Количество заказов " + listOrderm.size());
        return "orderm-list";
    }
}
