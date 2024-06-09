package followUp.server;

import com.google.gson.Gson;
import core.domain.application.Application;
import core.domain.application.ApplicationsDTO;
import core.domain.candidate.Candidate;
import core.domain.customer.Customer;
import core.domain.email.EmailHandler;
import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobOpeningDTO;
import core.domain.notification.Notification;
import core.domain.user.Jobs4URoles;

import core.protocol.UnsignedInteger;
import core.services.*;
import infrastructure.authz.AuthenticationCredentialHandler;
import infrastructure.authz.CredentialHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for handling various types of requests from clients.
 * Extends BaseResponder to use common response functionalities.
 *
 * @author Miguel Cardoso
 */
public class CallResponder extends BaseResponder {
    private static final Logger LOGGER = LoggerFactory.getLogger(CallResponder.class);

    private final ApplicationService applicationService = new ApplicationService();
    private final ApplicationsDTOService applicationsDTOService = new ApplicationsDTOService();
    private final CandidateService candidateService = new CandidateService();
    private final CustomerService customerService = new CustomerService();
    private final JobOpeningService jobOpeningService = new JobOpeningService();
    private final JobOpeningDTOService jobOpeningDTOService = new JobOpeningDTOService();
    private final NotificationService notificationService = new NotificationService();

    /**
     * Constructor that initializes CallResponder with a client socket.
     *
     * @param socket The client socket
     * @throws IOException If an I/O error occurs when creating the CallResponder
     */
    public CallResponder(Socket socket) throws IOException {
        super(socket);
    }

    /**
     * Starts handling the incoming client requests.
     */
    @Override
    public void startHandler() {
        System.out.println("Connection established!");

        try {
            byte version = inData.readByte();
            System.out.println("\nVersion: " + version);

            byte code = inData.readByte();

            switch (code) {
                case 0:
                    handleCode0();
                    break;
                case 1:
                    handleCode1();
                    break;
                case 2:
                    handleCode2();
                    break;
                case 3:
                    handleCode3();
                    break;
                case 4:
                    handleCode4();
                    break;
                case 5:
                    handleCode5();
                    break;
                case 6:
                    handleCode6();
                    break;
                case 7:
                    handleCode7();
                    break;
                case 8:
                    handleCode8();
                    break;
                case 9:
                    handleCode9();
                    break;
                default:
                    System.out.println("Invalid code received!");
                    protocol.sendErr();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles communication test (COMMTEST) request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode0() throws IOException {
        System.out.println("Code 0 received! -> COMMTEST\n");
        protocol.sendAck();
    }

    /**
     * Handles disconnect request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode1() throws IOException {
        System.out.println("Code 1 received! -> DISCONNECT\n");
    }

    /**
     * Handles acknowledgment (ACK) request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode2() throws IOException {
        System.out.println("Code 2 received! -> ACK\n");
    }

    /**
     * Handles error (ERR) request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode3() throws IOException {
        System.out.println("Code 3 received! -> ERR\n");
    }

    /**
     * Handles authentication request.
     *
     * @throws IOException            If an I/O error occurs
     * @throws ClassNotFoundException If the class of a serialized object cannot be found
     */
    private void handleCode4() throws IOException, ClassNotFoundException {
        System.out.println("Code 4 received! -> Authentication Request\n");

        int lenghtEmail = readLenght();

        byte[] emailBytes = inData.readNBytes(lenghtEmail);
        String email = new String(emailBytes, StandardCharsets.UTF_8);
        System.out.println("Email: " + email);

        int lenghtPassword = readLenght();

        byte[] passwordBytes = inData.readNBytes(lenghtPassword);
        String password = new String(passwordBytes, StandardCharsets.UTF_8);
        System.out.println("Password: " + password);

        CredentialHandler credentialHandler = new AuthenticationCredentialHandler();
        if (credentialHandler.authenticated(email, password, Jobs4URoles.CANDIDATE)) {
            System.out.println("\nAuthenticated as candidate!");
            protocol.sendAck();
        } else if (credentialHandler.authenticated(email, password, Jobs4URoles.CUSTOMER)) {
            System.out.println("\nAuthenticated as customer!");
            protocol.sendAck();
        } else {
            System.out.println("\nAuthentication failed!");
            protocol.sendErr();
        }
    }

    /**
     * Handles email request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode5() throws IOException {
        System.out.println("Code 5 received! -> Email Request\n");

        int lenghtToWho = readLenght();

        byte[] toWhoBytes = inData.readNBytes(lenghtToWho);
        String toWho = new String(toWhoBytes, StandardCharsets.UTF_8);
        System.out.println("To: " + toWho);

        int lenghtSubject = readLenght();

        byte[] subjectBytes = inData.readNBytes(lenghtSubject);
        String subject = new String(subjectBytes, StandardCharsets.UTF_8);
        System.out.println("Subject: " + subject);

        int lenghtBody = readLenght();

        byte[] bodyBytes = inData.readNBytes(lenghtBody);
        String body = new String(bodyBytes, StandardCharsets.UTF_8);
        System.out.println("Body: " + body);

        EmailHandler emailHandler = new EmailHandler();
        boolean flag = emailHandler.sendEmail(toWho, subject, body);
        if (flag) {
            System.out.println("\nEmail sent to " + toWho + "!");
            protocol.sendAck();
        } else {
            System.out.println("\nError sending email to " + toWho + "!");
            protocol.sendErr();
        }
    }

    /**
     * Handles list candidate applications request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode6() throws IOException {
        System.out.println("Code 6 received! -> List Candidate Applications Request\n");

        int lenghtEmail = readLenght();

        byte[] emailBytes = inData.readNBytes(lenghtEmail);
        String email = new String(emailBytes, StandardCharsets.UTF_8);
        System.out.println("Email: " + email);

        Iterable<Application> allApplications = applicationService.allApplications();
        List<Application> candidateApplications = new ArrayList<>();
        List<String> candidateApplicationsDTO = new ArrayList<>();

        Candidate candidate = candidateService.findCandidateByEmail(email);

        for (Application application : allApplications) {
            if (application.candidate().equals(candidate)) {
                candidateApplications.add(application);
            }
        }

        List<ApplicationsDTO> applicationsDTOS = applicationsDTOS(candidateApplications);

        for (ApplicationsDTO applicationDTO : applicationsDTOS) {
            String string = applicationDTO.toString();
            candidateApplicationsDTO.add(string);
        }

        if (candidateApplications.isEmpty() || candidateApplicationsDTO.isEmpty()) {
            protocol.sendErr();

        } else {
            String applicationsJSON = new Gson().toJson(candidateApplicationsDTO);
            boolean flag = protocol.receiveListApplications(applicationsJSON);

            if (flag) {
                System.out.println("\nApplications Listed!");
            } else {
                System.out.println("\nError listing applications!");
                protocol.sendErr();
            }
        }

    }

    /**
     * Handles list customer job openings request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode7() throws IOException {
        System.out.println("Code 7 received! -> List Customer Job Openings request\n");

        int lengthEmail = readLenght();

        byte[] emailBytes = inData.readNBytes(lengthEmail);
        String email = new String(emailBytes, StandardCharsets.UTF_8);
        System.out.println("Email: " + email);

        List<JobOpening> allJobOpenings = (List<JobOpening>) jobOpeningService.allJobOpenings();

        List<JobOpening> customerJobOpenings1 = new ArrayList<>();

        List<String> customerJobOpenings = new ArrayList<>();


        Customer customer = customerService.findCustomerByEmail(email);

        for (JobOpening jobOpening : allJobOpenings) {
            if (jobOpening.customer().equals(customer)) {
                customerJobOpenings1.add(jobOpening);
            }
        }

        List<JobOpeningDTO> jobOpeningDTOs = jobOpeningDTOService.jobOpeningDTOS(customerJobOpenings1);

        for (JobOpeningDTO jobOpeningDTO : jobOpeningDTOs) {
            String string = jobOpeningDTO.toString();
            customerJobOpenings.add(string);
        }

        String json = new Gson().toJson(customerJobOpenings);

        boolean flag = protocol.receiveJobOpeningLists(json);

        if (flag) {
            System.out.println("\nJob Openings Listed!");
        } else {
            System.out.println("\nError listing job openings!");
            protocol.sendErr();
        }

    }

    /**
     * Handles list candidate new notifications request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode8() throws IOException {
        System.out.println("Code 8 received! -> List Candidate new notifications request\n");

        int lengthEmail = readLenght();

        byte[] emailBytes = inData.readNBytes(lengthEmail);
        String email = new String(emailBytes, StandardCharsets.UTF_8);
        System.out.println("Email: " + email);

        Candidate candidate = candidateService.findCandidateByEmail(email);

        listNewCandidateNotifications(candidate);

        new Thread(() -> {
            try {
                checkForNewNotifications(candidate);
            } catch (IOException e) {
                LOGGER.error("An error occurred during notification checks. ", e);
            }
        }).start();
    }

    /**
     * Handles list candidate old notifications request.
     *
     * @throws IOException If an I/O error occurs
     */
    private void handleCode9() throws IOException {
        System.out.println("Code 9 received! -> List Candidate old notifications request\n");

        int lengthEmail = readLenght();

        byte[] emailBytes = inData.readNBytes(lengthEmail);
        String email = new String(emailBytes, StandardCharsets.UTF_8);
        System.out.println("Email: " + email);

        Candidate candidate = candidateService.findCandidateByEmail(email);

        listOldCandidateNotifications(candidate);
    }

    /**
     * Lists new notifications for the given candidate.
     *
     * @param candidate The candidate whose notifications are to be listed
     * @throws IOException If an I/O error occurs
     */
    private void listNewCandidateNotifications(Candidate candidate) throws IOException {
        List<Notification> allNotifications = (List<Notification>) notificationService.allNotifications();

        List<String> candidateNotifications = new ArrayList<>();

        for (Notification notification : allNotifications) {
            if (notification.candidate().equals(candidate)) {
                if (!notification.read()) {
                    candidateNotifications.add(notification.toString());
                    notificationService.UpdateBoolean(notification);
                }
            }
        }

        String json = new Gson().toJson(candidateNotifications);

        boolean flag = protocol.receiveNewNotificationsList(json);

        if (flag) {
            System.out.println("\nNotifications Listed!");
        } else {
            System.out.println("\nError listing notifications!");
            protocol.sendErr();
        }
    }

    /**
     * Lists old notifications for the given candidate.
     *
     * @param candidate The candidate whose notifications are to be listed
     * @throws IOException If an I/O error occurs
     */
    private void listOldCandidateNotifications(Candidate candidate) throws IOException {
        List<Notification> allNotifications = (List<Notification>) notificationService.allNotifications();

        List<String> candidateNotifications = new ArrayList<>();

        for (Notification notification : allNotifications) {
            if (notification.candidate().equals(candidate)) {
                if (notification.read()) {
                    candidateNotifications.add(notification.toString());
                }
            }
        }

        String json = new Gson().toJson(candidateNotifications);

        boolean flag = protocol.receiveOldNotificationsList(json);

        if (flag) {
            System.out.println("\nNotifications Listed!");
        } else {
            System.out.println("\nError listing notifications!");
            protocol.sendErr();
        }
    }

    /**
     * Checks for new notifications periodically and sends them to the candidate.
     *
     * @param candidate The candidate whose notifications are to be checked
     * @throws IOException If an I/O error occurs
     */
    private void checkForNewNotifications(Candidate candidate) throws IOException {
        while (true) {
            try {
                List<Notification> pendingNotifications = notificationService.findNotificationsByCandidate(candidate);

                if (!pendingNotifications.isEmpty()) {
                    List<String> notificationMessages = new ArrayList<>();
                    for (Notification notification : pendingNotifications) {
                        notificationMessages.add(notification.toString());
                    }
                    String json = new Gson().toJson(notificationMessages);
                    boolean success = protocol.receiveNewNotificationsList(json);
                    if (success) {
                        System.out.println("Notifications sent successfully!");
                    } else {
                        System.out.println("Error sending notifications!");
                    }
                }

                Thread.sleep(2500000);
            } catch (SocketException e) {
                System.err.println("SocketException: Connection lost or closed. Stopping notification checks.");
                break;
            } catch (IOException e) {
                LOGGER.error("An error occurred during notification checks. ", e);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException occurred: " + e.getMessage());
                LOGGER.error("An error occurred during notification checks. ", e);
            }
        }
    }

    /**
     * Reads the length of the data from the input stream.
     *
     * @return The length of the data
     * @throws IOException If an I/O error occurs
     */
    private int readLenght() throws IOException {
        int dataLenL = new UnsignedInteger(inData.readByte()).positiveValue();
        int dataLenM = new UnsignedInteger(inData.readByte()).positiveValue();

        return 256 * dataLenM + dataLenL;
    }

    /**
     * Converts a list of applications to a list of ApplicationsDTOs.
     *
     * @param applications The list of applications
     * @return The list of ApplicationsDTOs
     */
    public List<ApplicationsDTO> applicationsDTOS(List<Application> applications) {
        List<ApplicationsDTO> applicationsDTOS = new ArrayList<>();
        for (Application application : applications) {
            ApplicationsDTO applicationDTO = applicationsDTOService.toDTO(application);
            applicationsDTOS.add(applicationDTO);
        }

        return applicationsDTOS;

    }

}
