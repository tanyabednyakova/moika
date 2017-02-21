package io.khasang.moika.model.impl;

import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Client;
import io.khasang.moika.model.AKovalevDataAccess;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.*;

/**
 * Created by Благомир on 18.02.2017..
 *
 */
@Service
public class AKovalevDataAccessImpl implements AKovalevDataAccess {
    private JdbcTemplate jdbcTemplate;
    private static long currId;
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
        currId = maxId == null ? 1 : ++maxId;
    }

    public static long getCurrId() {
        return currId;
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
    public List<Car> selectQuery(String type) {
        String sql = "SELECT * FROM cars WHERE type = ?";
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
            List args = new ArrayList();
            for (Car car : cars) {
                car.setId(currId++);
                args.addAll(Arrays.asList(car.getId(), car.getCarModel(), car.getCarNumber(),
                        car.getCarType(), car.getDescription()));
                sql.append(" (?,?,?,?,?),");
            }
            sql.replace(sql.length() - 1, sql.length(), ";");
            System.out.println(sql.toString());
            jdbcTemplate.update(sql.toString(), args.toArray());
        }
    }

    //
    @Override
    public List<Car> withinSelectQuery() {
        String sql = "SELECT * FROM cars " +
                "WHERE cartype = 'hatchback' " +
                "AND id IN (SELECT id FROM cars WHERE carnumber LIKE '%777')";
        List<Car> cars = jdbcTemplate.query(sql, rowMapperCar);
        return cars;
    }
    //Выбрать машины у которых клиентов номера начинаются на 555
    @Override
    public List<Pair<Car, Client>> joinQuery() {
        String sql = "SELECT * FROM cars " +
                "INNER JOIN clients ON cars.id = clients.car_id AND clients.phone LIKE '555%'";
        return jdbcTemplate.query(sql, (ResultSet rs, int rowNum) -> {
            Client client = new Client();
            client.setId(rs.getLong("clients.id"));
            client.setCarId(rs.getLong("car_id"));
            client.setLastname(rs.getString("lastname"));
            client.setName(rs.getString("name"));
            client.setPhone(rs.getString("phone"));
            Car car = rowMapperCar.mapRow(rs, rowNum);
            car.setId(rs.getLong("cars.id"));
            return new Pair<Car, Client>(car, client);
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
    public void doBackup(String path) {
       /*try {
            //Process process = Runtime.getRuntime().exec(path+ File.separator+"pg_dump.exe -U root -t cars carwash > db.sql");
            Process process = Runtime.getRuntime().exec(path+
                    File.separator+"pg_dump.exe -U root -W -t cars carwash > db.sql");
            process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),"Cp1251"));
            String line = null;
            while((line = br.readLine())!=null){
                System.out.println(line);
            }
            //System.out.println(process.getInputStream());
            //process.getOutputStream().write(Byte.valueOf("root"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
