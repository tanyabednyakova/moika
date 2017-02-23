package io.khasang.moika.dao;


import io.khasang.moika.entity.WashBox;

import java.util.List;

public interface WashBoxDao {
    void createWashBox(WashBox washBox);
    void updateWashBox(WashBox washBox);
    void deleteWashBox(WashBox washBox);
    WashBox getWashBox(int id);
    WashBox getWashBox(int idFacility, String name );
    List<WashBox> getWashBoxesOnFacility(int idFacility);
    List<WashBox> getAllWashBoxes();
}
