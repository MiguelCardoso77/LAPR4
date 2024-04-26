package backoffice.presentation.company;

import core.application.controllers.ListCompaniesController;
import core.domain.company.Company;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ListCompaniesUI extends AbstractListUI<Company> {
    private final ListCompaniesController theController = new ListCompaniesController();
    private static final Logger LOGGER = LoggerFactory.getLogger(ListCompaniesUI.class);

    @Override
    public String headline() {
        return "List All Companies";
    }

    @Override
    protected String emptyMessage() {
        return "No Companies found.";
    }

    @Override
    protected Iterable<Company> elements() {
        return theController.allCompanies();
    }

    @Override
    protected Visitor<Company> elementPrinter() {
        return null;
    }

    @Override
    protected String elementName() {
        return "Company";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %-30s%-30s", "ID", "Name");
    }

    @Override
    public boolean doShow() {
        final List<Company> list = new ArrayList<>();
        final Iterable<Company> iterable = elements();
        Company company = null;
        if (!iterable.iterator().hasNext()) {
            System.out.println("There are no companies");
        } else {
            int cont = 1;
            System.out.println("List of Companies: \n");
            for (Company company1 : iterable) {
                list.add(company1);
                System.out.printf("%-6s%-30s%-30s%n", cont, company1.companyNumber(), company1.identity());
                cont++;
            }

            final int option = Console.readInteger("Enter the number of the company");
            if (option == 0) {
                System.out.println("No company selected");
            } else {
                try {
                    company = this.theController.findCompany(list.get(option - 1).companyNumber());
                } catch (IntegrityViolationException | ConcurrencyException ex) {
                    LOGGER.error("Error performing the operation", ex);
                    System.out.println(
                            "Unfortunately there was an unexpected error in the application. Please try again and if the problem persists, contact your system administrator.");
                }
            }
        }
        return true;
    }
}
