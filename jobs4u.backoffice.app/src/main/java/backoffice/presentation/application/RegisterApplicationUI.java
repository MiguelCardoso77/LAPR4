package backoffice.presentation.application;

import core.application.controllers.*;
import core.domain.application.Application;
import core.domain.application.ApplicationFiles;
import core.domain.candidate.Candidate;
import core.domain.candidate.TelephoneNumber;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobReference;
import core.domain.user.Jobs4URoles;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;
import jakarta.transaction.InvalidTransactionException;
import lombok.SneakyThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 1220812@isep.ipp.pt
 */

public class RegisterApplicationUI extends AbstractListUI<Application> {

    private final RegisterCandidateController registerCandidateController = new RegisterCandidateController();
    private final ApplicationRegisterController applicationRegisterController = new ApplicationRegisterController();
    private final AddJobOpeningController jobOpeningController = new AddJobOpeningController();
    private final ListCandidatesController listCandidatesController = new ListCandidatesController();
    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final AddUserController addUserController = new AddUserController();
    private final Calendar createdOn = Calendar.getInstance();
    private List<Path> filePaths = new ArrayList<>();

    private List<String> candidateData = new ArrayList<>();
    @Override
    public String headline(){
        return "Register Applications for a Job Opening";
    }

    @Override
    protected Iterable<Application> elements() {
        return null;
    }

    @Override
    protected Visitor<Application> elementPrinter() {
        return null;
    }

    @Override
    protected String elementName() {
        return null;
    }

    @Override
    protected String listHeader() {
        return null;
    }

    @Override
    protected String emptyMessage() {
        return "No applications found for this job opening.";
    }
    @Override
    public boolean doShow(){
        Path path = selectSubfolder();

        try {
            candidateData = applicationRegisterController.importCandidateFile(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        JobReference jobReference = JobReference.valueOf(candidateData.get(0));

        if (!jobOpeningController.verifyID(jobReference)) {
            try {
                throw new InvalidTransactionException("The referred Job Opening identified by " + jobReference + " doesn't exist in the system!");
            } catch (InvalidTransactionException e) {
                throw new RuntimeException(e);
            }
        }

        SystemUser operator = addUserController.getLoggedInUser();

        try {
            JobOpening jobOpening = listJobOpeningController.findJobOpeningByJobReference(jobReference);

            String emailAddress = candidateData.get(1);
            String firstName = candidateData.get(2);
            String lastName = candidateData.get(3);
            String telephoneNumber = candidateData.get(4);

            Path cvPath = applicationRegisterController.findCVFile(path);
            String pathCV = String.valueOf(cvPath);

            filePaths = applicationRegisterController.findAllFiles(path);
            ApplicationFiles applicationFiles = new ApplicationFiles(filePaths);

            Candidate applicationCandidate = candidate(emailAddress, firstName, lastName, telephoneNumber, pathCV);

            Application application = applicationRegisterController.registerApplication("to be specified", applicationFiles, jobOpening, applicationCandidate, operator);
            if(application != null){
                System.out.println("Application registered successfully");
                System.out.println(application);
            }else{
                System.out.println("Failed to register application.");
            }


        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }


        return true;
    }

    private Candidate candidate(String emailAddress, String firstName, String lastName, String telephoneNumber, String cvPath){
        TelephoneNumber telephoneNumber1 = TelephoneNumber.valueOf(telephoneNumber);
        if(!registerCandidateController.verifyTelephoneNumber(telephoneNumber1)){
            Set<Role> roles = new HashSet<>();
            roles.add(Jobs4URoles.CANDIDATE);

            return registerCandidateController.registerCandidate(firstName, lastName, emailAddress, roles, createdOn, telephoneNumber, cvPath);
        }
        return listCandidatesController.findCandidateByTelephoneNumber(telephoneNumber1);
    }


    public Path selectSubfolder() {
        Scanner scanner = new Scanner(System.in);
        Path outputDirectory = Paths.get("SCOMP/2001/applicationEmailBot");

        try {
            List<Path> subfolders = Files.list(outputDirectory)
                    .filter(Files::isDirectory)
                    .collect(Collectors.toList());

            for (int i = 0; i < subfolders.size(); i++) {
                System.out.println((i + 1) + ". " + subfolders.get(i).getFileName());
            }

            System.out.println("Select the application you want to register:");
            int selectedSubfolderIndex = scanner.nextInt() - 1;

            Path selectedSubfolder = subfolders.get(selectedSubfolderIndex);

            List<Path> subSubfolders = Files.list(selectedSubfolder)
                    .filter(Files::isDirectory)
                    .collect(Collectors.toList());

            for (int i = 0; i < subSubfolders.size(); i++) {
                System.out.println((i + 1) + ". " + subSubfolders.get(i).getFileName());
            }

            System.out.println("Select a sub-subfolder by entering its number:");
            int selectedSubSubfolderIndex = scanner.nextInt() - 1;

            return subSubfolders.get(selectedSubSubfolderIndex);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}