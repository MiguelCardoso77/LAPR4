package backoffice.presentation.candidate;

import core.application.controllers.RegisterCandidateController;
import core.domain.candidate.Candidate;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * User interface for registering a new candidate.
 * It prompts the user to input the candidate's information such as first name, last name, email, telephone number,
 * and curriculum path. Then, it calls the controller to register the candidate.
 *
 * @author Miguel Cardoso
 */
public class RegisterCandidateUI extends AbstractUI {
    private final RegisterCandidateController theController = new RegisterCandidateController();

    /**
     * Shows the UI for registering a candidate.
     *
     * @return true if the action is successfully executed, false otherwise.
     */
    @Override
    protected boolean doShow() {
        final String firstName = Console.readLine("First Name");
        final String lastName = Console.readLine("Last Name");
        final String email = Console.readLine("Email");
        final String telephoneNumber = Console.readLine("Telephone Number");
        final String curriculumPath = Console.readLine("Curriculum Path");

        final Calendar createdOn = Calendar.getInstance();
        final Set<Role> roles = new HashSet<>();

        try {
            Candidate registeredCandidate = theController.registerCandidate(firstName, lastName, email, roles, createdOn, telephoneNumber, curriculumPath);

            if (registeredCandidate != null) {
                System.out.println("Candidate registered successfully:");
                System.out.println(registeredCandidate);
            } else {
                System.out.println("Failed to register candidate.");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    /**
     * The headline for this UI.
     *
     * @return the headline for this UI
     */
    @Override
    public String headline() {
        return "Register Candidate";
    }
}
