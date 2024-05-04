package core.application.controllers;

import core.domain.jobRequirementsSpecification.JobRequirementsSpecification;
import core.services.JobRequirementsService;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
/**
 * Controller class for managing job requirements specifications.
 * Provides methods for retrieving, finding, importing, and extracting job requirements specifications from files.
 * Acts as an intermediary between the presentation layer and the service layer.
 *
 * @author 1220812@isep.ipp.pt
 */
public class ListJobRequirementsSpecificationController {

    private final JobRequirementsService service = new JobRequirementsService();

    /**
     * Retrieves all job requirements specifications.
     *
     * @return An Iterable containing all job requirements specifications.
     */

    public Iterable<JobRequirementsSpecification> allJobRequirementsSpecification(){
        return service.allJobRequirementsSpecification();
    }

    /**
     * Finds a job requirements specification by its ID.
     *
     * @param id The ID of the job requirements specification to find.
     * @return The found JobRequirementsSpecification object, or null if not found.
     */

    public JobRequirementsSpecification findJobRequirementSpecification(Integer id){
        return service.findJobRequirementsSpecification(id);
    }

    /**
     * Imports job requirements specifications from a file.
     *
     * @param path The path to the file containing job requirements specifications.
     * @return A list of strings representing the imported job requirements specifications.
     * @throws FileNotFoundException if the file specified by the path is not found.
     */
    public static List<String> importRequirementsSpecification(Path path) throws FileNotFoundException {
        List<String> requirements = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String value = parts[1].trim();
                    requirements.add(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requirements;
    }
    /**
     * Extracts a job requirements specification from imported data.
     *
     * @param data A list of strings representing the imported data.
     * @return The extracted JobRequirementsSpecification object.
     */
    public JobRequirementsSpecification extractSpecificationFromFile(List<String> data){
        String academicDegree = data.get(0);
        Integer experience =  Integer.parseInt(data.get(2));
        String knowledge = data.get(1);
        return service.registerJobRequirement(academicDegree, knowledge, experience);
    }

}