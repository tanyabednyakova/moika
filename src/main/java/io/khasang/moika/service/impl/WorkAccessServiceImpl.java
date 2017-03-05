package io.khasang.moika.service.impl;

import io.khasang.moika.dao.WorkDao;
import io.khasang.moika.entity.Work;
import io.khasang.moika.service.WorkAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("WorkAccessServiceImpl")
@Transactional
public class WorkAccessServiceImpl implements WorkAccessService {
    @Autowired
    private WorkDao workDao;

    public WorkAccessServiceImpl() {
    }

    @Override
    public void addWork(Work work) {
        workDao.addWork(work);
    }

    @Override
    public void updateWork(Work work) {
        workDao.updateWork(work);
    }

    @Override
    public void deleteWork(Work work) {
        workDao.deleteWork(work);
    }

    @Override
    public Work getWork(long id) {
        return workDao.getWork(id);
    }

    @Override
    public Work getWork(String name) {
        return workDao.getWork(name);
    }

    @Override
    public List<Work> getAllWork() {
        return workDao.getAllWork();
    }
}
