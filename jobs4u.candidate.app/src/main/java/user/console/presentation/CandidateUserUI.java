package user.console.presentation;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;

/**
 * Abstract class representing a user interface component for the candidate application.
 * This class provides basic functionality for generating headlines and drawing form titles.
 * Subclasses can extend this class to implement specific user interface components.
 *
 * @author Miguel Cardoso
 */
@SuppressWarnings("squid:S106")
public abstract class CandidateUserUI extends AbstractUI {

    /** Authorization service for managing user authentication. */
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Generates the headline for the user interface.
     * If a user is authenticated, their username is included in the headline.
     * If no user is authenticated, the headline indicates an anonymous user.
     *
     * @return The generated headline.
     */
    @Override
    public String headline() {
        return authz.session().map(s -> "Candidate App [ @" + s.authenticatedUser().identity() + " ] ").orElse("Candidate App [ ==Anonymous== ]");
    }

    /**
     * Draws the title of a form with a border.
     * This method formats the title with a border and then calls {@code drawFormBorder()} to draw the border.
     *
     * @param title The title of the form.
     */
    @Override
    protected void drawFormTitle(final String title) {
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }
}
