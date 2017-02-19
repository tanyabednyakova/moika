import io.khasang.moika.config.AppConfig;
import io.khasang.moika.entity.Car;
import io.khasang.moika.entity.Cats;
import io.khasang.moika.model.AKovalevDataAccess;
import io.khasang.moika.model.Impl.AKovalevDataAccessImpl;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (((AKovalevDataAccessImpl) dataAccess).getCurrId() < 2) {
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
        JdbcTemplate jdbcTemplate = jdbcTemplate();
        AKovalevDataAccess dataAccess = new AKovalevDataAccessImpl(jdbcTemplate);
        initTable(dataAccess, jdbcTemplate);
        Map<String,Integer> params = new HashMap<String,Integer>();
        params.put("%lada%",4);
        params.put("%benz%",7);
        System.out.println(dataAccess.withCaseQuery(params));
        String path = "D:\\Programming\\PostgreSQL\\9.5\\bin";
        dataAccess.doBackup(path);
    }
}
