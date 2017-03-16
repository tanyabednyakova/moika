package io.khasang.moika.model.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.model.OrlovDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class OrlovDataAccessImpl implements OrlovDataAccess {
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_SELECT = "select id, cartype, carnumber, carmodel, description from cars";
    private static final String SQL_SELECT_BY_ID = SQL_SELECT + " where id = ?";

    private RowMapper<Car> rowMapper = (ResultSet rs, int rowNum) -> {
        Car car = new Car();
        car.setId(rs.getInt("id"));
//        car.setCarType(rs.getString("cartype"));
        car.setCarNumber(rs.getString("carnumber"));
        car.setCarModel(rs.getString("carmodel"));
        car.setDescription(rs.getString("description"));
        return car;
    };

    @Autowired
    public OrlovDataAccessImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> select() {
        List<Car> result = jdbcTemplate.query(SQL_SELECT,
                rowMapper);
        return result;
    }

    @Override
    public Car selectById(int id) {
        List<Car> result = jdbcTemplate.query(SQL_SELECT_BY_ID,
                rowMapper,
            id);
        return result.get(0);
    }
}
