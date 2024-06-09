package plugin.interviewModule;

import plugin.interviewModule.autogen.InterviewModelGrammarBaseVisitor;
import plugin.interviewModule.autogen.InterviewModelGrammarParser;

import java.util.ArrayList;
import java.util.List;

/**
 * The ResponsesVisitor class extends the ANTLR-generated base visitor class to collect candidate responses
 * from the parse tree. It stores the responses in a list and provides a method to retrieve them.
 *
 * @author Miguel Cardoso
 */
class ResponsesVisitor extends InterviewModelGrammarBaseVisitor<Object> {
    private final List<String> candidateResponses = new ArrayList<>();

    /**
     * Visits the question type node and delegates to the appropriate method based on the question type.
     *
     * @param ctx the context of the question type node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionType(InterviewModelGrammarParser.QuestionTypeContext ctx) {
        int endIndex = ctx.getText().indexOf(":");
        String questionType = ctx.getText().substring(0, endIndex).trim();

        switch (questionType) {
            case "Date":
                return visit(ctx.questionDate());
            case "Decimal Number":
                return visit(ctx.questionDecimalNumber());
            case "Integer Number":
                return visit(ctx.questionIntegerNumber());
            case "Multiple Choice":
                return visit(ctx.questionMultipleChoice());
            case "Numeric Range":
                return visit(ctx.questionNumericRange());
            case "Single Choice":
                return visit(ctx.questionSingleChoice());
            case "Short Answer":
                return visit(ctx.questionShortAnswer());
            case "Time":
                return visit(ctx.questionTime());
            case "True/False":
                return visit(ctx.questionTrueFalse());
            default:
                System.out.println("Invalid Question Type");
        }

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Visits the answer date node and adds the response to the list.
     *
     * @param ctx the context of the answer date node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerDate(InterviewModelGrammarParser.AnswerDateContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer decimal node and adds the response to the list.
     *
     * @param ctx the context of the answer decimal node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerDecimal(InterviewModelGrammarParser.AnswerDecimalContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer integer node and adds the response to the list.
     *
     * @param ctx the context of the answer integer node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerInteger(InterviewModelGrammarParser.AnswerIntegerContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer multiple choice node and adds the response to the list.
     *
     * @param ctx the context of the answer multiple choice node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerMultipleChoice(InterviewModelGrammarParser.AnswerMultipleChoiceContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer numeric range node and adds the response to the list.
     *
     * @param ctx the context of the answer numeric range node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerNumericRange(InterviewModelGrammarParser.AnswerNumericRangeContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer single choice node and adds the response to the list.
     *
     * @param ctx the context of the answer single choice node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerSingleChoice(InterviewModelGrammarParser.AnswerSingleChoiceContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer short text node and adds the response to the list.
     *
     * @param ctx the context of the answer short text node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerShortText(InterviewModelGrammarParser.AnswerShortTextContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer time node and adds the response to the list.
     *
     * @param ctx the context of the answer time node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerTime(InterviewModelGrammarParser.AnswerTimeContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the answer true/false node and adds the response to the list.
     *
     * @param ctx the context of the answer true/false node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerTrueFalse(InterviewModelGrammarParser.AnswerTrueFalseContext ctx) {
        candidateResponses.add(ctx.getText());
        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Retrieves the list of candidate responses collected by this visitor.
     *
     * @return the list of candidate responses
     */
    public List<String> retrieveResponses() {
        return candidateResponses;
    }
}
