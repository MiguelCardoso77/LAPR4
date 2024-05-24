package followUp.server;

import core.domain.application.Application;
import core.domain.candidate.Candidate;
import core.services.ApplicationService;
import core.services.CandidateService;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
            int code = (int) input.readObject();

            if (code == 4) {
                System.out.println("Code 4 received!");
                String password = input.readObject().toString();

                //TODO: Implement login
            }

            if (code == 5) {
                System.out.println("Code 5 received!");
                String email = input.readObject().toString();

                Candidate candidate = candidateService.findCandidateByEmail(email);
                String list = listCandidateApplications(candidate);
                byte[] byteArray = list.getBytes(StandardCharsets.UTF_8);
                int sizeInBytes = byteArray.length;

                output.writeObject(list);
            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String listCandidateApplications(Candidate candidate) {
        List<Application> applicationList = applicationService.applicationsByCandidate(candidate);

        StringBuilder sb = new StringBuilder();
        for (Application app : applicationList) {
            sb.append(app.toString()).append("\n");
        }

        return sb.toString();
    }
}
