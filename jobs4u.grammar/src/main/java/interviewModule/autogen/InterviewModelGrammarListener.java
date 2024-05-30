package interviewModule.autogen;// Generated from C:/Users/migue/IdeaProjects/lprog-plugin-2dd5/interviewModel/src/main/resources/InterviewModelGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InterviewModelGrammarParser}.
 */
public interface InterviewModelGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(InterviewModelGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(InterviewModelGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questions}.
	 * @param ctx the parse tree
	 */
	void enterQuestions(InterviewModelGrammarParser.QuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questions}.
	 * @param ctx the parse tree
	 */
	void exitQuestions(InterviewModelGrammarParser.QuestionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionItem}.
	 * @param ctx the parse tree
	 */
	void enterQuestionItem(InterviewModelGrammarParser.QuestionItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionItem}.
	 * @param ctx the parse tree
	 */
	void exitQuestionItem(InterviewModelGrammarParser.QuestionItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionBody}.
	 * @param ctx the parse tree
	 */
	void enterQuestionBody(InterviewModelGrammarParser.QuestionBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionBody}.
	 * @param ctx the parse tree
	 */
	void exitQuestionBody(InterviewModelGrammarParser.QuestionBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionType}.
	 * @param ctx the parse tree
	 */
	void enterQuestionType(InterviewModelGrammarParser.QuestionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionType}.
	 * @param ctx the parse tree
	 */
	void exitQuestionType(InterviewModelGrammarParser.QuestionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionDate}.
	 * @param ctx the parse tree
	 */
	void enterQuestionDate(InterviewModelGrammarParser.QuestionDateContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionDate}.
	 * @param ctx the parse tree
	 */
	void exitQuestionDate(InterviewModelGrammarParser.QuestionDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerDate}.
	 * @param ctx the parse tree
	 */
	void enterAnswerDate(InterviewModelGrammarParser.AnswerDateContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerDate}.
	 * @param ctx the parse tree
	 */
	void exitAnswerDate(InterviewModelGrammarParser.AnswerDateContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionDecimalNumber}.
	 * @param ctx the parse tree
	 */
	void enterQuestionDecimalNumber(InterviewModelGrammarParser.QuestionDecimalNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionDecimalNumber}.
	 * @param ctx the parse tree
	 */
	void exitQuestionDecimalNumber(InterviewModelGrammarParser.QuestionDecimalNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerDecimal}.
	 * @param ctx the parse tree
	 */
	void enterAnswerDecimal(InterviewModelGrammarParser.AnswerDecimalContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerDecimal}.
	 * @param ctx the parse tree
	 */
	void exitAnswerDecimal(InterviewModelGrammarParser.AnswerDecimalContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionIntegerNumber}.
	 * @param ctx the parse tree
	 */
	void enterQuestionIntegerNumber(InterviewModelGrammarParser.QuestionIntegerNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionIntegerNumber}.
	 * @param ctx the parse tree
	 */
	void exitQuestionIntegerNumber(InterviewModelGrammarParser.QuestionIntegerNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerInteger}.
	 * @param ctx the parse tree
	 */
	void enterAnswerInteger(InterviewModelGrammarParser.AnswerIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerInteger}.
	 * @param ctx the parse tree
	 */
	void exitAnswerInteger(InterviewModelGrammarParser.AnswerIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionMultipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterQuestionMultipleChoice(InterviewModelGrammarParser.QuestionMultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionMultipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitQuestionMultipleChoice(InterviewModelGrammarParser.QuestionMultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerMultipleChoice}.
	 * @param ctx the parse tree
	 */
	void enterAnswerMultipleChoice(InterviewModelGrammarParser.AnswerMultipleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerMultipleChoice}.
	 * @param ctx the parse tree
	 */
	void exitAnswerMultipleChoice(InterviewModelGrammarParser.AnswerMultipleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionNumericRange}.
	 * @param ctx the parse tree
	 */
	void enterQuestionNumericRange(InterviewModelGrammarParser.QuestionNumericRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionNumericRange}.
	 * @param ctx the parse tree
	 */
	void exitQuestionNumericRange(InterviewModelGrammarParser.QuestionNumericRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerNumericRange}.
	 * @param ctx the parse tree
	 */
	void enterAnswerNumericRange(InterviewModelGrammarParser.AnswerNumericRangeContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerNumericRange}.
	 * @param ctx the parse tree
	 */
	void exitAnswerNumericRange(InterviewModelGrammarParser.AnswerNumericRangeContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionSingleChoice}.
	 * @param ctx the parse tree
	 */
	void enterQuestionSingleChoice(InterviewModelGrammarParser.QuestionSingleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionSingleChoice}.
	 * @param ctx the parse tree
	 */
	void exitQuestionSingleChoice(InterviewModelGrammarParser.QuestionSingleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerSingleChoice}.
	 * @param ctx the parse tree
	 */
	void enterAnswerSingleChoice(InterviewModelGrammarParser.AnswerSingleChoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerSingleChoice}.
	 * @param ctx the parse tree
	 */
	void exitAnswerSingleChoice(InterviewModelGrammarParser.AnswerSingleChoiceContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionShortAnswer}.
	 * @param ctx the parse tree
	 */
	void enterQuestionShortAnswer(InterviewModelGrammarParser.QuestionShortAnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionShortAnswer}.
	 * @param ctx the parse tree
	 */
	void exitQuestionShortAnswer(InterviewModelGrammarParser.QuestionShortAnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerShortText}.
	 * @param ctx the parse tree
	 */
	void enterAnswerShortText(InterviewModelGrammarParser.AnswerShortTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerShortText}.
	 * @param ctx the parse tree
	 */
	void exitAnswerShortText(InterviewModelGrammarParser.AnswerShortTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionTime}.
	 * @param ctx the parse tree
	 */
	void enterQuestionTime(InterviewModelGrammarParser.QuestionTimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionTime}.
	 * @param ctx the parse tree
	 */
	void exitQuestionTime(InterviewModelGrammarParser.QuestionTimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerTime}.
	 * @param ctx the parse tree
	 */
	void enterAnswerTime(InterviewModelGrammarParser.AnswerTimeContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerTime}.
	 * @param ctx the parse tree
	 */
	void exitAnswerTime(InterviewModelGrammarParser.AnswerTimeContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questionTrueFalse}.
	 * @param ctx the parse tree
	 */
	void enterQuestionTrueFalse(InterviewModelGrammarParser.QuestionTrueFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questionTrueFalse}.
	 * @param ctx the parse tree
	 */
	void exitQuestionTrueFalse(InterviewModelGrammarParser.QuestionTrueFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answerTrueFalse}.
	 * @param ctx the parse tree
	 */
	void enterAnswerTrueFalse(InterviewModelGrammarParser.AnswerTrueFalseContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answerTrueFalse}.
	 * @param ctx the parse tree
	 */
	void exitAnswerTrueFalse(InterviewModelGrammarParser.AnswerTrueFalseContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#score}.
	 * @param ctx the parse tree
	 */
	void enterScore(InterviewModelGrammarParser.ScoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#score}.
	 * @param ctx the parse tree
	 */
	void exitScore(InterviewModelGrammarParser.ScoreContext ctx);
}