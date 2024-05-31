package followUp.server;

import core.domain.user.Jobs4URoles;
import core.protocol.ComProtocolV0;
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
            System.out.println("Version: " + version);

            byte code = inData.readByte();
            System.out.println("Code: " + code);

            if (code == 4) {
                handleCode4();
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleCode4() throws IOException, ClassNotFoundException {
        System.out.println("Code 4 received!");

        byte emailDataLenL = inData.readByte();
        System.out.println("Email L: " + emailDataLenL);

        byte emailDataLenM = inData.readByte();
        System.out.println("Email M: " + emailDataLenM);

        byte[] emailBytes = inData.readNBytes(emailDataLenL);
        String email = new String(emailBytes, StandardCharsets.UTF_8);
        System.out.println("Email: " + email);

        byte passwordDataLenL = inData.readByte();
        System.out.println("Password L: " + passwordDataLenL);

        byte passwordDataLenM = inData.readByte();
        System.out.println("Password M: " + passwordDataLenM);

        byte[] passwordBytes = inData.readNBytes(passwordDataLenL);
        String password = new String(passwordBytes, StandardCharsets.UTF_8);
        System.out.println("Password: " + password);

        CredentialHandler credentialHandler = new AuthenticationCredentialHandler();
        if (credentialHandler.authenticated(email, password, Jobs4URoles.CANDIDATE)) {
            protocol.sendAck();
        } else if (credentialHandler.authenticated(email, password, Jobs4URoles.CUSTOMER)) {
            protocol.sendAck();
        } else {
            protocol.sendErr();
        }
    }
}
