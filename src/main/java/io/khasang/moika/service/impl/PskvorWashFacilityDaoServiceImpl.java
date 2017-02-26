package io.khasang.moika.service.impl;


import io.khasang.moika.dao.WashFacilityDao;
import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;
import io.khasang.moika.service.PskvorWashFacilityDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("PskvorWashFacilityDaoServiceImpl")
@Transactional
public class PskvorWashFacilityDaoServiceImpl implements PskvorWashFacilityDaoService {
    @Autowired
    private WashFacilityDao washFacilityDao;

    public PskvorWashFacilityDaoServiceImpl() {
    }

    @Override
    public void addWashFacility(WashFacility washFacility) {
        washFacilityDao.addWashFacility(washFacility);
    }

    @Override
    public void updateWashFacility(WashFacility washFacility) {
        washFacilityDao.updateWashFacility(washFacility);
    }

    @Override
    public void deleteWashFacility(WashFacility washFacility) {
        washFacilityDao.deleteWashFacility(washFacility);
    }

    @Override
    public WashFacility getWashFacilityByID(int id) {
        return washFacilityDao.getWashFacility(id);
    }

    @Override
    public List<WashFacility> getAllWashFacilities() {
        return washFacilityDao.getAllWashFacilities();
    }

    @Override
    public List<WashFacility> getWashFacilitiesOnNet(int idNet) {
        return null;
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(WashFacility washFacility) {
        return washFacilityDao.getWashBoxesOnFacility(washFacility.getId());
    }

    @Override
    public List<WashBox> getWashBoxesOnFacility(int idFclt) {
        return washFacilityDao.getWashBoxesOnFacility(idFclt);
    }

}