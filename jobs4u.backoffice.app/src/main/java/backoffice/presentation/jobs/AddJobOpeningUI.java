package backoffice.presentation.jobs;

import core.application.controllers.AddJobOpeningController;
import core.application.controllers.ListCompaniesController;
import core.domain.company.Company;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.Mode;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AddJobOpeningUI extends AbstractUI {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddJobOpeningUI.class);

    private final AddJobOpeningController theController = new AddJobOpeningController();

    private final ListCompaniesController companiesController = new ListCompaniesController();

    @Override
    protected boolean doShow() {
        final String description = Console.readLine("Description");
        final int vacanciesNumber = Console.readInteger("Vacancies Number");
        final String address = Console.readLine("Address");

        System.out.println("Mode");
        final Mode modes = showModes();

        System.out.println("Contract Type");
        final ContractType contractTypes = showContractTypes();

        final String titleOrFunction = Console.readLine("Title or Function");

        showCompanies();
        Company company = selectCompany();

        final String jobReference = company.companyName().toString();

        try {
            this.theController.addJobOpening(jobReference, description, vacanciesNumber, address, modes, contractTypes, titleOrFunction, company);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That job reference is already in use.");
        }

        return false;
    }

    private Mode showModes() {
        System.out.println("Select Mode:");
        System.out.println("1. HYBRID");
        System.out.println("2. ON_SITE");
        System.out.println("3. REMOTE");

        int selectedOption = Console.readInteger("Enter your choice:");

        switch (selectedOption) {
            case 1:
                return Mode.HYBRID;
            case 2:
                return Mode.ON_SITE;
            case 3:
                return Mode.REMOTE;
            default:
                System.out.println("Invalid option. Please select again.");
                return showModes();
        }
    }

    private ContractType showContractTypes() {
        System.out.println("Select Contract Type:");
        System.out.println("1. FULL_TIME");
        System.out.println("2. PART_TIME");

        int selectedOption = Console.readInteger("Enter your choice:");

        switch (selectedOption) {
            case 1:
                return ContractType.FULL_TIME;
            case 2:
                return ContractType.PART_TIME;
            default:
                System.out.println("Invalid option. Please select again.");
                return showContractTypes();
        }
    }

    private void showCompanies() {
        final Iterable<Company> iterable = companiesController.allCompanies();

        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no companies");
        } else {
            int cont = 1;
            System.out.println("List of registered Companies: \n");
            for (Company company : iterable) {
                System.out.printf("%-6s%-30s%n", cont, company.companyName());
                cont++;
            }
        }
    }

    private Company selectCompany() {
        final List<Company> list = new ArrayList<>();
        for (Company company : companiesController.allCompanies()) {
            list.add(company);
        }

        Company company = null;
        final int option = Console.readInteger("Enter the number of the company");
        if (option == 0) {
            System.out.println("No company selected");
        } else {
            try {
                company = this.companiesController.findCompany(list.get(option - 1).identity());
            } catch (IntegrityViolationException | ConcurrencyException ex) {
                LOGGER.error("Error performing the operation", ex);
                System.out.println(
                        "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
            }
        }

        return company;
    }

    @Override
    public String headline() {
        return "Add Job Opening";
    }
}
