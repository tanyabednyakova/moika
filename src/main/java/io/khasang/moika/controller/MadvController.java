package io.khasang.moika.controller;

import io.khasang.moika.entity.Car;
import io.khasang.moika.model.MadvDataAcces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MadvController {
    @Autowired
    MadvDataAcces madvDataAcces;


    @RequestMapping("/index1")
    public String hello() {
        return "index1";
    }

    @RequestMapping("/madv/insert")
    public String create(Model model) {
        Car car = new Car();
        car.setId(3l);
        car.setCarModel("Седан");
        car.setCarNumber("");
        car.setCarType("");
        car.setDescription("");
        model.addAttribute("insert", madvDataAcces.insert(car));
        return "insert";
    }
}
