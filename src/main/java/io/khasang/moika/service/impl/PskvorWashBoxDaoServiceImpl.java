package io.khasang.moika.service.impl;


import io.khasang.moika.dao.WashBoxDao;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.service.PskvorWashBoxDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("PskvorWashBoxDaoServiceImpl")
@Transactional
public class PskvorWashBoxDaoServiceImpl implements PskvorWashBoxDaoService {
    @Autowired
    private WashBoxDao washBoxDao;

    public PskvorWashBoxDaoServiceImpl() {
    }

    @Override
    public void addWashBox(WashBox washBox) {
        washBoxDao.addWashBox(washBox);
    }

    @Override
    public void updateWashBox(WashBox washBox) {
        washBoxDao.updateWashBox(washBox);
    }

    @Override
    public void deleteWashBox(WashBox washBox) {
        washBoxDao.deleteWashBox(washBox);
    }

    @Override
    public WashBox getWashBoxByID(int id) {
        return washBoxDao.getWashBoxById(id);
    }

    @Override
    public WashBox getWashBox(int idFclt, String name) {
      return washBoxDao.getWashBox(idFclt, name);    }

    @Override
    public List<WashBox> getWashBoxesByType(int boxType) {
        return washBoxDao.getWashBoxesByType(boxType);
    }

    @Override
    public List<WashBox> getWashBoxesByStatus(int boxStatus) {
        return washBoxDao.getWashBoxesByStatus(boxStatus);
    }

    @Override
    public List<WashBox> getAllWashBoxes() {
        return washBoxDao.getAllWashBoxes();
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFclt) {
        return washBoxDao.getWashBoxesOnFacility(idFclt);
    }

}