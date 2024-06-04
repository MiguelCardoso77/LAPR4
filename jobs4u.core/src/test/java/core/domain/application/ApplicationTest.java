package core.domain.application;

import core.domain.candidate.Candidate;
import core.domain.candidate.Curriculum;
import core.domain.candidate.TelephoneNumber;
import core.domain.company.Company;
import core.domain.company.CompanyName;
import core.domain.customer.Customer;
import core.domain.interviewModel.InterviewModel;
import core.domain.jobOpening.*;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.domain.process.Process;
import core.domain.process.ProcessState;
import core.domain.process.ProcessStatus;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ApplicationTest {
    private Application application;
    private Rank rank;
    private Calendar createdOn;
    private String applicationFiles;
    private JobOpening jobReference;
    private Candidate candidate;
    private SystemUser operator;
    private SystemUser customerUser;
    private SystemUser customerManager;
    private SystemUser candidateUser;
    private CandidateRequirements candidateRequirements;
    private Customer customer;

    @BeforeEach
    void setUp() {
        rank = new Rank("1");
        createdOn = Calendar.getInstance();
        applicationFiles = "test_files";
        customerUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username", "password", "firstName", "lastName", "email@example.com").build();
        customerManager = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username1", "password", "firstName", "lastName", "email1@gmail.com").build();
        candidateUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username2", "password", "firstName", "lastName", "email2@gmail.com").build();
        operator = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username3", "password", "firstNam", "lastName", "email3@gmail.com").build();
        customer = new Customer(customerUser, EmailAddress.valueOf("email@test.com"), new Company(new CompanyName("Company1"), 3), customerManager);
        jobReference = new JobOpening(new JobReference("COMPANY1", true), new Description("Test Job"),
                new VacanciesNumber(3), new Address("Test Address"), Mode.HYBRID, ContractType.FULL_TIME, new TitleOrFunction("Test Title"),
                customer, new JobRequirementsSpecification(1, "test.txt"), new Process(ProcessState.APPLICATION, Calendar.getInstance(), ProcessStatus.OPEN),
                new InterviewModel("Test Interview Model"));

        candidate = new Candidate(candidateUser, new TelephoneNumber("123456789"), new Curriculum("dfghjkl.txt"));
        candidateRequirements = new CandidateRequirements(Arrays.asList("Requirement1", "Requirement2"));

        application = new Application(rank, createdOn, applicationFiles, jobReference, candidate, operator);
        application.uploadCandidateRequirements(candidateRequirements);
    }

    @Test
    void testRank() {
        assertEquals(rank, application.rank());
    }

    @Test
    void testSubmissionDate() {
        assertEquals(createdOn, application.submissionDate());
    }

    @Test
    void testDataFile() {
        assertEquals(applicationFiles, application.dataFile());
    }

    @Test
    void testOperator() {
        assertEquals(operator, application.operator());
    }

    @Test
    void testCandidate() {
        assertEquals(candidate, application.candidate());
    }

    @Test
    void testJobReference() {
        assertEquals(jobReference, application.jobReference());
    }

    @Test
    void testCandidateRequirements() {
        assertEquals(candidateRequirements, application.candidateRequirements());
    }

    @Test
    void testIdentity() {
        assertNotNull(application.identity());
    }

    @Test
    void testStatus() {
        application.changeStatus(Status.CHOSEN);
        assertEquals(Status.CHOSEN, application.status()); // assuming the initial status is PENDING
    }

    @Test
    void testUpdateRank() {
        Rank newRank = new Rank("2");
        application.updateRank(2);
        assertEquals(newRank, application.rank());
    }

    @Test
    void testChangeStatus() {
        Status newStatus = Status.ACCEPTED; // replace with actual Status
        application.changeStatus(newStatus);
        assertEquals(newStatus, application.status());
    }

    @Test
    void testUploadCandidateRequirements() {
        CandidateRequirements newCandidateRequirements = new CandidateRequirements(Arrays.asList("Requirement3", "Requirement4"));
        application.uploadCandidateRequirements(newCandidateRequirements);
        assertEquals(newCandidateRequirements, application.candidateRequirements());
    }
}