package core.domain.jobOpening;

import core.domain.company.Company;
import core.domain.company.CompanyName;
import core.domain.customer.Customer;
import core.domain.interviewModel.InterviewModel;
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

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class JobOpeningBuilderTest {
    private JobReference jobReference;
    private Description description;
    private VacanciesNumber vacanciesNumber;
    private Address address;
    private Mode mode;
    private ContractType contractType;
    private TitleOrFunction titleOrFunction;
    private Customer customer;
    private JobRequirementsSpecification jobRequirementsSpecification;
    private Process process;
    private InterviewModel interviewModel;
    private SystemUser customerUser;
    private SystemUser customerManager;
    private JobOpeningBuilder jobOpeningBuilder;

    @BeforeEach
    void setUp() {
        customerUser = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username", "password", "firstName", "lastName", "email@example.com").build();
        jobReference = new JobReference("COMPANY1", true);
        description = new Description("Test Job");
        vacanciesNumber = new VacanciesNumber(3);
        address =new Address("Test Address");
        customerManager = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder()).with("username1", "password", "firstName", "lastName", "email1@gmail.com").build();
        mode = Mode.HYBRID;
        contractType = ContractType.FULL_TIME;
        titleOrFunction = new TitleOrFunction("Test Title");
        customer = new Customer(customerUser, EmailAddress.valueOf("email@test.com"), new Company(new CompanyName("Company1"), 3), customerManager);
        jobRequirementsSpecification = new JobRequirementsSpecification(1, "test.txt"); // replace with actual JobRequirementsSpecification object
        process = new Process(ProcessState.APPLICATION, Calendar.getInstance(), ProcessStatus.OPEN);
        interviewModel = new InterviewModel("Test Interview Model");

        jobOpeningBuilder = new JobOpeningBuilder();
    }

    @Test
    void testWithAll() {
        jobOpeningBuilder.withAll(jobReference, description.toString(), vacanciesNumber.number(), address.toString(), mode, contractType, titleOrFunction.toString(), customer, jobRequirementsSpecification, process, interviewModel);
        JobOpening jobOpening = jobOpeningBuilder.build();

        assertEquals(jobReference, jobOpening.jobReference());
        assertEquals(description, jobOpening.description());
        assertEquals(vacanciesNumber, jobOpening.vacanciesNumber());
        assertEquals(address, jobOpening.address());
        assertEquals(mode, jobOpening.mode());
        assertEquals(contractType, jobOpening.contractType());
        assertEquals(titleOrFunction, jobOpening.titleOrFunction());
        assertEquals(customer, jobOpening.customer());
    }

    @Test
    void testBuild() {
        jobOpeningBuilder.withAll(jobReference, description.toString(), vacanciesNumber.number(), address.toString(), mode, contractType, titleOrFunction.toString(), customer, jobRequirementsSpecification, process, interviewModel);
        JobOpening jobOpening = jobOpeningBuilder.build();

        assertNotNull(jobOpening);
    }

}