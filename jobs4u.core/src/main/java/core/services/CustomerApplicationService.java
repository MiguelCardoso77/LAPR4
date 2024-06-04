package core.services;

import core.domain.jobOpening.JobOpeningDTO;
import core.protocol.Jobs4UProtocol;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CustomerApplicationService {
    /**
    CustomerJobOpeningsService customerJobOpeningsService = new CustomerJobOpeningsService();

    public void requestJobOpenings(String email){
        try{
            Socket socket = new Socket("localhost", 12345);
            DataInputStream in = new DataInputStream(socket.getInputStream());

            Jobs4UProtocol protocol = new Jobs4UProtocol(socket);
            protocol.sendRequestJobOpenings(email);

            byte list = in.readByte();
            byte[] jobOpenings = in.readNBytes(list);

            List<String> listJobOpenings = new ArrayList<>();
            String JobOpening = new String(jobOpenings, StandardCharsets.UTF_8);
            listJobOpenings.add(JobOpening);

            // String[] appEntries = jobOpenings.replaceAll("[\\[\\]]", "").split(", ");
            List<JobOpeningDTO> jobOpeningDTOs = new ArrayList<>();
            List<JobOpeningDTO> allJobOpenings = (List<JobOpeningDTO> customerJobOpeningsService.);

            for(String entry : appEntries){
                String cleanedEntry = entry.replaceAll("[^0-9]", "");
                if(!cleanedEntry.isEmpty()){
                    int id = Integer.parseInt(cleanedEntry);

                    for(JobOpeningDTO jobOpeningDTO : allJobOpenings){
                        if(jobOpeningDTO.jobReference() == id){
                            jobOpeningDTOs.add(jobOpeningDTO);
                        }
                    }
                }else{
                    System.out.println("Invalid entry: " + entry);
                }
            }

            System.out.printf("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */
}
