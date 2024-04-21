package backoffice.presentation.authz;

import core.usermanagement.application.ListUsersController;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import eapli.framework.visitor.Visitor;

public class ListCandidatesUI extends AbstractListUI<SystemUser> {
    private final ListUsersController theController = new ListUsersController();
    private final Menu userMenu = buildUserMenu();
    private static final String RETURN_LABEL = "Return ";


    @Override
    public String headline() {
        return "List all Candidates";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<SystemUser> elements() {
        return theController.allCandidates();
    }

    @Override
    protected Visitor<SystemUser> elementPrinter() {
        return new SystemUserPrinter();
    }

    @Override
    protected String elementName() {
        return "User";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %-30s", "EMAIL");
    }

    private Menu buildUserMenu() {
        final Menu menu = new Menu("User Options >");
        menu.addItem(1, "View Details", () -> {
            // Prompt for user selection
            int selection = Console.readInteger("Enter the number of the user to view details (or 0 to cancel): ");
            if (selection > 0) {
                SystemUser selectedUser = getUserByIndex(selection);
                if (selectedUser != null) {
                    displayUserDetails(selectedUser);
                } else {
                    System.out.println("Invalid selection.");
                }
            } else {
                System.out.println("Operation cancelled.");
            }
            return false;
        });
        menu.addItem(0, RETURN_LABEL, Actions.SUCCESS); // Cancel option
        return menu;
    }

    private void displayUserDetails(SystemUser user) {
        System.out.println("\nCandidate Details:");
        System.out.println("Email: " + user.email());
        System.out.println("Name: " + user.name());
    }

    private SystemUser getUserByIndex(int index) {
        int currentIndex = 1;
        for (SystemUser user : elements()) {
            if (currentIndex == index) {
                return user;
            }
            currentIndex++;
        }
        return null;
    }

    @Override
    public boolean show() {
        super.show();
        final MenuRenderer renderer = new VerticalMenuRenderer(userMenu, MenuItemRenderer.DEFAULT);
        renderer.render();
        return true;
    }


}
