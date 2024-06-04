package core.services;

import core.domain.application.Application;
import core.protocol.Jobs4UProtocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CandidateApplicationsService {
    ApplicationService applicationService = new ApplicationService();

    public List<Application> requestApplications(String email) {
        try {
            Socket socket = new Socket("127.0.0.1", 2005);
            DataInputStream inData = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendApplications(email);

            byte list = inData.readByte();
            byte[] listApp = inData.readNBytes(list);

            List<String> listApplications = new ArrayList<>();
            String application = new String(listApp, StandardCharsets.UTF_8);
            listApplications.add(application);

            String[] appEntries = application.replaceAll("[\\[\\]]", "").split(",");
            List<Application> applications = new ArrayList<>();
            List<Application> allApplications = (List<Application>) applicationService.allApplications();


            for (String entry : appEntries) {
                String cleanedEntry = entry.replaceAll("[^0-9]", "");
                if (!cleanedEntry.isEmpty()) {
                    int id = Integer.parseInt(cleanedEntry);

                    for (Application application1 : allApplications) {
                        if (application1.identity().equals(id)) {
                            applications.add(application1);
                        }
                    }

                } else {
                    System.out.println("You don't have any applications! ");
                }
            }
            socket.close();
            return applications;

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
    }
}
