package io.khasang.moika.model.impl;

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
    public String truncate(String table) {
        try {
            jdbcTemplate.execute("TRUNCATE "+table);
            return "Таблица " + table+ " успешно очищена";
        } catch (DataAccessException e) {
           return "Ошибка при очистке теблицы"+ table+ ": "+e;
        }
    }
}

