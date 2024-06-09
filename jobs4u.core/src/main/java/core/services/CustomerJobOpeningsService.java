package core.services;

import core.domain.jobOpening.JobOpening;
import core.domain.jobOpening.JobOpeningDTO;
import core.protocol.Jobs4UProtocol;
import core.protocol.ProtocolCodes;
import core.protocol.UnsignedInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
 * @author Diogo Ribeiro
 */
@Service
public class CustomerJobOpeningsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerJobOpeningsService.class);
    JobOpeningDTOService jobOpeningDTOService = new JobOpeningDTOService();

    /**
     * Requests job openings from the server and returns a list of matched job openings.
     *
     * @param email The email of the customer.
     * @return A list of matched job openings.
     */
    public List<String> requestJobOpenings(String email) {
        List<String> matchedJobOpenings = new ArrayList<>();
        try {
            Socket socket = new Socket("127.0.0.1", 2005);
            DataInputStream in = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendJobOpenings(email);

            in.readByte();
            byte code = in.readByte();

            if (code == ProtocolCodes.JOB_OPENINGS.code()) {
                int dataLenL = new UnsignedInteger(in.readByte()).positiveValue();
                int dataLenM = new UnsignedInteger(in.readByte()).positiveValue();

                int lengthData = (256 * dataLenM) + dataLenL;

                byte[] jobOpenings = in.readNBytes(lengthData);

                String jobOpening = new String(jobOpenings, StandardCharsets.UTF_8);

                String jobOpeningsArray = jobOpening.replaceAll("[\\[\\]\"]", "").trim();

                String[] appEntries = jobOpeningsArray.split(",");

                socket.close();

                return new ArrayList<>(List.of(appEntries));

            } else {
                System.out.println("No job openings found for the customer.");
                return null;
            }
        } catch (IOException e) {
            LOGGER.error("Error while requesting job openings from the server: {}", e.getMessage());
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
