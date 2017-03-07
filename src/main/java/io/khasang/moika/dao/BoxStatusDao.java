package io.khasang.moika.dao;

import io.khasang.moika.entity.BoxStatus;

import java.util.List;


public interface BoxStatusDao {
    void addBoxStatus(BoxStatus boxStatus);
    void updateBoxStatus(BoxStatus boxStatus);
    void deleteBoxStatus(BoxStatus boxStatus);
    BoxStatus getBoxStatusById(int id);
    BoxStatus getBoxStatusByCode(String code);
    List<BoxStatus> getAllBoxStatuses();
}
