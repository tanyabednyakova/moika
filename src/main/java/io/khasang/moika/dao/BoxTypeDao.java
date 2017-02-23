package io.khasang.moika.dao;

import io.khasang.moika.entity.BoxType;

import java.util.List;

public interface BoxTypeDao {
    void addBoxType(BoxType boxType);
    void updateBoxType(BoxType boxType);
    void deleteBoxType(BoxType boxType);
    BoxType getBoxTypeById(int id);
    BoxType getBoxTypeByCode(String code);
    List<BoxType> getAllBoxTypes();
}
