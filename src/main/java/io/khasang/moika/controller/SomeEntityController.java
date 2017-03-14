package io.khasang.moika.controller;

import io.khasang.moika.dao.SomeEntityDao;
import io.khasang.moika.entity.SomeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/some")
public class SomeEntityController {
    @Autowired
    private SomeEntityDao someEntityDao;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getAllEntity(){
        return someEntityDao.getAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object addEntity(@RequestBody SomeEntity someEntity){
        someEntityDao.create(someEntity);
        return someEntity;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object deleteEntity(@PathVariable("id") long id){
        SomeEntity someEntity = someEntityDao.get(id);
        return someEntityDao.delete(someEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getEntity(@PathVariable("id") long id){
        return someEntityDao.get(id);
    }
}
