package io.khasang.moika.service.impl;

import io.khasang.moika.dao.CompanyDao;
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
        companyDao.addCompany(company);
    }

    public void addCompany(Company company, String name) {
        company.setName(name);
        companyDao.addCompany(company);
    }

    public Company getCompanyById(long id) {
        return companyDao.getCompanyById(id);
    }

    @Override
    public void updateCompany(Company company) {
        companyDao.updateCompany(company);
    }

    @Override
    public void deleteCompany(long id) {
        Company company = new Company();
        company.setId(id);
        companyDao.deleteCompany(company);
    }

    public List<Company> getCompanyGazpromList() {
        return companyDao.getCompanyList();
    }

    @Override
    public void addButterfly(Butterfly butterfly) {
        this.addCompany(butterfly);
    }
}
