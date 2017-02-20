import io.khasang.moika.entity.Car;
import io.khasang.moika.model.AKovalevDataAccess;
import io.khasang.moika.model.impl.AKovalevDataAccessImpl;
import org.junit.Test;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.*;

/**
 * Created by Благомир on 18.02.2017.
 */
public class TestJdbcDAO {
    private Car newCar(String carModel,String carNumber, String carType, String description){
        Car car = new Car();
        car.setCarModel(carModel);
        car.setCarNumber(carNumber);
        car.setCarType(carType);
        car.setDescription(description);
        return car;
    }

    private void initTable(AKovalevDataAccess dataAccess, JdbcTemplate jdbcTemplate) {
        if (((AKovalevDataAccessImpl) dataAccess).getCurrentCarId() < 2) {
            List<Car> cars = Arrays.asList(
                    newCar("lada granta", "A854BE95", "sedan", "bla-bla-bla"),
                    newCar("chevrolet lacetti", "C345KK777", "hatchback", "bla-bla-bla"),
                    newCar("daewoo matiz", "K321HH199", "hatchback", "bla-bla-bla"),
                    newCar("nissan almera", "C789XX95", "sedan", "bla-bla-bla"),
                    newCar("chevrolet lacetti", "X382MC777", "wagon", "bla-bla-bla"),
                    newCar("volkswagen tiguan", "E001KB198", "crossover", "bla-bla-bla"),
                    newCar("great wall hover h3", "X382MC777", "suv", "bla-bla-bla"),
                    newCar("ford focus", "M631BC197", "sedan", "bla-bla-bla"),
                    newCar("mercedes-benz vito", "M552CX199", "minibus", "bla-bla-bla"),
                    newCar("skoda octavia", "X001XX37", "sedan", "bla-bla-bla"),
                    newCar("kia rio", "B201EX165", "sedan", "bla-bla-bla"),
                    newCar("lada priora", "E111BA777", "hatchback", "bla-bla-bla"));
            dataAccess.insertQuery(cars);
            Long maxClientsId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM clients", Long.class);
            if (maxClientsId == null || maxClientsId < 1){
                String sql = "INSERT INTO clients (id,car_id,lastname,name,phone) " +
                        "VALUES (1,1,'Ivanov','Ivan','555-55-55')," +
                        "(2,2,'Petrov','Petr','555-44-44')," +
                        "(3,3,'Sidorov','Sidr','444-44-44')," +
                        "(4,4,'Lionidov','Lionid','654-44-41')," +
                        "(5,5,'Alekseev','Aleksey','354-11-44')," +
                        "(6,6,'Aleksandrov','Aleksandr','098-22-13')," +
                        "(7,7,'Viteev','Vitaliy','321-12-34')," +
                        "(8,8,'Eliseev','Jeka','456-98-76')," +
                        "(9,9,'Kumarin','Fedot','234-32-21')," +
                        "(10,10,'Fedorov','Fedr','799-32-56')," +
                        "(11,11,'Maksimov','Maksim','997-88-00')," +
                        "(12,12,'Pavlov','Pavel','100-10-01');";
                jdbcTemplate.update(sql);
            }
        }
    }

    public JdbcTemplate jdbcTemplate(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/carwash");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
    @Test
    public void test(){
        /*JdbcTemplate jdbcTemplate = jdbcTemplate();
        AKovalevDataAccess dataAccess = new AKovalevDataAccessImpl(jdbcTemplate);
        initTable(dataAccess, jdbcTemplate);
        System.out.println(dataAccess.containsCarQuery(5L));
        System.out.println(dataAccess.containsCarQuery(15L));
        dataAccess.truncateQuery();
        dataAccess.deleteQuery("A512BB97");
        Car car = dataAccess.selectQuery(2);
        car.setDescription("vedro-car");
        car.setCarNumber("A512BB97");
        car.setCarModel("vaz niva");
        car.setCarType("suv");
        dataAccess.insertQuery(car);
        dataAccess.updateQuery(car);
        System.out.println(String.format("id: %d  carnumber: %s  carmodel: %s",car.getId(),
                car.getCarNumber(),car.getCarModel()));
        dataAccess.selectQuery("hatchback").forEach((car ->
                System.out.println(String.format("id: %d  carnumber: %s  carmodel: %s",car.getId(),
                        car.getCarNumber(),car.getCarModel()))));
        dataAccess.joinQuery().forEach((pair)->
                System.out.println(String.format("car id: %d  carnumber: %s  client id: %d  car_id: %d  name: %s",
                        pair.getKey().getId(),pair.getKey().getCarNumber(),
                        pair.getValue().getId(),pair.getValue().getCarId(),pair.getValue().getName())));
        Map<String,Integer> params = new HashMap<>();
        params.put("%lada%",4);
        params.put("%benz%",7);
        System.out.println(dataAccess.withCaseQuery(params));

        ConfigurableEnvironment environment = new StandardEnvironment();
        Map<String,Object> propeties = new HashMap<>();
        propeties.put("jdbc.postgresql.pgDump.path","c:/Program Files/PostgreSQL/9.5/bin/");
        propeties.put("jdbc.postgresql.pgDump.url","postgres://postgres:root@localhost/carwash");
        propeties.put("jdbc.postgresql.pgDump.backupFolder","c:/Program Files/PostgreSQL/9.5/bin/");
        propeties.put("jdbc.postgresql.pgDump.backupName","tb.sql");
        propeties.put("jdbc.postgresql.username","root");
        environment.getPropertySources().addFirst(new MapPropertySource("properties",propeties));

        dataAccess.doBackup(environment);*/
    }
}
