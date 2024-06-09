package plugin.requirements;

import plugin.requirements.autogen.RequirementsGrammarBaseVisitor;
import plugin.requirements.autogen.RequirementsGrammarParser;

import java.util.ArrayList;
import java.util.List;

/**
 * The ResponseRequirementsVisitor class extends the ANTLR-generated base visitor class to extract candidate requirements
 * from a parsed grammar tree.
 *
 * @author Miguel Cardoso
 */
public class ResponseRequirementsVisitor extends RequirementsGrammarBaseVisitor<Object> {
    private final List<String> candidateRequirements =new ArrayList<>();

    /**
     * Visits the requirement type context and delegates to the specific requirement type visitor method.
     *
     * @param ctx the requirement type context
     * @return the result of visiting the specific requirement type
     */
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

    /**
     * Visits the academic degree context and adds the candidate requirement to the list.
     *
     * @param ctx the academic degree context
     * @return the result of visiting the academic degree context
     */
    @Override
    public Object visitAcademicDegree(RequirementsGrammarParser.AcademicDegreeContext ctx) {
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    /**
     * Visits the languages context and adds the candidate requirement to the list.
     *
     * @param ctx the languages context
     * @return the result of visiting the languages context
     */
    @Override
    public Object visitLanguages(RequirementsGrammarParser.LanguagesContext ctx) {
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    /**
     * Visits the programming languages context and adds the candidate requirement to the list.
     *
     * @param ctx the programming languages context
     * @return the result of visiting the programming languages context
     */
    @Override
    public Object visitProgrammingLanguages(RequirementsGrammarParser.ProgrammingLanguagesContext ctx) {
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    /**
     * Visits the years of experience context and adds the candidate requirement to the list.
     *
     * @param ctx the years of experience context
     * @return the result of visiting the years of experience context
     */
    @Override
    public Object visitYearsOfExperience(RequirementsGrammarParser.YearsOfExperienceContext ctx) {
        candidateRequirements.add(ctx.getText());

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Retrieves the candidate response requirements.
     *
     * @return the list of candidate response requirements
     */
    public List<String> retrieveResponseRequirements(){
        return candidateRequirements;
    }
}
