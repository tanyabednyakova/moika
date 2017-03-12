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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;
@Component
@Transactional
public class ProductDaoImpl implements ProductDao {
    private final SessionFactory sessionFactory;
    private EntityManagerFactory factory;

    @Autowired
    public ProductDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private EntityManager getEntityManager() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory("persistenceUnit");
        }
        return factory.createEntityManager();
    }

    @Override
    public List<Product> getProductList() {
        Query query = sessionFactory.getCurrentSession().createNativeQuery("select name, amount, price from products;");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return (List<Product>) query.list();
    }

    @Override
    public Product getProductById(long id) {
//        EntityManager em = getEntityManager();
//        Metamodel m = em.getMetamodel();
//        EntityType<Product> product = m.entity(Product.class);
//        CriteriaBuilder builder = em.getCriteriaBuilder();
//        CriteriaQuery criteria = builder.createQuery(Product.class);
//        Root<Product> root = criteria.from(product);
//        criteria.select(root);
//        criteria.where(builder.equal(
//                root.get()
//        ))

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
