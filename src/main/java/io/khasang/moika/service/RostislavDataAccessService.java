package io.khasang.moika.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface RostislavDataAccessService {

    /**
     * Получить сведения обо всех машинах.
     * @return
     */
    public List<Map<String, Object>> getAllCars();

    /**
     * Получить сведения машинах определённой модели.
     * @param carModel тип машины
     * @return
     */
    public List<Map<String, Object>> getCars(String carModel);

    /**
     * Получить дурацкий никому не нужный результирующий набор из декартова произведения всех машин на все.
     * @return
     */
    List<Map<String, Object>> getCarToCarCrossData();
}
