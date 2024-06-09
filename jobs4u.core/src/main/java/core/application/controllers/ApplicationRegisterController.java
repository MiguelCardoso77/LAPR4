package core.application.controllers;

import core.domain.application.Rank;
import core.domain.candidate.Candidate;
import core.domain.jobOpening.JobOpening;
import core.domain.user.Jobs4URoles;
import core.services.ApplicationService;
import core.domain.application.Application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class for registering job applications.
 * This class provides methods for registering job applications and ensures authorization
 * for users with appropriate roles.
 *
 * @author Diogo Ribeiro
 */
@UseCaseController
public class ApplicationRegisterController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ApplicationService applicationService = new ApplicationService();

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The currently logged-in user, or null if no user is logged in.
     */
    public SystemUser getLoggedInUser() {
        return authz.session().map(UserSession::authenticatedUser).orElse(null);
    }

    /**
     * Registers a new job application.
     *
     * @param rank         The rank of the application.
     * @param files        The files associated with the application.
     * @param jobReference The job opening reference for which the application is made.
     * @param candidate    The candidate who submitted the application.
     * @param operator     The system user who registered the application.
     * @return The registered application.
     */
    public Application registerApplication(final String rank,
                                           final String files, final JobOpening jobReference,
                                           final Candidate candidate, final SystemUser operator) {
        return createApplication(rank, files, jobReference, candidate, operator);
    }

    /**
     * Creates a new job application and registers it.
     *
     * @param rank         The rank of the application.
     * @param files        The files associated with the application.
     * @param jobReference The job opening reference for which the application is made.
     * @param candidate    The candidate who submitted the application.
     * @param operator     The system user who registered the application.
     * @return The registered application.
     */
    private Application createApplication(final String rank,
                                          final String files, final JobOpening jobReference,
                                          final Candidate candidate, final SystemUser operator) {
        Preconditions.noneNull(rank, files, jobReference, candidate, operator);

        authz.ensureAuthenticatedUserHasAnyOf(Jobs4URoles.ADMIN, Jobs4URoles.OPERATOR, Jobs4URoles.BOOTSTRAP);

        return applicationService.registerApplication(Rank.valueOf(rank), files, jobReference, candidate, operator);
    }

    /**
     * Imports data from a candidate file.
     *
     * @param path The path to the directory containing candidate files.
     * @return A list of strings containing the imported data.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static List<String> importCandidateFile(Path path) throws FileNotFoundException {
        String stringPath = String.valueOf(path);
        File mainFile = new File(stringPath);
        File[] archives = mainFile.listFiles();
        List<String> data = new ArrayList<>();
        assert archives != null;
        for (File file : archives) {
            if (file.getName().contains("candidate-data")) {
                Scanner scanner = new Scanner(file);
                int i = 0;
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (i == 2) {
                        String[] names = line.split(" ");
                        data.add(names[0]);
                        data.add(names[1]);
                    } else {
                        data.add(line);
                    }
                    i++;
                }
                scanner.close();
                break;
            }
        }
        return data;
    }

    /**
     * Finds the path of the CV file within a directory.
     *
     * @param directoryPath The path to the directory to search for CV files.
     * @return The path of the CV file, or null if not found.
     */
    public static Path findCVFile(Path directoryPath) {
        File directory = directoryPath.toFile();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.getName().contains("cv")) {
                    return file.toPath();
                }
            }
        }

        return null;
    }

    /**
     * Finds all files within a directory.
     *
     * @param directoryPath The path to the directory to search for files.
     * @return A list of paths to all files found within the directory.
     */
    public static List<Path> findAllFiles(Path directoryPath) {
        List<Path> filePaths = new ArrayList<>();
        File directory = directoryPath.toFile();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    filePaths.add(file.toPath());
                }
            }
        }

        return filePaths;
    }
}
