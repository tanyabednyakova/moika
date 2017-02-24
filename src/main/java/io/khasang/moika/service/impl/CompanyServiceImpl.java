package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CompanyDao;
import io.khasang.moika.entity.Company;
import io.khasang.moika.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component("CompanyServiceImpl")
@Transactional
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    CompanyDao companyDao;

    public CompanyServiceImpl() {
    }

    public void addCompany(Company company) {
        companyDao.addCompany(company);
    }

    public void addCompany(Company company, String name) {
        company.setName(name);
        companyDao.addCompany(company);
    }

    public Company getCompanyById(int id) {
        return companyDao.getCompanyById(id);
    }

    public List<Company> getCompanyList(){
        return companyDao.getCompanyList();
    }
}
