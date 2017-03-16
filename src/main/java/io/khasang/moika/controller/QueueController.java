package io.khasang.moika.controller;

import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.ServiceType;
import io.khasang.moika.service.MoikaServiceTypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("queue/")
public class QueueController {
    @Autowired
    MoikaServiceTypesService serviceTypeService;

    @RequestMapping("show/")
    public String create(Model model) {
        try {
            List<ServiceType> typeList = new ArrayList<>();
            typeList = serviceTypeService.getAllServiceTypes();
            model.addAttribute("serviceType", typeList);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        return "queue";
    }
}


