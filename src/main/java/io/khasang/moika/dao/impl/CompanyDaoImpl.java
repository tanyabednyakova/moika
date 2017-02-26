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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CompanyDaoImpl implements CompanyDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public CompanyDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCompany(Company company) {
        getCurrentSession().save(company);
    }

    @Override
    public void updateCompany(Company company) {
        getCurrentSession().update(company);
    }

    @Override
    public void deleteCompany(Company company) {
        final Session session = getCurrentSession();
        session.delete(company);
        session.flush();
    }

    @Override
    public Company getCompanyById(int id) {
        Criteria criteria = getCurrentSession().
                createCriteria(Company.class);
        criteria.add(Restrictions.eq("id", id));
        return (Company) criteria.uniqueResult();
    }

    private Session getCurrentSession() {
        return sessionFactory.
                getCurrentSession();
    }

    @Override
    public Company getCompanyByName(String name) {
        Criteria criteria = getCurrentSession().
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
        Query query = getCurrentSession().createNativeQuery("select * from Company;");
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
        return query.list();
    }
}
