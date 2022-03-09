package com.service;

import com.model.Company;

import java.util.List;

public interface CompanyService {
    void save(Company company);

    List<Company> getAllCompany();

    Company getCompanyById(Long id);

    void updateCompany(Long id, Company company);

    void deleteCompany(Long id);
}
