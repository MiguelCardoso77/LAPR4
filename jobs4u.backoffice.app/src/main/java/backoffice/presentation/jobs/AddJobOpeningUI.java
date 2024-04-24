package backoffice.presentation.jobs;

import core.application.controllers.AddJobOpeningController;
import core.domain.jobOpening.ContractType;
import core.domain.jobOpening.Mode;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddJobOpeningUI extends AbstractUI {
    private final AddJobOpeningController theController = new AddJobOpeningController();

    @Override
    protected boolean doShow() {
        final String jobReference = "1";
        final String description = Console.readLine("Description");
        final int vacanciesNumber = Console.readInteger("Vacancies Number");
        final String address = Console.readLine("Address");

        System.out.println("Mode");
        final Mode modes = showModes();

        System.out.println("Contract Type");
        final ContractType contractTypes = showContractTypes();

        final String titleOrFunction = Console.readLine("Title or Function");


        try {
            this.theController.addJobOpening(jobReference, description, vacanciesNumber, address, modes, contractTypes, titleOrFunction);
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

    @Override
    public String headline() {
        return "Add Job Opening";
    }
}
