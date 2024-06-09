package backoffice.presentation.application;

import console.presentation.utils.ConsoleColors;
import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * UI class responsible for handling the registration of applications for a job opening.
 * It interacts with various controllers to carry out the necessary actions.
 *
 * @author Diogo Ribeiro
 */
public class RegisterApplicationUI extends AbstractUI {
    private final RegisterCandidateController registerCandidateController = new RegisterCandidateController();
    private final ApplicationRegisterController applicationRegisterController = new ApplicationRegisterController();
    private final AddJobOpeningController jobOpeningController = new AddJobOpeningController();
    private final ListCandidatesController listCandidatesController = new ListCandidatesController();
    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final AddUserController addUserController = new AddUserController();
    private final Calendar createdOn = Calendar.getInstance();

    /**
     * Executes the UI for registering applications for a job opening.
     *
     * @return true if the registration process is successful, false otherwise.
     */
    @Override
    public boolean doShow() {
        Path path = selectSubfolder();

        if (path == null) {
            System.out.println(ConsoleColors.RED + "No valid subfolder selected." + ConsoleColors.RESET);
            return false;
        }

        List<String> candidateData;
        try {
            candidateData = ApplicationRegisterController.importCandidateFile(path);
        } catch (FileNotFoundException e) {
            System.out.println("Candidate file not found: " + e.getMessage());
            return false;
        }

        JobReference jobReference = JobReference.stringToJobReference(candidateData.get(0));

        if (!jobOpeningController.verifyID(jobReference)) {
            System.out.println("The referred Job Opening identified by " + jobReference + " doesn't exist in the system!");
            return false;
        }

        SystemUser operator = addUserController.getLoggedInUser();

        try {
            JobOpening jobOpening = listJobOpeningController.findJobOpeningByJobReference(jobReference);

            String emailAddress = candidateData.get(1);
            String firstName = candidateData.get(2);
            String lastName = candidateData.get(3);
            String telephoneNumber = candidateData.get(4);

            Path cvPath = ApplicationRegisterController.findCVFile(path);
            assert cvPath != null;
            String pathCV = cvPath.toString();

            String applicationFiles = path.toString();

            Candidate applicationCandidate = candidate(emailAddress, firstName, lastName, telephoneNumber, pathCV);

            Application application = applicationRegisterController.registerApplication("to be specified", applicationFiles, jobOpening, applicationCandidate, operator);
            if (application != null) {
                System.out.println("Application registered successfully");
            } else {
                System.out.println("Failed to register application.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return true;
    }

    /**
     * Registers a candidate in the system.
     *
     * @param emailAddress     the candidate's email address.
     * @param firstName        the candidate's first name.
     * @param lastName         the candidate's last name.
     * @param telephoneNumber  the candidate's telephone number.
     * @param cvPath           the path to the candidate's CV file.
     * @return the candidate object if the registration is successful, null otherwise.
     */
    private Candidate candidate(String emailAddress, String firstName, String lastName, String telephoneNumber, String cvPath) {
        TelephoneNumber telephoneNumber1 = TelephoneNumber.valueOf(telephoneNumber);
        if (!registerCandidateController.verifyTelephoneNumber(telephoneNumber1)) {
            Set<Role> roles = new HashSet<>();
            roles.add(Jobs4URoles.CANDIDATE);

            return registerCandidateController.registerCandidate(firstName, lastName, emailAddress, roles, createdOn, telephoneNumber, cvPath);
        }
        return listCandidatesController.findCandidateByTelephoneNumber(telephoneNumber1);
    }

    /**
     * Allows the user to select a subfolder for processing.
     *
     * @return the path of the selected subfolder, or null if no valid selection is made.
     */
    public Path selectSubfolder() {
        Scanner scanner = new Scanner(System.in);
        Path outputDirectory = Paths.get("SCOMP/2001/applicationEmailBot");

        try (Stream<Path> subfoldersStream = Files.list(outputDirectory)) {
            List<Path> subfolders = subfoldersStream
                    .filter(Files::isDirectory)
                    .collect(Collectors.toList());

            if (subfolders.isEmpty()) {
                System.out.println("No subfolders found in the specified directory.");
                return null;
            }

            for (int i = 0; i < subfolders.size(); i++) {
                System.out.println((i + 1) + ". " + subfolders.get(i).getFileName());
            }

            System.out.println("Select the application you want to register:");
            int selectedSubfolderIndex = scanner.nextInt() - 1;

            if (selectedSubfolderIndex < 0 || selectedSubfolderIndex >= subfolders.size()) {
                System.out.println("Invalid selection.");
                return null;
            }

            Path selectedSubfolder = subfolders.get(selectedSubfolderIndex);

            try (Stream<Path> subSubfoldersStream = Files.list(selectedSubfolder)) {
                List<Path> subSubfolders = subSubfoldersStream
                        .filter(Files::isDirectory)
                        .collect(Collectors.toList());

                if (subSubfolders.isEmpty()) {
                    System.out.println("No sub-subfolders found in the selected directory.");
                    return null;
                }

                for (int i = 0; i < subSubfolders.size(); i++) {
                    System.out.println((i + 1) + ". " + subSubfolders.get(i).getFileName());
                }

                System.out.println("Select a sub-subfolder by entering its number:");
                int selectedSubSubfolderIndex = scanner.nextInt() - 1;

                if (selectedSubSubfolderIndex < 0 || selectedSubSubfolderIndex >= subSubfolders.size()) {
                    System.out.println("Invalid selection.");
                    return null;
                }

                return subSubfolders.get(selectedSubSubfolderIndex);
            }

        } catch (IOException e) {
            System.out.println("Error accessing directories: " + e.getMessage());
        }

        return null;
    }

    /**
     * The description of the UI.
     *
     * @return the description of the UI.
     */
    @Override
    public String headline() {
        return "Register Applications for a Job Opening";
    }
}