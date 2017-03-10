package io.khasang.moika.model.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import io.khasang.moika.model.AKovalevDataAccess;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Arrays;

@Component
public class AKovalevDataAccessImpl implements AKovalevDataAccess {
    private JdbcTemplate jdbcTemplate;
    private static long currentCarId;
    private RowMapper<Car> rowMapperCar = (ResultSet rs, int rowNum) -> {
        Car car = new Car();
        car.setId(rs.getLong("id"));
        car.setCarModel(rs.getString("carmodel"));
        car.setCarNumber(rs.getString("carnumber"));
        car.setCarType(rs.getString("cartype"));
        car.setDescription(rs.getString("description"));
        return car;
    };

    public AKovalevDataAccessImpl() {
    }

    @Autowired
    public AKovalevDataAccessImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        Long maxId = this.jdbcTemplate.queryForObject("SELECT MAX(id) FROM cars", Long.class);
        currentCarId = maxId == null ? 1 : ++maxId;
    }

    public static long getCurrentCarId() {
        return currentCarId;
    }

    @Override
    public List<Pair<String, Integer>> withCaseQuery() {
        Map<String, Integer> params = new HashMap<>();
        params.put("%priora%", 5);
        params.put("%lacetti%", 10);
        params.put("%matiz%", 15);
        return withCaseQuery(params);
    }

    @Override
    public List<Pair<String, Integer>> withCaseQuery(Map<String, Integer> params) {
        if (params == null || params.size() < 1)
            return null;
        String templSql = "SELECT  carnumber, " +
                "CASE " +
                "%s" +
                "ELSE 0 " +
                "END discount " +
                "FROM cars " +
                "WHERE %s";
        StringBuilder whens = new StringBuilder(),
                whereExpr = new StringBuilder();
        params.forEach((str, i) -> {
            whens.append(String.format("WHEN carmodel LIKE '%s' THEN %d ", str, i));
            whereExpr.append(String.format("carmodel LIKE '%s' OR ", str));
        });
        whereExpr.replace(whereExpr.length() - 4, whereExpr.length(), "");
        return jdbcTemplate.query(String.format(templSql, whens, whereExpr),
                (ResultSet rs, int rowNum) -> new Pair<>(rs.getString("carnumber"),
                        rs.getInt("discount")));
    }

    @Override
    public boolean containsCarQuery(long id) {
        String sql = "SELECT COUNT(id) FROM cars WHERE id=?";
        Long countCars = this.jdbcTemplate.queryForObject(sql, new Object[]{id}, Long.class);
        return countCars != 0;
    }

    @Override
    public List<Car> selectAllCarsQuery() {
        String sql = "SELECT * FROM cars";
        return jdbcTemplate.query(sql, rowMapperCar);
    }

    @Override
    public Car selectQuery(long id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapperCar);
    }
    @Override
    public List<Car> selectQuery(String type) {
        String sql = "SELECT * FROM cars WHERE cartype = ?";
        return jdbcTemplate.query(sql, new Object[]{type}, rowMapperCar);
    }

    @Override
    public void updateQuery(Car car) {
        String sql = "UPDATE cars " +
                "SET carmodel=?,carnumber=?,cartype=?,description=?" +
                "WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{car.getCarModel(), car.getCarNumber(), car.getCarType(),
                car.getDescription(), car.getId()}, new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.BIGINT});
    }

    @Override
    public void insertQuery(Car car) {
        insertQuery(Collections.singletonList(car));
    }

    @Override
    public void insertQuery(List<Car> cars) {
        if (cars.size() > 0) {
            StringBuilder sql = new StringBuilder("INSERT INTO cars " +
                    "(id,carmodel,carnumber,cartype,description) " +
                    "VALUES");
            List<Object> args = new ArrayList<>();
            for (Car car : cars) {
                car.setId(currentCarId++);
                args.addAll(Arrays.asList(car.getId(), car.getCarModel(), car.getCarNumber(),
                        car.getCarType(), car.getDescription()));
                sql.append(" (?,?,?,?,?),");
            }
            sql.replace(sql.length() - 1, sql.length(), ";");
            jdbcTemplate.update(sql.toString(), args.toArray());
        }
    }

    @Override
    public List<Car> withinSelectQuery() {
        String sql = "SELECT * FROM cars " +
                "WHERE cartype = 'hatchback' " +
                "AND id IN (SELECT id FROM cars WHERE carnumber LIKE '%777')";
        return jdbcTemplate.query(sql, rowMapperCar);
    }

    //Выбрать машины у которых клиентов номера начинаются на 555
    @Override
    public List<Pair<Car, Client>> joinQuery() {
        String sql = "SELECT cars.*,clients.id AS client_id, clients.car_id,clients.name,clients.lastname," +
                "clients.phone FROM cars " +
                "INNER JOIN clients ON cars.id = clients.car_id AND clients.phone LIKE '555%'";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Client client = new Client();
            client.setId(rs.getLong("client_id"));
            client.setCarId(rs.getLong("car_id"));
            client.setLastname(rs.getString("lastname"));
            client.setName(rs.getString("name"));
            client.setPhone(rs.getString("phone"));
            Car car = rowMapperCar.mapRow(rs, rowNum);
            return new Pair<>(car, client);
        });
    }

    @Override
    public void deleteQuery(Long id) {
        String sql = "DELETE FROM cars WHERE id=?";
        jdbcTemplate.update(sql, new Object[]{id}, new int[]{Types.BIGINT});
    }

    @Override
    public void deleteQuery(String carnumber) {
        String sql = "DELETE FROM cars WHERE carnumber=?";
        jdbcTemplate.update(sql, new Object[]{carnumber}, new int[]{Types.VARCHAR});
    }

    @Override
    public void truncateQuery() {
        String sql = "TRUNCATE TABLE cars";
        jdbcTemplate.execute(sql);
    }

    @Override
    @Async
    public void doBackup(Environment environment) {
        ProcessBuilder processBuilder = new ProcessBuilder(
                environment.getProperty("jdbc.postgresql.pgDump.path") + "pg_dump.exe",
                "-d", environment.getProperty("jdbc.postgresql.pgDump.url"),
                "-U", environment.getProperty("jdbc.postgresql.username"),
                "-t", "cars",
                "-f", environment.getProperty("jdbc.postgresql.pgDump.backupFolder") +
                environment.getProperty("jdbc.postgresql.pgDump.backupName"));

        try {
            Process process = processBuilder.start();
            process.waitFor();
            System.out.println(process.exitValue());//Переделать! Эта строка должна быть отправлена в лог с пояснениями!
        } catch (IOException|InterruptedException e) {
            e.printStackTrace();
        }
    }
}