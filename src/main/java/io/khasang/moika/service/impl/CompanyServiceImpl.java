package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.dao.MoikaDaoException;
import io.khasang.moika.entity.Butterfly;
import io.khasang.moika.entity.Company;
import io.khasang.moika.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("CompanyServiceImpl")
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDao companyDao;

    public CompanyServiceImpl() {
    }

    public void addCompany(Company company) {
        if(company.getName().startsWith("a")){
            company.setAmount(BigDecimal.valueOf(100L));
        }
        if(company.getName().startsWith("b")){
            company.setAmount(BigDecimal.valueOf(50L));
        }
        try {
            companyDao.addEntity(company);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    public void addCompany(Company company, String name) {
        company.setName(name);
        try {
            companyDao.addEntity(company);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    public Company getCompanyById(int id) {
        try {
            return companyDao.getEntityById(id);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCompany(Company company) {
        try {
            companyDao.updateEntity(company);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompany(int id) {
        Company company = new Company();
        company.setId(id);
        try {
            companyDao.deleteEntity(company);
        } catch (MoikaDaoException e) {
            e.printStackTrace();
        }
    }

    public List<Company> getCompanyGazpromList() {
        try {
            return companyDao.getAllEntities();
        } catch (MoikaDaoException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addButterfly(Butterfly butterfly) {
        this.addCompany(butterfly);
    }
}
