package core.application.controllers;

import core.domain.interview.InterviewModel;
import core.services.InterviewService;

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

}
