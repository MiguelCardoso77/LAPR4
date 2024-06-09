package core.domain.interview;

import core.domain.application.Application;
import core.domain.application.CandidateRequirements;
import core.domain.application.Rank;
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

import static org.junit.jupiter.api.Assertions.*;

class JobInterviewTest {
    private JobInterview jobInterview;
    private Calendar createdOn;
    private Time time;
    private Score score;
    private Result result;
    private Application application;
    private InterviewAnswers interviewAnswers;
    private Rank rank;
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
        createdOn = Calendar.getInstance();
        time = new Time(115);
        score = new Score(5);
        result = new Result("Declined");
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
        interviewAnswers = new InterviewAnswers(Arrays.asList("Answer 1", "Answer 2", "Answer 3"));

        jobInterview = new JobInterview(createdOn, time, score, result, application, interviewAnswers);
    }

    @Test
    void testCreatedOn() {
        assertEquals(createdOn, jobInterview.createdOn());
    }

    @Test
    void testTime() {
        assertEquals(time, jobInterview.time());
    }

    @Test
    void testScore() {
        assertEquals(score, jobInterview.score());
    }

    @Test
    void testResult() {
        assertEquals(result, jobInterview.result());
    }

    @Test
    void testApplication() {
        assertEquals(application, jobInterview.application());
    }

    @Test
    void testInterviewAnswers() {
        assertEquals(interviewAnswers, jobInterview.interviewAnswers());
    }

    @Test
    void testUploadInterviewAnswers() {
        InterviewAnswers newInterviewAnswers = new InterviewAnswers(Arrays.asList("New Answer 1", "New Answer 2", "New Answer 3"));
        jobInterview.uploadInterviewAnswers(newInterviewAnswers);

        assertEquals(newInterviewAnswers, jobInterview.interviewAnswers());
    }

    @Test
    void testEquals() {
        JobInterview sameJobInterview = new JobInterview(createdOn, time, score, result, application, interviewAnswers);
        assertTrue(jobInterview.equals(sameJobInterview));
    }

    @Test
    void testHashCode() {
        JobInterview sameJobInterview = new JobInterview(createdOn, time, score, result, application, interviewAnswers);
        assertEquals(jobInterview.hashCode(), sameJobInterview.hashCode());
    }

    @Test
    void testSameAs() {
        JobInterview sameJobInterview = new JobInterview(createdOn, time, score, result, application, interviewAnswers);
        assertTrue(jobInterview.sameAs(sameJobInterview));
    }

    @Test
    void testToString() {
        String expectedString = "JobInterview{" +
                "id=" + jobInterview.identity() +
                ", createdOn=" + createdOn +
                ", time=" + time +
                ", score=" + score +
                ", result=" + result +
                ", application=" + application +
                ", interviewAnswers=" + interviewAnswers +
                '}';
        assertEquals(expectedString, jobInterview.toString());
    }

    @Test
    void testUpdateScore() {
        Score newScore = new Score(10); // replace with actual Score object
        jobInterview.updateScore(newScore);

        assertEquals(newScore, jobInterview.score());
    }

    @Test
    void testGetScore() {
        assertEquals(score, jobInterview.score1(null));
    }

    @Test
    void testReturnScore() {
        assertEquals(score.score(), jobInterview.returnScore());
    }

}