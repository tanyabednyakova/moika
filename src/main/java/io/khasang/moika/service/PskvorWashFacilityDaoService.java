package io.khasang.moika.service;

import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;

import java.util.List;

public interface PskvorWashFacilityDaoService {
    void addWashFacility(WashFacility washFacility);
    void updateWashFacility(WashFacility washFacility);
    void deleteWashFacility(WashFacility washFacility);
    WashFacility getWashFacilityByID(int id);
    List<WashFacility> getAllWashFacilities();
    List<WashFacility> getWashFacilitiesOnNet(int idNet);
    List<WashBox> getWashBoxesOnFacility(int idFacility);
    List<WashBox> getWashBoxesOnFacility(WashFacility washFacility);
}
