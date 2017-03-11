package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
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
        try {
            washBoxDao.addEntity(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWashBox(WashBox washBox) {
        try {
            washBoxDao.updateEntity(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWashBox(WashBox washBox) {
        try {
            washBoxDao.deleteEntity(washBox);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WashBox getWashBoxByID(int id) {
        try {
            return washBoxDao.getEntityById(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public WashBox getWashBox(int idFclt, String name) {
        try {
            return washBoxDao.getWashBox(idFclt, name);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesByType(int boxType) {
        try {
            return washBoxDao.getWashBoxesByType(boxType);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesByStatus(int boxStatus) {
        try {
            return washBoxDao.getWashBoxesByStatus(boxStatus);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getAllWashBoxes() {
        try {
            return washBoxDao.getAllEntities();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFclt) {
        try {
            return washBoxDao.getWashBoxesOnFacility(idFclt);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

}