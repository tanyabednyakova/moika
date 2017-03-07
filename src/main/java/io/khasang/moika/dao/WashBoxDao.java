package io.khasang.moika.dao;


import io.khasang.moika.entity.WashBox;

import java.util.List;

public interface WashBoxDao {
    void addWashBox(WashBox washBox);
    void updateWashBox(WashBox washBox);
    void deleteWashBox(WashBox washBox);
    WashBox getWashBoxById(int id);
    WashBox getWashBox(int idFacility, String name );
    List<WashBox> getWashBoxesOnFacility(int idFacility);
    List<WashBox> getAllWashBoxes();
    List<WashBox> getWashBoxesByType(int boxType);
    List<WashBox> getWashBoxesByStatus(int boxStatus);
}
