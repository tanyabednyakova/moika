package io.khasang.moika.controller;

import io.khasang.moika.entity.Test;
import io.khasang.moika.service.PskvorTestDaoService;
import org.springframework.stereotype.Controller;
import io.khasang.moika.model.CreateTable;
import io.khasang.moika.service.PskvorDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PsAppController {
    @Autowired
    CreateTable createTable;
    @Autowired
    PskvorDataAccessService pskvorDataAccessService;
    @Autowired
    PskvorTestDaoService pskvorTestDaoService;

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

    @RequestMapping(value = "/ps-backup", method = RequestMethod.GET)
    public String backupData(Model model) {
        String res = pskvorDataAccessService.backupData("C:\\Program Files (x86)\\pgAdmin 4\\v1\r\\untime\\", "c:\\backup\\carwash.sql", false);
        model.addAttribute("selecttable", res);
        return "ps-queries"; //имя jsp
    }

    @RequestMapping(value = "/testlist", method = RequestMethod.GET)
    public String getTestList(Model model) {
        model.addAttribute("CrudType", "Select *");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        List<Test> testList = pskvorTestDaoService.getAllTests();
        model.addAttribute("testlist", testList);
        model.addAttribute("nrows", testList.size() + " rows affected");
        return "ps-dao-test";
    }

    @RequestMapping(value = "test/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    //@ResponseBody
    public Object addTest(@RequestBody Test test, Model model) {
        model.addAttribute("CrudType", "Insert");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        pskvorTestDaoService.addTest(test);
        List<Test> testList = new ArrayList<>();
        testList.add(test);
        model.addAttribute("testlist", testList);
        model.addAttribute("nrows", "ID: "+test.getId() + " added");
        return "ps-dao-test";
    }

    @RequestMapping(value = "test/update", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
   // @ResponseBody
    public Object updateTest(@RequestBody Test test, Model model) {
        model.addAttribute("CrudType", "Update");
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        pskvorTestDaoService.updateTest(test);
        List<Test> testList = new ArrayList<>();
        testList.add(test);
        model.addAttribute("testlist", testList);
        model.addAttribute("nrows", "ID: "+test.getId() + " updated");
        return "ps-dao-test";
    }

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public String getTest(@PathVariable(value = "id") String inputId, HttpServletResponse response, Model model) {
          Test test = pskvorTestDaoService.getTestByID(Integer.valueOf(inputId));
          model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
          model.addAttribute("CrudType", "Select  id = " + inputId);
          if (test != null) {
              List<Test> testList = new ArrayList<>();
              testList.add(test);
              model.addAttribute("testlist", testList);
          }
          else
          {model.addAttribute("nrows", inputId + "doesn`t exists ");}
        return "ps-dao-test";
    }

    @RequestMapping(value = "/test/delete/{id}", method = RequestMethod.POST)
    //@ResponseBody
    public String deleteTest(@PathVariable(value = "id") String inputId, HttpServletResponse response, Model model) {
        model.addAttribute("currentTime", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
        model.addAttribute("CrudType", "Delete id = " + inputId);
        Test test = pskvorTestDaoService.getTestByID(Integer.valueOf(inputId));
        if (test != null) {
            int id = test.getId();
            pskvorTestDaoService.deleteTest(test);
            model.addAttribute("nrows", id + " deleted");
        }
        else
        {model.addAttribute("nrows", inputId + "doesn`t exists ");}
        return "ps-dao-test";
    }
}
