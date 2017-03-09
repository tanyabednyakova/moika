package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CarDao;
import io.khasang.moika.entity.Car;
import io.khasang.moika.util.DataAccessUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class CarDaoImpl extends BasicDaoImpl<Car> implements CarDao {

    private final SessionFactory sessionFactory;

    private final DataAccessUtil dataAccessUtil;

    @Autowired
    public CarDaoImpl(SessionFactory sessionFactory, DataAccessUtil dataAccessUtil) {
        super(Car.class);
        this.sessionFactory = sessionFactory;
        this.dataAccessUtil = dataAccessUtil;
    }

    @Override
    public void addCar(Car car) {
        getCurrentSession().save(car);
    }

    @Override
    public List<Car> getCarList() {
        Query<Car> query = getCurrentSession().createQuery("FROM cars", Car.class);
        return query.getResultList();
    }

/*
    @Override
    public Car updateCar(Car car) {
        getCurrentSession().update(car);
        return car;
    }

    @Override
    public Car getCarById(long carId) {
        //        System.out.println(car.getCarModel());
        return getCurrentSession().get(Car.class, carId);
    }

    @Override
    public Car updateCar(long carId, Map<String, Object> fieldValueMap) {

        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        //see: http://www.programcreek.com/java-api-examples/index.php?api=javax.persistence.criteria.CriteriaUpdate
        CriteriaUpdate<Car> carCriteriaUpdate = criteriaBuilder.createCriteriaUpdate(Car.class);
        Root<Car> root = carCriteriaUpdate.from(Car.class);

        fieldValueMap.entrySet().forEach(e -> carCriteriaUpdate.set(e.getKey(), e.getValue()));

        carCriteriaUpdate.where(criteriaBuilder.equal(root.get("id"), carId));
        getCurrentSession().createQuery(carCriteriaUpdate).executeUpdate();

        Car car = getCarById(carId);
        dataAccessUtil.setNewValuesToBean(car, fieldValueMap);

        return updateCar(car);
    }
*/
}
