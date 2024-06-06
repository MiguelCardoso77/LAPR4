package plugin.requirements;

import plugin.requirements.autogen.RequirementsGrammarBaseVisitor;
import plugin.requirements.autogen.RequirementsGrammarParser;

import java.util.ArrayList;
import java.util.List;

public class ResponseRequirementsVisitor extends RequirementsGrammarBaseVisitor<Object> {

    private final List<String> candidateRequirements =new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitRequirementType(RequirementsGrammarParser.RequirementTypeContext ctx) {
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
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitLanguages(RequirementsGrammarParser.LanguagesContext ctx) {
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitProgrammingLanguages(RequirementsGrammarParser.ProgrammingLanguagesContext ctx) {
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitYearsOfExperience(RequirementsGrammarParser.YearsOfExperienceContext ctx) {
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////


    public List<String> retrieveResponseRequirements(){
        return candidateRequirements;
    }

}
