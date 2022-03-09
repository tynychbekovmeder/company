package com.dao;

import com.model.Company;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class CompanyDaoImpl implements CompanyDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Company company) {
        entityManager.persist(company);
    }

    @Override
    public List<Company> getAllCompany() {
        return entityManager.createQuery("SELECT company FROM Company company", Company.class).getResultList();
    }

    @Override
    public void updateCompany(Long id, Company company) {
        Company company1 = getCompanyById(id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(company1);
    }

    @Override
    public void deleteCompany(Long id) {
        entityManager.remove(getCompanyById(id));
    }

    @Override
    public Company getCompanyById(Long id) {
        Company company = entityManager.find(Company.class, id);
        if (company == null) {
            throw new EntityNotFoundException("Can't find Company for ID "
                    + id);
        }
        return company;
    }
}
