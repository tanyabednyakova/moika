package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ProductDao;
import io.khasang.moika.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Component
@Transactional
public class ProductDaoImpl implements ProductDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getProductList() {
        CriteriaBuilder builder = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = builder.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);
        criteriaQuery.select(root);
        return sessionFactory.getCurrentSession().createQuery(criteriaQuery).list();
    }

    @Override
    public Product getProductById(long id) {
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public Product getProductByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Product.class);
        criteria.add(Restrictions.eq("name", name));
        return (Product) criteria.uniqueResult();
    }

    @Override
    public void addNewProduct(Product product) {
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void updateProduct(Product product) {
        sessionFactory.getCurrentSession().update(product);
    }
}
