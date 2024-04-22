package core.jobOpening.services;

import core.jobOpening.domain.ContractType;
import core.jobOpening.domain.JobOpening;
import core.jobOpening.domain.Mode;
import core.jobOpening.repositories.JobOpeningRepository;
import core.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class JobOpeningService {
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();

    @Transactional
    public JobOpening registerJobOpening(String jobReference, String description, int vacanciesNumber, String address, Mode mode, ContractType contractType, String titleOrFunction) {
        JobOpeningBuilder jobOpeningBuilder = new JobOpeningBuilder();
        jobOpeningBuilder.withAll(jobReference, description, vacanciesNumber, address, mode, contractType, titleOrFunction);
        JobOpening jobOpening = jobOpeningBuilder.build();
        return jobOpeningRepository.save(jobOpening);
    }

    public Iterable<JobOpening> allJobOpenings() {
        return jobOpeningRepository.findAll();
    }
}
