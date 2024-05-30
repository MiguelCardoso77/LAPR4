package plugin.requirements;

import plugin.requirements.autogen.RequirementsGrammarBaseVisitor;
import plugin.requirements.autogen.RequirementsGrammarParser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RequirementsVisitor extends RequirementsGrammarBaseVisitor<Object> {
    private final Map<String, String> candidateResponses;
    private boolean requirementsMet;

    public RequirementsVisitor(Map<String, String> cResponses) {
        this.candidateResponses = cResponses;
        this.requirementsMet = true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitRequirementType(RequirementsGrammarParser.RequirementTypeContext ctx) {
        System.out.println("-------------------------------------------");

        int endIndex = ctx.getText().indexOf(":");
        String requirementType = ctx.getText().substring(0, endIndex).trim();

        switch (requirementType) {
            case "Academic Degree":
                return visit(ctx.academicDegree());
            case "Languages":
                return visit(ctx.languages());
            case "Programming Languages":
                return visit(ctx.programmingLanguages());
            case "Years of Experience":
                return visit(ctx.yearsOfExperience());
            default:
                System.out.println("Invalid Requirement Type");
        }

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitAcademicDegree(RequirementsGrammarParser.AcademicDegreeContext ctx) {
        System.out.println("Academic Degree: ");
        return visitChildren(ctx);
    }

    @Override
    public Object visitLanguages(RequirementsGrammarParser.LanguagesContext ctx) {
        System.out.println("Languages:");
        return visitChildren(ctx);
    }

    @Override
    public Object visitProgrammingLanguages(RequirementsGrammarParser.ProgrammingLanguagesContext ctx) {
        System.out.println("Programming Languages:");

        return visitChildren(ctx);
    }

    @Override
    public Object visitYearsOfExperience(RequirementsGrammarParser.YearsOfExperienceContext ctx) {
        System.out.println("Years of Experience:");
        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitAcademicDegreeType(RequirementsGrammarParser.AcademicDegreeTypeContext ctx) {
        String candidateRequirement = candidateResponses.get("Academic Degree");
        System.out.println("Required Degree -> " + ctx.getText());
        System.out.println("Candidate Degree -> " + candidateRequirement);

        if (ctx.getText().equals(candidateRequirement)) {
            System.out.println("Academic Degree Requirement Met");
            if (!requirementsMet) {
                requirementsMet = false;
            } else {
                requirementsMet = true;
            }
        } else {
            System.out.println("Academic Degree Requirement Not Met");
            requirementsMet = false;
        }

        return visitChildren(ctx);
    }

    @Override
    public Object visitLanguagesType(RequirementsGrammarParser.LanguagesTypeContext ctx) {
        String requiredLanguages = ctx.getText();
        String candidateRequirement = candidateResponses.get("Languages");
        System.out.println("Required Languages -> " + ctx.getText());
        System.out.println("Candidate Languages -> " + candidateRequirement);

        List<String> requiredLanguagesList = Arrays.asList(requiredLanguages.split(","));
        List<String> candidateLanguagesList = Arrays.asList(candidateRequirement.split(","));

        if (candidateLanguagesList.containsAll(requiredLanguagesList)) {
            System.out.println("Languages Requirement Met");
            if (!requirementsMet) {
                requirementsMet = false;
            } else {
                requirementsMet = true;
            }
        } else {
            System.out.println("Languages Requirement Not Met");
            requirementsMet = false;
        }

        return visitChildren(ctx);
    }

    @Override
    public Object visitProgrammingLanguagesType(RequirementsGrammarParser.ProgrammingLanguagesTypeContext ctx) {
        String requiredLanguages = ctx.getText();
        String candidateRequirement = candidateResponses.get("Programming Languages");
        System.out.println("Required Programming Languages -> " + ctx.getText());
        System.out.println("Candidate Programming Languages -> " + candidateRequirement);

        List<String> requiredLanguagesList = Arrays.asList(requiredLanguages.split(","));
        List<String> candidateLanguagesList = Arrays.asList(candidateRequirement.split(","));

        if (candidateLanguagesList.containsAll(requiredLanguagesList)) {
            System.out.println("Programming Languages Requirement Met");
            if (!requirementsMet) {
                requirementsMet = false;
            } else {
                requirementsMet = true;
            }
        } else {
            System.out.println("Programming Languages Requirement Not Met");
            requirementsMet = false;
        }

        return visitChildren(ctx);
    }

    @Override
    public Object visitYearsOfExperienceType(RequirementsGrammarParser.YearsOfExperienceTypeContext ctx) {
        String candidateRequirement = candidateResponses.get("Years of Experience");
        System.out.println("Required Years -> " + ctx.getText());
        System.out.println("Candidate Years -> " + candidateRequirement);

        if (Integer.parseInt(candidateRequirement) >= Integer.parseInt(ctx.getText())) {
            System.out.println("Years of Experience Requirement Met");
            if (!requirementsMet) {
                requirementsMet = false;
            } else {
                requirementsMet = true;
            }
        } else {
            System.out.println("Years of Experience Requirement Not Met");
            requirementsMet = false;
        }

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public boolean checkRequirements() {
        return requirementsMet;
    }
}
