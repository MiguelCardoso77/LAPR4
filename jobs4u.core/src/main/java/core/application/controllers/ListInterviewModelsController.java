package core.application.controllers;

import core.domain.interviewModel.InterviewModel;
import core.services.InterviewService;

public class ListInterviewModelsController {

        private final InterviewService service = new InterviewService();

        public Iterable<InterviewModel> allInterviewModels(){
                return service.allInterviewModels();
        }

}
