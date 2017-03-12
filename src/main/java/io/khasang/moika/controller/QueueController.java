package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class QueueController {
    @Autowired
    MoikaServiceTypesService serviceTypeService;

    @RequestMapping("/queue/show")
    public String create(Model model) {
        try {
            model.addAttribute("serviceType", serviceTypeService.getAllServiceTypes());
        }catch (MoikaDaoException e)
        {
            e.printStackTrace();
        }
            return "queue";
        }
    }


