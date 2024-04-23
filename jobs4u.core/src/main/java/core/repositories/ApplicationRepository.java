package core.repositories;

import core.domain.application.Application;
import core.domain.application.IdApplication;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import eapli.framework.domain.repositories.DomainRepository;

public interface ApplicationRepository extends DomainRepository<IdApplication,Application> {
    Iterable<Application> allApplications();
}