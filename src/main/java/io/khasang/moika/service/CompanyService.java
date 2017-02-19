package io.khasang.moika.service;

import io.khasang.moika.entity.Company;

public interface CompanyService {
    public void addCompany(Company company);
    public void addCompany(Company company, String name);
    public Company getCompanyById(int id);
}