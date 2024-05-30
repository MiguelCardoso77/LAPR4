package plugin.requirements.autogen;// Generated from C:/Users/migue/IdeaProjects/lprog-plugin-2dd5/requirements/src/main/resources/RequirementsGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RequirementsGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RequirementsGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(RequirementsGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#requirements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirements(RequirementsGrammarParser.RequirementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#requirementsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirementsList(RequirementsGrammarParser.RequirementsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#requirementType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirementType(RequirementsGrammarParser.RequirementTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#academicDegree}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcademicDegree(RequirementsGrammarParser.AcademicDegreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#academicDegreeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcademicDegreeType(RequirementsGrammarParser.AcademicDegreeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#languages}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguages(RequirementsGrammarParser.LanguagesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#languagesSelection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguagesSelection(RequirementsGrammarParser.LanguagesSelectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#languagesType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLanguagesType(RequirementsGrammarParser.LanguagesTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#programmingLanguages}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgrammingLanguages(RequirementsGrammarParser.ProgrammingLanguagesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#programmingLanguagesType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgrammingLanguagesType(RequirementsGrammarParser.ProgrammingLanguagesTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#programmingTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgrammingTypes(RequirementsGrammarParser.ProgrammingTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#yearsOfExperience}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYearsOfExperience(RequirementsGrammarParser.YearsOfExperienceContext ctx);
	/**
	 * Visit a parse tree produced by {@link RequirementsGrammarParser#yearsOfExperienceType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitYearsOfExperienceType(RequirementsGrammarParser.YearsOfExperienceTypeContext ctx);
}