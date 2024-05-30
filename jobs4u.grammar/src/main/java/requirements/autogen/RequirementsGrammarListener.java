package requirements.autogen;// Generated from C:/Users/migue/IdeaProjects/lprog-plugin-2dd5/requirements/src/main/resources/RequirementsGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RequirementsGrammarParser}.
 */
public interface RequirementsGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(RequirementsGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(RequirementsGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#requirements}.
	 * @param ctx the parse tree
	 */
	void enterRequirements(RequirementsGrammarParser.RequirementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#requirements}.
	 * @param ctx the parse tree
	 */
	void exitRequirements(RequirementsGrammarParser.RequirementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#requirementsList}.
	 * @param ctx the parse tree
	 */
	void enterRequirementsList(RequirementsGrammarParser.RequirementsListContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#requirementsList}.
	 * @param ctx the parse tree
	 */
	void exitRequirementsList(RequirementsGrammarParser.RequirementsListContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#requirementType}.
	 * @param ctx the parse tree
	 */
	void enterRequirementType(RequirementsGrammarParser.RequirementTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#requirementType}.
	 * @param ctx the parse tree
	 */
	void exitRequirementType(RequirementsGrammarParser.RequirementTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#academicDegree}.
	 * @param ctx the parse tree
	 */
	void enterAcademicDegree(RequirementsGrammarParser.AcademicDegreeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#academicDegree}.
	 * @param ctx the parse tree
	 */
	void exitAcademicDegree(RequirementsGrammarParser.AcademicDegreeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#academicDegreeType}.
	 * @param ctx the parse tree
	 */
	void enterAcademicDegreeType(RequirementsGrammarParser.AcademicDegreeTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#academicDegreeType}.
	 * @param ctx the parse tree
	 */
	void exitAcademicDegreeType(RequirementsGrammarParser.AcademicDegreeTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#languages}.
	 * @param ctx the parse tree
	 */
	void enterLanguages(RequirementsGrammarParser.LanguagesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#languages}.
	 * @param ctx the parse tree
	 */
	void exitLanguages(RequirementsGrammarParser.LanguagesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#languagesSelection}.
	 * @param ctx the parse tree
	 */
	void enterLanguagesSelection(RequirementsGrammarParser.LanguagesSelectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#languagesSelection}.
	 * @param ctx the parse tree
	 */
	void exitLanguagesSelection(RequirementsGrammarParser.LanguagesSelectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#languagesType}.
	 * @param ctx the parse tree
	 */
	void enterLanguagesType(RequirementsGrammarParser.LanguagesTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#languagesType}.
	 * @param ctx the parse tree
	 */
	void exitLanguagesType(RequirementsGrammarParser.LanguagesTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#programmingLanguages}.
	 * @param ctx the parse tree
	 */
	void enterProgrammingLanguages(RequirementsGrammarParser.ProgrammingLanguagesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#programmingLanguages}.
	 * @param ctx the parse tree
	 */
	void exitProgrammingLanguages(RequirementsGrammarParser.ProgrammingLanguagesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#programmingLanguagesType}.
	 * @param ctx the parse tree
	 */
	void enterProgrammingLanguagesType(RequirementsGrammarParser.ProgrammingLanguagesTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#programmingLanguagesType}.
	 * @param ctx the parse tree
	 */
	void exitProgrammingLanguagesType(RequirementsGrammarParser.ProgrammingLanguagesTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#programmingTypes}.
	 * @param ctx the parse tree
	 */
	void enterProgrammingTypes(RequirementsGrammarParser.ProgrammingTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#programmingTypes}.
	 * @param ctx the parse tree
	 */
	void exitProgrammingTypes(RequirementsGrammarParser.ProgrammingTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#yearsOfExperience}.
	 * @param ctx the parse tree
	 */
	void enterYearsOfExperience(RequirementsGrammarParser.YearsOfExperienceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#yearsOfExperience}.
	 * @param ctx the parse tree
	 */
	void exitYearsOfExperience(RequirementsGrammarParser.YearsOfExperienceContext ctx);
	/**
	 * Enter a parse tree produced by {@link RequirementsGrammarParser#yearsOfExperienceType}.
	 * @param ctx the parse tree
	 */
	void enterYearsOfExperienceType(RequirementsGrammarParser.YearsOfExperienceTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link RequirementsGrammarParser#yearsOfExperienceType}.
	 * @param ctx the parse tree
	 */
	void exitYearsOfExperienceType(RequirementsGrammarParser.YearsOfExperienceTypeContext ctx);
}