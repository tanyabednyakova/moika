package io.khasang.moika.dao;

import io.khasang.moika.entity.Company;

import java.util.List;

public interface CompanyDao {
    void addCompany(Company company);

    void updateCompany(Company company);

    void deleteCompany(Company company);

    Company getCompanyById(int id);

    Company getCompanyByName(String name);

    List getCompanyList();
}
