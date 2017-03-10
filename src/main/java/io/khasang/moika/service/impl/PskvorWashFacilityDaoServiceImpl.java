package io.khasang.moika.service.impl;


import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.dao.WashFacilityDao;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("pskvorWashFacilityDaoServiceImpl")
@Transactional
public class PskvorWashFacilityDaoServiceImpl implements PskvorWashFacilityDaoService {
    @Autowired
    private WashFacilityDao washFacilityDao;

    public PskvorWashFacilityDaoServiceImpl() {
    }

    @Override
    public void addWashFacility(WashFacility washFacility) {
        try {
            washFacilityDao.addEntity(washFacility);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateWashFacility(WashFacility washFacility) {
        try {
            washFacilityDao.updateEntity(washFacility);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteWashFacility(WashFacility washFacility) {
        try {
            washFacilityDao.deleteEntity(washFacility);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WashFacility getWashFacilityByID(int id) {
        try {
            return washFacilityDao.getEntityById(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashFacility> getAllWashFacilities() {
        try {
            return washFacilityDao.getAllEntities();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashFacility> getWashFacilitiesOnNet(int idNet) {
        return null;
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(WashFacility washFacility) {
        try {
            return washFacilityDao.getWashBoxesOnFacility(washFacility.getId());
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFclt) {
        try {
            return washFacilityDao.getWashBoxesOnFacility(idFclt);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

}