package backoffice.presentation.jobRequirementsSpecifications;

import core.application.controllers.ListJobOpeningController;
import core.application.controllers.ListJobRequirementsSpecificationController;
import eapli.framework.presentation.console.AbstractUI;

public class SelectRequirementsSpecificationUI extends AbstractUI {

    private final ListJobOpeningController listJobOpeningController = new ListJobOpeningController();
    private final ListJobRequirementsSpecificationController listJobRequirementsSpecification = new ListJobRequirementsSpecificationController();

    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Select Requirements Specifications";
    }
}