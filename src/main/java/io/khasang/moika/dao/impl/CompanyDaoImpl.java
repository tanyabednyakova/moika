package io.khasang.moika.dao.impl;

import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.entity.Company;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("company")
public class CompanyDaoImpl implements CompanyDao {
    private SessionFactory sessionFactory;

    public CompanyDaoImpl() {
    }

    @Autowired
    public CompanyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCompany(Company company) {
        sessionFactory.getCurrentSession().save(company);
    }

    @Override
    public void updateCompany(Company company) {
        sessionFactory.getCurrentSession().update(company);
    }

    @Override
    public void deleteCompany(Company company) {
        final Session session = sessionFactory.getCurrentSession();
        session.delete(company);
        session.flush();
    }

    @Override
    public Company getCompanyById(int id) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("id", id));
        return (Company) criteria.uniqueResult();
    }

    @Override
    public Company getCompanyByName(String name) {
        Criteria criteria = sessionFactory.
                getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("name", name));
        return (Company) criteria.uniqueResult();
    }

//    with criteria

//    @Override
//    @SuppressWarnings("unchecked")
//    public List<Question> getQuestionList() {
//        Criteria criteria = sessionFactory.
//                getCurrentSession().
//                createCriteria(Question.class);
//        return (List<Question>) criteria.list();
//    }

    /**
     * with native sql
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Company> getCompanyList() {
        Query query = sessionFactory.getCurrentSession().createNativeQuery("select * from company;");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
}
