package menus;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import services.Jobs4uUserService;

public abstract class Jobs4uUserBaseUI extends AbstractUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    protected Jobs4uUserService svc = new Jobs4uUserService();

    @Override
    public String headline() {
        return "Jobs4U User";
    }

    @Override
    protected void drawFormTitle(final String title) {
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }
}
