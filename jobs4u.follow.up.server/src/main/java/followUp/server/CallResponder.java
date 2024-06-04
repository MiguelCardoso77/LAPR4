package followUp.server;

import core.domain.email.EmailHandler;
import core.domain.user.Jobs4URoles;
import core.services.ApplicationService;
import core.services.CandidateService;
import infrastructure.authz.AuthenticationCredentialHandler;
import infrastructure.authz.CredentialHandler;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CallResponder extends Handler {
    private final ApplicationService applicationService = new ApplicationService();
    private final CandidateService candidateService = new CandidateService();

    public CallResponder(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public void handle() {
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
                default:
                    System.out.println("Invalid code received!");
                    protocol.sendErr();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleCode0() throws IOException {
        System.out.println("Code 0 received! -> COMMTEST\n");
        protocol.sendAck();
    }

    private void handleCode1() throws IOException {
        System.out.println("Code 1 received! -> DISCONNECT\n");
    }

    private void handleCode2() throws IOException {
        System.out.println("Code 2 received! -> ACK\n");
    }

    private void handleCode3() throws IOException {
        System.out.println("Code 3 received! -> ERR\n");
    }

    private void handleCode4() throws IOException, ClassNotFoundException {
        System.out.println("Code 4 received! -> Authentication Request\n");

        byte emailDataLenL = inData.readByte();
        byte emailDataLenM = inData.readByte();

        byte lenghtEmail = (byte) (256 * emailDataLenM + emailDataLenL);

        byte[] emailBytes = inData.readNBytes(lenghtEmail);
        String email = new String(emailBytes, StandardCharsets.UTF_8);
        System.out.println("Email: " + email);

        byte passwordDataLenL = inData.readByte();
        byte passwordDataLenM = inData.readByte();

        byte lenghtPassword = (byte) (256 * passwordDataLenM + passwordDataLenL);

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

    private void handleCode5() throws IOException {
        System.out.println("Code 5 received! -> Email Request\n");

        byte toWhoDataLenL = inData.readByte();
        byte toWhoDataLenM = inData.readByte();

        byte lenghtToWho = (byte) (256 * toWhoDataLenM + toWhoDataLenL);

        byte[] toWhoBytes = inData.readNBytes(lenghtToWho);
        String toWho = new String(toWhoBytes, StandardCharsets.UTF_8);
        System.out.println("To: " + toWho);

        byte subjectDataLenL = inData.readByte();
        byte subjectDataLenM = inData.readByte();

        byte lenghtSubject = (byte) (256 * subjectDataLenM + subjectDataLenL);

        byte[] subjectBytes = inData.readNBytes(lenghtSubject);
        String subject = new String(subjectBytes, StandardCharsets.UTF_8);
        System.out.println("Subject: " + subject);

        byte bodyDataLenL = inData.readByte();
        byte bodyDataLenM = inData.readByte();

        byte lenghtBody = (byte) (256 * bodyDataLenM + bodyDataLenL);

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
}
