package interviewModule.autogen;// Generated from C:/Users/migue/IdeaProjects/lprog-plugin-2dd5/interviewModel/src/main/resources/InterviewModelGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link InterviewModelGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface InterviewModelGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(InterviewModelGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestions(InterviewModelGrammarParser.QuestionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionItem(InterviewModelGrammarParser.QuestionItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionBody(InterviewModelGrammarParser.QuestionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionType(InterviewModelGrammarParser.QuestionTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionDate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionDate(InterviewModelGrammarParser.QuestionDateContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerDate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerDate(InterviewModelGrammarParser.AnswerDateContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionDecimalNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionDecimalNumber(InterviewModelGrammarParser.QuestionDecimalNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerDecimal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerDecimal(InterviewModelGrammarParser.AnswerDecimalContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionIntegerNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionIntegerNumber(InterviewModelGrammarParser.QuestionIntegerNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerInteger(InterviewModelGrammarParser.AnswerIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionMultipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionMultipleChoice(InterviewModelGrammarParser.QuestionMultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerMultipleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerMultipleChoice(InterviewModelGrammarParser.AnswerMultipleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionNumericRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionNumericRange(InterviewModelGrammarParser.QuestionNumericRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerNumericRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerNumericRange(InterviewModelGrammarParser.AnswerNumericRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionSingleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionSingleChoice(InterviewModelGrammarParser.QuestionSingleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerSingleChoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerSingleChoice(InterviewModelGrammarParser.AnswerSingleChoiceContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionShortAnswer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionShortAnswer(InterviewModelGrammarParser.QuestionShortAnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerShortText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerShortText(InterviewModelGrammarParser.AnswerShortTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionTime(InterviewModelGrammarParser.QuestionTimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerTime(InterviewModelGrammarParser.AnswerTimeContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questionTrueFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestionTrueFalse(InterviewModelGrammarParser.QuestionTrueFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answerTrueFalse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswerTrueFalse(InterviewModelGrammarParser.AnswerTrueFalseContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#score}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScore(InterviewModelGrammarParser.ScoreContext ctx);
}