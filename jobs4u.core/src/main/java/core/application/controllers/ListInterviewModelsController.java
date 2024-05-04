package core.application.controllers;

import core.domain.interview.InterviewModel;
import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.InterviewService;
import core.services.JobInterviewService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ListInterviewModelsController {

        private final InterviewService service = new InterviewService();

        public Iterable<InterviewModel> allInterviewModels(){
                return service.allInterviewModels();
        }

        public static List<String> importInterviewModel(Path path) throws FileNotFoundException {
                List<String> interviews = new ArrayList<>();
                try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
                        String line;
                        br.readLine();
                        while ((line = br.readLine()) != null) {
                                String[] parts = line.split(":");
                                if (parts.length == 2) {
                                        String value = parts[1].trim();
                                        interviews.add(value);
                                }
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return interviews;
        }


        public InterviewModel extractInterviewModelFromFile(List<String> data){
                String model = data.get(0);
                return service.registerJobRequirement(model);
        }
}
