package io.khasang.moika.model.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.model.MadvDataAcces;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by madv on 19.02.2017.
 */

public class MadvDataAccesImpl implements MadvDataAcces {
    private JdbcTemplate jdbcTemplate;

    public MadvDataAccesImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MadvDataAccesImpl() {
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String insert(Car car) {
        String sql = "INSERT INTO cars " +
                "(id, carmodel, carnumber, cartype, description) VALUES (?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql, new Object[]{
                    car.getId(), car.getCarModel(), car.getCarNumber(), car.getCarType(), car.getDescription()
            });
            return "Строка успешно вставлена";
        } catch (DataAccessException e) {
            return "Ошибка при вставке строки: "+e;
        }


    }
}
