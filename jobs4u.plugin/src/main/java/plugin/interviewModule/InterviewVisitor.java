package plugin.interviewModule;

import plugin.interviewModule.autogen.InterviewModelGrammarBaseVisitor;
import plugin.interviewModule.autogen.InterviewModelGrammarParser;

import java.util.List;

class InterviewVisitor extends InterviewModelGrammarBaseVisitor<Object> {
    private final List<String> candidateResponses;
    private int totalScore;
    private int currentScore;
    private int currentQuestionIndex;


    public InterviewVisitor(List<String> cResponses) {
        this.candidateResponses = cResponses;
        this.totalScore = 0;
        this.currentQuestionIndex = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitQuestionType(InterviewModelGrammarParser.QuestionTypeContext ctx) {
        System.out.println("-------------------------------------------");
        System.out.println("Question Line:");

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
    public Object visitQuestionBody(InterviewModelGrammarParser.QuestionBodyContext ctx) {
        System.out.println("Question: " + ctx.getText());
        return visitChildren(ctx);
    }

    @Override
    public String visitScore(InterviewModelGrammarParser.ScoreContext ctx) {
        System.out.println("Score: " + ctx.getText());

        return ctx.getText().replace("%", "").trim();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitQuestionDate(InterviewModelGrammarParser.QuestionDateContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.DATE().getText());
        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionDecimalNumber(InterviewModelGrammarParser.QuestionDecimalNumberContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.DECIMAL_NUMBER().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionIntegerNumber(InterviewModelGrammarParser.QuestionIntegerNumberContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.INTEGER_NUMBER().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionMultipleChoice(InterviewModelGrammarParser.QuestionMultipleChoiceContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.MULTIPLE_CHOICE().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionNumericRange(InterviewModelGrammarParser.QuestionNumericRangeContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.NUMERIC_RANGE().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionSingleChoice(InterviewModelGrammarParser.QuestionSingleChoiceContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.SINGLE_CHOICE().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionShortAnswer(InterviewModelGrammarParser.QuestionShortAnswerContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.SHORT_ANSWER().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionTime(InterviewModelGrammarParser.QuestionTimeContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.TIME().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    @Override
    public Object visitQuestionTrueFalse(InterviewModelGrammarParser.QuestionTrueFalseContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.TRUE_FALSE().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Object visitAnswerDate(InterviewModelGrammarParser.AnswerDateContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerDecimal(InterviewModelGrammarParser.AnswerDecimalContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerInteger(InterviewModelGrammarParser.AnswerIntegerContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerMultipleChoice(InterviewModelGrammarParser.AnswerMultipleChoiceContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerNumericRange(InterviewModelGrammarParser.AnswerNumericRangeContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerSingleChoice(InterviewModelGrammarParser.AnswerSingleChoiceContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerShortText(InterviewModelGrammarParser.AnswerShortTextContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerTime(InterviewModelGrammarParser.AnswerTimeContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    @Override
    public Object visitAnswerTrueFalse(InterviewModelGrammarParser.AnswerTrueFalseContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;

        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    public int findOutTotalScore() {
        return totalScore;
    }

    private void checkAnswer(String correctAnswer, String candidateAnswer) {
        System.out.println("Correct Answer: " + correctAnswer);
        System.out.println("Candidate Answer: " + candidateAnswer);

        if (correctAnswer.trim().equals(candidateAnswer.trim())) {
            System.out.println("Correct Answer");
            totalScore += currentScore;
        } else {
            System.out.println("Incorrect Answer");
        }
    }

}
