package backoffice.presentation.jobs;

import core.jobOpening.application.AddJobOpeningController;
import core.jobOpening.domain.ContractType;
import core.jobOpening.domain.Mode;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

import java.util.HashSet;
import java.util.Set;

public class AddJobOpeningUI extends AbstractUI {
    private final AddJobOpeningController theController = new AddJobOpeningController();

    @Override
    protected boolean doShow() {
        final int jobReference = theController.buildJobReference(1);
        final String description = Console.readLine("Description");
        final int vacanciesNumber = Console.readInteger("Vacancies Number");
        final String adress = Console.readLine("Adress");

        final Set<Mode> modes = new HashSet<>();
        boolean show;
        do {
            show = showModes(modes);
        } while (!show);

        final Set<ContractType> contractTypes = new HashSet<>();
        boolean show2;
        do {
            show2 = showContractTypes(contractTypes);
        } while (!show2);

        final String titleOrFunction = Console.readLine("Title or Function");


        try {
            this.theController.addJobOpening(jobReference, description, vacanciesNumber, adress, null, null, titleOrFunction);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("That job reference is already in use.");
        }

        return false;
    }

    private boolean showModes(final Set<Mode> modes) {
        final Menu modesMenu;

        modesMenu = buildAvailableModes(modes);

        final MenuRenderer renderer = new VerticalMenuRenderer(modesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private boolean showContractTypes(final Set<ContractType> contractTypes) {
        final Menu contractTypesMenu;

        contractTypesMenu = buildAvailableContractTypes(contractTypes);

        final MenuRenderer renderer = new VerticalMenuRenderer(contractTypesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildAvailableModes(final Set<Mode> modes) {
        final Menu modesMenu = new Menu();
        int counter = 0;

        modesMenu.addItem(MenuItem.of(counter++, Mode.HYBRID.toString(), () -> modes.add(Mode.HYBRID)));
        modesMenu.addItem(MenuItem.of(counter++, Mode.ON_SITE.toString(), () -> modes.add(Mode.ON_SITE)));
        modesMenu.addItem(MenuItem.of(counter, Mode.REMOTE.toString(), () -> modes.add(Mode.REMOTE)));

        return modesMenu;
    }

    private Menu buildAvailableContractTypes(final Set<ContractType> contractTypes) {
        final Menu contractTypesMenu = new Menu();
        int counter = 0;

        contractTypesMenu.addItem(MenuItem.of(counter++, ContractType.FULL_TIME.toString(), () -> contractTypes.add(ContractType.FULL_TIME)));
        contractTypesMenu.addItem(MenuItem.of(counter, ContractType.PART_TIME.toString(), () -> contractTypes.add(ContractType.PART_TIME)));

        return contractTypesMenu;
    }

    @Override
    public String headline() {
        return "Add Job Opening";
    }
}
