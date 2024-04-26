package bootstrappers.smoketests;

import bootstrappers.bootstraping.UsersBootstrapper;
import eapli.framework.actions.Action;

public class MasterUsersSmokeTester extends UsersBootstrapper implements Action {
    @Override
    public boolean execute() {
        registerTestCandidate("TestOne", "CandidateOne", "testcandidate1@gmail.com", "910920930", "curriculumPathOne");
        registerTestCandidate("TestTwo", "CandidateTwo", "testcandidate2@gmail.com", "940950960", "curriculumpPathTwo");

        return true;
    }

    private void registerTestCandidate(final String firstName, final String lastName, final String email, final String telephoneNumber, final String curriculum) {
        registerCandidate(firstName, lastName, email, telephoneNumber, curriculum);
    }
}
