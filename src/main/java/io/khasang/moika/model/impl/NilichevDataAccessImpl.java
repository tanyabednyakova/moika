package io.khasang.moika.model.impl;

import io.khasang.moika.model.NilichevDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NilichevDataAccessImpl implements NilichevDataAccess {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void deleteCar(long id) {
        String sql = "DELETE FROM cars WHERE id = " + id;
        jdbcTemplate.update(sql);
    }
}
