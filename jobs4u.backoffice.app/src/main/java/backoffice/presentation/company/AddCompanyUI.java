package backoffice.presentation.company;

import core.application.controllers.AddCompanyController;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddCompanyUI extends AbstractUI {
    private final AddCompanyController theController = new AddCompanyController();

    @Override
    protected boolean doShow() {
        final String companyName = Console.readLine("Company Name");
        try {
            this.theController.addCompany(companyName);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That company name is already in use.");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Add Company";
    }
}
