package io.khasang.moika.controller;

import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PsWashFacilityController {
    @Autowired
    CreateTable createTable;

    @Autowired
    PskvorWashFacilityDaoService pskvorWashFacilityDaoService;

    @RequestMapping(value = "/washFacilitylist", method = RequestMethod.GET)
    public String getWashFacilityList(Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashFacility> washFacilityList = pskvorWashFacilityDaoService.getAllWashFacilities();
        model.addAttribute("fcltlist", washFacilityList);
        model.addAttribute("nrows", washFacilityList.size() + " rows affected");
        return "ps-dao-carwashfacility";
    }

    @RequestMapping(value = "/washFacility/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    public Object addWashFacility(@RequestBody WashFacility washFacility, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        pskvorWashFacilityDaoService.addWashFacility(washFacility);
        List<WashFacility> washFacilityList = new ArrayList<>();
        washFacilityList.add(washFacility);
        model.addAttribute("fcltlist", washFacilityList);
        model.addAttribute("nrows", "ID: " + washFacility.getId() + " added");
        return "ps-dao-carwashfacility";
    }

    @RequestMapping(value = "/washFacility/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object updateWashFacility(@RequestBody WashFacility washFacility) {
        pskvorWashFacilityDaoService.updateWashFacility(washFacility);
        return washFacility;
    }

    @RequestMapping(value = "/washFacility/{id}", method = RequestMethod.GET)
    public String getWashFacility(@PathVariable(value = "id") String idFclt, Model model) {
        WashFacility washFacility = pskvorWashFacilityDaoService.getWashFacilityByID(Integer.valueOf(idFclt));
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (washFacility != null) {
            List<WashFacility> washFacilityList = new ArrayList<>();
            washFacilityList.add(washFacility);
            model.addAttribute("fcltlist", washFacilityList);
        } else {model.addAttribute("nrows", "ID: "+idFclt + " doesn`t exists ");}
        return "ps-dao-carwashfacility";
    }

    @RequestMapping(value = "/washFacilitiesonNet/{idNet}", method = RequestMethod.GET)
    public String getWashFacilityesOnFacility(@PathVariable(value = "idNet") String idNet, @PathVariable(value = "boxName") String boxName,
                                         HttpServletResponse response, Model model) {
        List<WashFacility> washFacilityList = pskvorWashFacilityDaoService.getWashFacilitiesOnNet(Integer.valueOf(idNet));
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        if (washFacilityList != null) {
            model.addAttribute("fcltlist", washFacilityList);
        } else {model.addAttribute("nrows", "There are no: facilities on net Id: "+idNet);}
        return "ps-dao-carwashfacility";
    }

    @RequestMapping(value = "/washFacility/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String deleteWashFacility(@PathVariable(value = "id") String inputId, HttpServletResponse response) {
        WashFacility washFacility = pskvorWashFacilityDaoService.getWashFacilityByID(Integer.valueOf(inputId));
        if (washFacility != null) {
            int id = washFacility.getId();
            pskvorWashFacilityDaoService.deleteWashFacility(washFacility);
            return String.valueOf(response.SC_OK);
        } else {return  String.valueOf(response.SC_NOT_FOUND);}
    }

    @RequestMapping(value = "/FacilityBoxes/{idFclt}", method = RequestMethod.GET)
    public String getFacilityBoxes(@PathVariable(value = "idFclt") String idFclt, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<WashBox> washBoxesList = pskvorWashFacilityDaoService.getWashBoxesOnFacility(Integer.valueOf(idFclt));
        model.addAttribute("boxlist", washBoxesList);
        model.addAttribute("nrows", washBoxesList.size() + " rows affected");
        return "ps-dao-carwashbox";
    }
}
