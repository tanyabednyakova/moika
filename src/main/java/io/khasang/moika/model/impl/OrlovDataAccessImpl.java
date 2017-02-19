package io.khasang.moika.model.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.model.OrlovDataAccess;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrlovDataAccessImpl implements OrlovDataAccess {
    private JdbcTemplate jdbcTemplate;
    private static final String SQL_STRING = "select id, car_type, car_number, car_model, description from cars";

    public List<Car> select() {
        List<Car> result = jdbcTemplate.query(SQL_STRING,
                new RowMapper<Car>() {
                    public Car mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Car car = new Car();
                        car.setId(rs.getInt("id"));
                        car.setCarType(rs.getString("car_type"));
                        car.setCarNumber(rs.getString("car_number"));
                        car.setCarModel(rs.getString("car_model"));
                        car.setDescription(rs.getString("description"));
                        return car;
                    }
                });
        return result;
    }
}
