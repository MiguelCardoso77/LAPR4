package core.services;

import core.domain.company.Company;
import core.domain.company.CompanyBuilder;
import core.persistence.PersistenceContext;
import core.repositories.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
/**
 * A service class for managing operations related to companies.
 *
 * @author 1220812@isep.ipp.pt
 */
@Service
public class CompanyService {
    private final CompanyRepository companyRepository = PersistenceContext.repositories().companies();
    /**
     * Registers a new company with the specified company name.
     *
     * @param companyName   the company name
     * @return the registered company
     */
    @Transactional
    public Company registerCompany(String companyName) {
        CompanyBuilder companyBuilder = new CompanyBuilder();
        companyBuilder.withCompanyName(companyName);
        Company company = companyBuilder.build();
        return companyRepository.save(company);
    }
    /**
     * Retrieves a company from the company repository based on the provided company number.
     *
     * @param companyNumber The company number to search for.
     * @return The company object corresponding to the provided company number if found,
     *         or null if no such company exists in the repository.
     */
    public Company findCompany(int companyNumber) {
        Iterable<Company> companies = companyRepository.allCompanies() ;
        for(Company company : companies){
            if(company.identity().equals(companyNumber) ){
                return company;
            }
        }
        return null;
    }

    /**
     * Retrieves all companies.
     *
     * @return an iterable of all companies
     */
    public Iterable<Company> allCompanies(){
        return companyRepository.allCompanies();
    }
}