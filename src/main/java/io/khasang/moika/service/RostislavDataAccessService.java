package io.khasang.moika.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface RostislavDataAccessService {

    /**
     * Получить сведения обо всех машинах.
     *
     * @return
     */
    public List<Map<String, Object>> getAllCars();

    /**
     * <<<<<<< HEAD
     * Получить сведения машинах определённого типа.
     *
     * @param carType тип машины
     * @return
     */
    List<Map<String, Object>> getCars(String carType);

    /**
     * Получить сведения машинах определённой модели.
     *
     * @param carModel тип машины
     * @return
     */

    public List<Map<String, Object>> getCarToCarCrossData();
}
