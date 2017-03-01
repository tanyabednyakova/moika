package io.khasang.moika.controller;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CarController {
    @Autowired
    CarService carService;

    @Autowired
    CarDao carDao;


}
