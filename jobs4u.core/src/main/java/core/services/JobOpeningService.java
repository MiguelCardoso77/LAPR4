package core.services;

import core.domain.company.Company;
import core.domain.jobOpening.*;
import core.repositories.JobOpeningRepository;
import core.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    @Transactional
    public JobOpening registerJobOpening(String jobReference, String description, int vacanciesNumber, String address,
                                         Mode mode, ContractType contractType, String titleOrFunction, Company company) {
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        jobOpeningBuilder.withAll(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction, company);
        JobOpening jobOpening = jobOpeningBuilder.build();
        return jobOpeningRepository.save(jobOpening);
    }

    public JobOpening findJobOpening(JobReference jobReference){
        Iterable<JobOpening> jobOpenings = jobOpeningRepository.allJobOpenings();
        for (JobOpening jobOpening : jobOpenings) {
            if(jobOpening.identity().equals(jobReference)){
                return jobOpening;
            }
        }
        return null;
    }

    public boolean verifyJobReference(JobReference jobReference){
        if(jobOpeningRepository.ofIdentity(jobReference).isPresent()){
            return true;
        }
        return false;
    }
    public Iterable<JobOpening> allJobOpenings() {
        return jobOpeningRepository.findAll();
    }

    public JobOpening findJobOpening(JobReference jobReference) {
        Iterable<JobOpening> jobOpenings = jobOpeningRepository.allJobOpenings() ;
        for(JobOpening jobOpening : jobOpenings){
            if(jobOpening.identity().equals(jobReference) ){
                return jobOpening;
            }
        }
        return null;
    }

}