package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.ProductDao;
import io.khasang.moika.entity.Product;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from products;");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }

    @Override
    public Product getProductById(long id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Product.class);
        criteria.add(Restrictions.eq("id", id));
        return (Product) criteria.uniqueResult();
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
