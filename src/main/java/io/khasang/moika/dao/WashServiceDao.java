package io.khasang.moika.dao;

public interface WashServiceDao {
    void addWashService(WashServiceDao washFacility);
    void updateWashService(WashServiceDao washFacility);
    void deleteWashService(WashServiceDao washFacility);
    WashServiceDao getWashService(int id);
}
