package plugin.requirements;

import plugin.requirements.autogen.RequirementsGrammarBaseVisitor;
import plugin.requirements.autogen.RequirementsGrammarParser;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * The RequirementsVisitor class extends the ANTLR-generated base visitor class to validate candidate responses
 * against job requirements. It checks various requirements such as academic degree, languages, programming languages,
 * and years of experience.
 *
 * @author Miguel Cardoso
 */
public class RequirementsVisitor extends RequirementsGrammarBaseVisitor<Object> {
    private final Map<String, String> candidateResponses;
    private boolean requirementsMet;

    /**
     * Constructor to initialize the candidate responses and set the initial state of requirementsMet to true.
     *
     * @param cResponses the map of candidate responses
     */
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

    /**
     * Visits the academic degree context.
     *
     * @param ctx the academic degree context
     * @return the result of visiting the academic degree context
     */
    @Override
    public Object visitAcademicDegree(RequirementsGrammarParser.AcademicDegreeContext ctx) {
        System.out.println("Academic Degree: ");
        return visitChildren(ctx);
    }

    /**
     * Visits the languages context.
     *
     * @param ctx the languages context
     * @return the result of visiting the languages context
     */
    @Override
    public Object visitLanguages(RequirementsGrammarParser.LanguagesContext ctx) {
        System.out.println("Languages:");
        return visitChildren(ctx);
    }

    /**
     * Visits the programming languages context.
     *
     * @param ctx the programming languages context
     * @return the result of visiting the programming languages context
     */
    @Override
    public Object visitProgrammingLanguages(RequirementsGrammarParser.ProgrammingLanguagesContext ctx) {
        System.out.println("Programming Languages:");
        return visitChildren(ctx);
    }

    /**
     * Visits the years of experience context.
     *
     * @param ctx the years of experience context
     * @return the result of visiting the years of experience context
     */
    @Override
    public Object visitYearsOfExperience(RequirementsGrammarParser.YearsOfExperienceContext ctx) {
        System.out.println("Years of Experience:");
        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Visits the academic degree type context and checks if the candidate meets the academic degree requirement.
     *
     * @param ctx the academic degree type context
     * @return the result of visiting the academic degree type context
     */
    @Override
    public Object visitAcademicDegreeType(RequirementsGrammarParser.AcademicDegreeTypeContext ctx) {
        String candidateRequirement = candidateResponses.get("Academic Degree");
        if (candidateRequirement == null) {
            requirementsMet = false;
        } else {
            System.out.println("Required Degree -> " + ctx.getText());
            System.out.println("Candidate Degree -> " + candidateRequirement);

            switch (ctx.getText()) {
                case ("Bachelor"):
                    requirementsMet = candidateRequirement.equals("Bachelor") || candidateRequirement.equals("Master") || candidateRequirement.equals("Doctorate");
                    break;
                case ("Master"):
                    requirementsMet = candidateRequirement.equals("Master") || candidateRequirement.equals("Doctorate");
                    break;
                case ("Doctorate"):
                    requirementsMet = candidateRequirement.equals("Doctorate");
                    break;
            }
        }

        return visitChildren(ctx);
    }

    /**
     * Visits the languages type context and checks if the candidate meets the language requirements.
     *
     * @param ctx the languages type context
     * @return the result of visiting the languages type context
     */
    @Override
    public Object visitLanguagesType(RequirementsGrammarParser.LanguagesTypeContext ctx) {
        String requiredLanguages = ctx.getText();
        String candidateRequirement = candidateResponses.get("Languages");

        if (candidateRequirement == null) {
            requirementsMet = false;
        } else {
            System.out.println("Required Languages -> " + ctx.getText());
            System.out.println("Candidate Languages -> " + candidateRequirement);
            List<String> requiredLanguagesList = Arrays.asList(requiredLanguages.split(","));
            List<String> candidateLanguagesList = Arrays.asList(candidateRequirement.split(","));

            if (!new HashSet<>(candidateLanguagesList).containsAll(requiredLanguagesList)) {
                System.out.println("Languages Requirement Not Met");
                requirementsMet = false;
            } else {
                System.out.println("Languages Requirement Met");
            }
        }

        return visitChildren(ctx);
    }

    /**
     * Visits the programming languages type context and checks if the candidate meets the programming languages requirements.
     *
     * @param ctx the programming languages type context
     * @return the result of visiting the programming languages type context
     */
    @Override
    public Object visitProgrammingLanguagesType(RequirementsGrammarParser.ProgrammingLanguagesTypeContext ctx) {
        String requiredLanguages = ctx.getText();
        String candidateRequirement = candidateResponses.get("Programming Languages");

        if (candidateRequirement == null) {
            requirementsMet = false;
        } else {
            System.out.println("Required Programming Languages -> " + ctx.getText());
            System.out.println("Candidate Programming Languages -> " + candidateRequirement);

            List<String> requiredLanguagesList = Arrays.asList(requiredLanguages.split(","));
            List<String> candidateLanguagesList = Arrays.asList(candidateRequirement.split(","));

            if (!new HashSet<>(candidateLanguagesList).containsAll(requiredLanguagesList)) {
                System.out.println("Programming Languages Requirement Not Met");
                requirementsMet = false;
            } else {
                System.out.println("Programming Languages Requirement Met");
            }
        }

        return visitChildren(ctx);
    }

    /**
     * Visits the years of experience type context and checks if the candidate meets the years of experience requirement.
     *
     * @param ctx the years of experience type context
     * @return the result of visiting the years of experience type context
     */
    @Override
    public Object visitYearsOfExperienceType(RequirementsGrammarParser.YearsOfExperienceTypeContext ctx) {
        String candidateRequirement = candidateResponses.get("Years of Experience");

        if (candidateRequirement == null) {
            requirementsMet = false;

        } else {
            System.out.println("Required Years -> " + ctx.getText());
            System.out.println("Candidate Years -> " + candidateRequirement);

            if (Integer.parseInt(candidateRequirement) < Integer.parseInt(ctx.getText())) {
                System.out.println("Years of Experience Requirement Not Met");
                requirementsMet = false;
            } else {
                System.out.println("Years of Experience Requirement Met");
            }
        }

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Checks if the candidate meets all the requirements.
     *
     * @return true if all requirements are met, false otherwise
     */
    public boolean checkRequirements() {
        return requirementsMet;
    }
}
