package io.khasang.moika.controller;

import io.khasang.moika.model.CreateTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

/* DRS
http://spring.io/guides/gs/serving-web-content/
In Spring’s approach to building web sites, HTTP requests are handled by a controller.
You can easily identify these requests by the @Controller annotation.
 */
@Controller
public class AppController {
    @Autowired
    CreateTable createTable;
    @Autowired
    Date testDate;

    /**
     * DRS The @RequestMapping annotation ensures that HTTP requests to / are mapped to the hello() method.
     *
     * @param name
     * @param model
     * @return
     */
    @RequestMapping("/")

    public String hello(@RequestParam(value = "name", required = false, defaultValue = "Car washer") String name, Model model) {

        /* DRS
        @RequestParam binds the value of the query String parameter name into the name parameter of the hello() method.
        This query String parameter is not required; if it is absent in the request, the defaultValue of "World" is used.
        The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.
        */

        model.addAttribute("name", name);
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        model.addAttribute("startTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(testDate));

        return "index";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        model.addAttribute("create", createTable.createTableStatus());
        return "create";
    }
}
