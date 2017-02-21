package io.khasang.moika.controller;

import org.springframework.stereotype.Controller;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.PskvorDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PsAppController {
    @Autowired
    CreateTable createTable;
    @Autowired
    PskvorDataAccessService pskvorDataAccessService;

    @RequestMapping(value = "/ps-queries", method = RequestMethod.GET)
    public String SelectAll(Model model) {
        String res = pskvorDataAccessService.select("public.box_status");
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-queries2", method = RequestMethod.GET)
    public String SelectWhere(Model model) {
        String res = pskvorDataAccessService.select("public.box_status", " id_status = ?", new Object[]{2});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-create2", method = RequestMethod.GET)
    public String createTable(Model model) {
        String res = pskvorDataAccessService.createData("public.test", new String[]{"id", "Name1", "Name2"},
                new String[]{"serial", "varchar (20)", "varchar(255)"}, " id_pkey PRIMARY KEY (id)");
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-change", method = RequestMethod.GET)
    public String updateTable(Model model) {
        String res = pskvorDataAccessService.changeData("public.test", new String[]{"Name1"}, " id = ?", new Object[]{"DDDDD", 1});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-insert", method = RequestMethod.GET)
    public String insertIntoTable(Model model) {
        String res = pskvorDataAccessService.addData("public.test", new String[]{"AAAAAA", "BBBBB"});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-selectjoin", method = RequestMethod.GET)
    public String selectJoin(Model model) {
        List<String> tables = new ArrayList<>();
        tables.add("public.cars");
        tables.add("public.car_types");
        List<String[]> fieldsArr = new ArrayList<>();
        String[] fields = new String[]{"id_type", "id_type"};
        fieldsArr.add(fields);
        String res = pskvorDataAccessService.joinSelect(tables, fieldsArr, "carnum = ?", new Object[]{"DDDDD"}); // поменять на № машины
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/ps-delete", method = RequestMethod.GET)
    public String deleteData(Model model) {
        String res = pskvorDataAccessService.deleteData("public.test", " id = ?", new Object[]{1});
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/backup", method = RequestMethod.GET)
    public String backupData(Model model) {
        String res = pskvorDataAccessService.backupData("C:\\Program Files (x86)\\pgAdmin 4\\v1\r\\untime\\","c:\\backup\\carwash.sql", false);
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }
}
