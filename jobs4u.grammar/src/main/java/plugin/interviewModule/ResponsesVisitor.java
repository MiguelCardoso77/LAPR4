package plugin.interviewModule;

import plugin.interviewModule.autogen.InterviewModelGrammarBaseVisitor;
import plugin.interviewModule.autogen.InterviewModelGrammarParser;

import java.util.ArrayList;
import java.util.List;

class ResponsesVisitor extends InterviewModelGrammarBaseVisitor<Object> {
    private final List<String> candidateResponses = new ArrayList<>();

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

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

    @Override
    public Object visitAnswerDate(InterviewModelGrammarParser.AnswerDateContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerDecimal(InterviewModelGrammarParser.AnswerDecimalContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerInteger(InterviewModelGrammarParser.AnswerIntegerContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerMultipleChoice(InterviewModelGrammarParser.AnswerMultipleChoiceContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerNumericRange(InterviewModelGrammarParser.AnswerNumericRangeContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerSingleChoice(InterviewModelGrammarParser.AnswerSingleChoiceContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerShortText(InterviewModelGrammarParser.AnswerShortTextContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerTime(InterviewModelGrammarParser.AnswerTimeContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerTrueFalse(InterviewModelGrammarParser.AnswerTrueFalseContext ctx) {
        candidateResponses.add(ctx.getText());

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public List<String> retrieveResponses() {
        return candidateResponses;
    }
}
