package core.application.controllers;

import core.domain.interviewModel.InterviewModel;
import core.services.InterviewModelService;

public class ListInterviewModelsController {

        private final InterviewModelService service = new InterviewModelService();

        public Iterable<InterviewModel> allInterviewModels(){
                return service.allInterviewModels();
        }

}
