package io.khasang.moika.dao;

import io.khasang.moika.entity.MoikaService;

import java.util.List;

public interface MoikaServiceDao {
    void addMoikaServiceDao(MoikaService MoikaService);
    void updateMoikaServiceDao(MoikaService MoikaService);
    void deleteMoikaServiceDao(MoikaService MoikaService);
    MoikaService getMoikaService(int id);
    List<MoikaService> getMoikaServiceComplex(int id);
    List<MoikaService> getAllMoikaService();
    List<MoikaService> getActualMoikaService();
}
