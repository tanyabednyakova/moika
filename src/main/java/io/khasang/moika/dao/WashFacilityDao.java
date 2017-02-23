package io.khasang.moika.dao;

import io.khasang.moika.entity.WashBox;
import io.khasang.moika.entity.WashFacility;

import java.util.List;

public interface WashFacilityDao {
    void createWashFacility(WashFacility washFacility);
    void updateWashFacility(WashFacility washFacility);
    void deleteWashFacility(WashFacility washFacility);
    WashFacility getWashFacility(int id);
    WashFacility getWashFacility(String name );
    List<WashFacility> getWashFacilitiesOnNet(int idNet);
    List<WashFacility> getAllWashFacilities();
    List<WashBox> getWashBoxesOnFacility(WashFacility washFacility);
    List<WashBox> getWashBoxesOnFacility(int idFacility);
}
