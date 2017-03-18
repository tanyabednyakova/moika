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
    public WashFacility addWashFacility(WashFacility washFacility) {
        WashFacility resFclt = null;
        try {
            resFclt = washFacilityDao.create(washFacility);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        return resFclt;
    }

    @Override
    public WashFacility updateWashFacility(WashFacility washFacility) {
        WashFacility resFclt = null;
        try {
            resFclt = washFacilityDao.update(washFacility);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
        return resFclt;
    }

    @Override
    public void deleteWashFacility(WashFacility washFacility) {
        try {
            washFacilityDao.delete(washFacility);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public WashFacility getWashFacilityByID(int id) {
        try {
            return washFacilityDao.get(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashFacility> getAllWashFacilities() {
        try {
            return washFacilityDao.getAll();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashFacility> getWashFacilitiesOnNet(int idNet) {
        try {
            return washFacilityDao.getWashFacilitiesOnNet(idNet); //getWashBoxesOnFacility(idFclt);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(WashFacility washFacility) {
        try {
            // return washFacilityDao.getWashBoxesOnFacility(washFacility.getId());
            WashFacility fclt = washFacilityDao.get(washFacility.getId());
            return fclt.getWashBoxes();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFclt) {
        try {
            WashFacility fclt = washFacilityDao.get(idFclt);
            return fclt.getWashBoxes(); //getWashBoxesOnFacility(idFclt);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

}