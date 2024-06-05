package core.domain.notification;

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

class NotificationTest {
    private Application application;
    private Candidate candidate;
    private Message message;
    private Notification notification;

    @BeforeEach
    void setUp() {
        Calendar createdOn = Calendar.getInstance();
        Rank rank = new Rank("1");
        createdOn = Calendar.getInstance();
        String applicationFiles = "test_files";
        SystemUser customerUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username", "password", "firstName", "lastName", "email@example.com").build();
        SystemUser customerManager = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username1", "password", "firstName", "lastName", "email1@gmail.com").build();
        SystemUser candidateUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username2", "password", "firstName", "lastName", "email2@gmail.com").build();
        SystemUser operator = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username3", "password", "firstNam", "lastName", "email3@gmail.com").build();
        Customer customer = new Customer(customerUser, EmailAddress.valueOf("email@test.com"), new Company(new CompanyName("Company1"), 3), customerManager);
        JobOpening jobReference = new JobOpening(new JobReference("COMPANY1", true), new Description("Test Job"),
                new VacanciesNumber(3), new Address("Test Address"), Mode.HYBRID, ContractType.FULL_TIME, new TitleOrFunction("Test Title"),
                customer, new JobRequirementsSpecification(1, "test.txt"), new Process(ProcessState.APPLICATION, Calendar.getInstance(), ProcessStatus.OPEN),
                new InterviewModel("Test Interview Model"));

        candidate = new Candidate(candidateUser, new TelephoneNumber("123456789"), new Curriculum("dfghjkl.txt"));
        CandidateRequirements candidateRequirements = new CandidateRequirements(Arrays.asList("Requirement1", "Requirement2"));

        application = new Application(rank, createdOn, applicationFiles, jobReference, candidate, operator);
        application.uploadCandidateRequirements(candidateRequirements);
        message = new Message("Test Message");
        notification = new Notification(application, message, candidate);
    }

    @Test
    void testConstructor() {
        assertEquals(application, notification.application());
        assertEquals(message, notification.message());
        assertEquals(candidate, notification.candidate());
    }

    @Test
    void testHashCode() {
        Notification sameNotification = new Notification(application, message, candidate);
        assertEquals(notification.hashCode(), sameNotification.hashCode());
    }

    @Test
    void testToString() {
        assertNotNull(notification.toString());
        assertTrue(notification.toString().contains("notification id"));
    }

    @Test
    void testIdentity() {
        assertNull(notification.identity()); // ID should be null initially
    }

    @Test
    void testEquals() {
        Notification sameNotification = new Notification(application, message, candidate);
        assertTrue(notification.equals(sameNotification));

        Notification differentNotification = new Notification(application, new Message("Different Message"), candidate);
        assertFalse(notification.equals(differentNotification));
    }

    @Test
    void testApplicationField() {
        assertEquals(application, notification.application());
    }

    @Test
    void testMessageField() {
        assertEquals(message, notification.message());
    }

    @Test
    void testCandidateField() {
        assertEquals(candidate, notification.candidate());
    }

    @Test
    void testEqualsAndHashCode() {
        Notification notification1 = new Notification(application, message, candidate);
        Notification notification2 = new Notification(application, message, candidate);

        assertTrue(notification1.equals(notification2));
        assertEquals(notification1.hashCode(), notification2.hashCode());
    }
}
