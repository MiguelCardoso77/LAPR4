package core.services;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobOpeningDTO;
import core.protocol.Jobs4UProtocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling customer applications.
 * It communicates with the server to request job openings and processes the received data.
 *
 * @author 1220812@isep.ipp.pt
 */
public class CustomerJobOpeningsService {
    JobOpeningDTOService jobOpeningDTOService = new JobOpeningDTOService();
    JobOpeningService jobOpeningService = new JobOpeningService();

    /**
     * Requests job openings from the server and returns a list of matched job openings.
     *
     * @param email The email of the customer.
     * @return A list of matched job openings.
     */

    public List<JobOpeningDTO> requestJobOpenings(String email) {
        List<JobOpeningDTO> jobOpeningDTOs;
        List<JobOpeningDTO> matchedJobOpenings = new ArrayList<>();
        try {
            Socket socket = new Socket("127.0.0.1", 2005);
            DataInputStream in = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendJobOpenings(email);

            byte list = in.readByte();
            byte[] jobOpenings = in.readNBytes(list);

            String jobOpening = new String(jobOpenings, StandardCharsets.UTF_8);

            List<JobOpening> allJobOpenings = (List<JobOpening>) jobOpeningService.allJobOpenings();

            jobOpeningDTOs = jobOpeningDTOS(allJobOpenings);

            String[] appEntries = jobOpening.replaceAll("[\\[\\]\"]", "").split(",");

            for (String entry : appEntries) {
                String cleanedEntry = entry.trim();
                if (!cleanedEntry.isEmpty()) {
                    for (JobOpeningDTO jobOpeningDTO : jobOpeningDTOs) {
                        if (jobOpeningDTO.myJobReference().equals(cleanedEntry)) {
                            matchedJobOpenings.add(jobOpeningDTO);
                        }
                    }
                }else{
                    System.out.println("No job openings found for the customer.");
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matchedJobOpenings;
    }

    /**
     * Converts a list of JobOpening objects into a list of JobOpeningDTO objects.
     *
     * @param jobOpenings The list of JobOpening objects to convert.
     * @return A list of JobOpeningDTO objects.
     */
    public List<JobOpeningDTO> jobOpeningDTOS(List<JobOpening> jobOpenings) {
        List<JobOpeningDTO> jobOpeningDTOs = new ArrayList<>();
        for (JobOpening jobOpening : jobOpenings) {
            JobOpeningDTO jobOpeningDTO = jobOpeningDTOService.toDTO(jobOpening);
            jobOpeningDTOs.add(jobOpeningDTO);
        }
        return jobOpeningDTOs;
    }

}
