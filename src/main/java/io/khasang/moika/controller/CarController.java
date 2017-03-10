package io.khasang.moika.controller;

import io.khasang.moika.entity.Car;
import io.khasang.moika.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер интерфейсов автомобиля
 *
 * @author Nikolay Ilichev, Lyubarev Aleksandr
 * @since 2017-03-01
 */
@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @RequestMapping(value = "car/add",
            method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Car addCar(@RequestBody Car car){
        carService.addCar(car);
        return car;
    }

    @RequestMapping(value = "/car/{id}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Car getCarById(@PathVariable("id") String id){
        return carService.getCarById(Long.parseLong(id));
    }

    //TODO: реализовать
    @RequestMapping(value = "/car/number/{number}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String getCarByNumber(Model model, @PathVariable("number") String carNumber){
        model.addAttribute("cars", carService.getCarByNumber(carNumber));
        return "cars";
    }

    //TODO: реализовать
    @RequestMapping(value = "/car/model/{model}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String getCarByModel(Model model, @PathVariable("model") String carModel){
        model.addAttribute("cars", carService.getCarByNumber(carModel));
        return "cars";
    }

    //TODO: реализовать
    @RequestMapping(value = "/car/type/{type}",
            method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public String getCarByType(Model model, @PathVariable("type") String carType) {
        model.addAttribute("cars", carService.getCarByNumber(carType));
        return "cars";
    }

    @RequestMapping(value = "/car", method = RequestMethod.GET)
    public String getCarList(Model model){
        model.addAttribute("cars", carService.getCarList());
        return "cars";
    }

    @RequestMapping(value = "/car/update",
            method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Car updateCar(@RequestBody Car car){
        carService.updateCar(car);
        return car;
    }

    @RequestMapping(value = "/car/delete/{id}",
            method = RequestMethod.DELETE, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String deleteCar(@PathVariable("id") String id){
        carService.deleteCar(Long.parseLong(id));
        return "redirect:/car";
    }


}
