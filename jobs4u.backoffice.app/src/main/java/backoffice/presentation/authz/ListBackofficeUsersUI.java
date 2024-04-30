package backoffice.presentation.authz;

import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import core.application.controllers.ListUsersController;

public class ListBackofficeUsersUI extends AbstractListUI<SystemUser> {
    private final ListUsersController theController = new ListUsersController();

    @Override
    public String headline() {
        return "List BackOffice Users";
    }

    @Override
    protected String emptyMessage() {
        return "No data.";
    }

    @Override
    protected Iterable<SystemUser> elements() {
        return theController.allBackofficeUsers();
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
        return String.format("#  %-30s%-30s%-30s", "EMAIL", "F. NAME", "L. NAME");
    }

    @Override
    protected boolean doShow(){
        final Iterable<SystemUser> iterable = elements();

        if(!iterable.iterator().hasNext()) {
            System.out.println("There is no Backoffice User");

        } else {
            for (SystemUser user : iterable) {
                System.out.println(user.username() +  user.name().firstName() + user.name().lastName());
            }
        }
        return false;
    }

}
