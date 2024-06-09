package plugin.interviewModule;

import plugin.interviewModule.autogen.InterviewModelGrammarBaseVisitor;
import plugin.interviewModule.autogen.InterviewModelGrammarParser;

import java.util.List;

/**
 * The InterviewVisitor class extends the ANTLR-generated base visitor class to process the interview model.
 * It visits the nodes of the parse tree, checks candidate answers, and computes the total score.
 *
 * @author Miguel Cardoso
 */
class InterviewVisitor extends InterviewModelGrammarBaseVisitor<Object> {
    private final List<String> candidateResponses;
    private int totalScore;
    private int currentScore;
    private int currentQuestionIndex;

    /**
     * Constructs an InterviewVisitor with the specified list of candidate responses.
     *
     * @param cResponses the list of candidate responses
     */
    public InterviewVisitor(List<String> cResponses) {
        this.candidateResponses = cResponses;
        this.totalScore = 0;
        this.currentQuestionIndex = 0;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Visits the question type node and delegates to the appropriate method based on the question type.
     *
     * @param ctx the context of the question type node
     * @return the result of visiting the child nodes
     */
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

    /**
     * Visits the question body node and prints the question text.
     *
     * @param ctx the context of the question body node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionBody(InterviewModelGrammarParser.QuestionBodyContext ctx) {
        System.out.println("Question: " + ctx.getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the score node and prints the score text.
     *
     * @param ctx the context of the score node
     * @return the score text without the percentage symbol
     */
    @Override
    public String visitScore(InterviewModelGrammarParser.ScoreContext ctx) {
        System.out.println("Score: " + ctx.getText());
        return ctx.getText().replace("%", "").trim();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Visits the question date node and prints the question type.
     *
     * @param ctx the context of the question date node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionDate(InterviewModelGrammarParser.QuestionDateContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.DATE().getText());
        return visitChildren(ctx);
    }

    /**
     * Visits the question decimal number node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question decimal number node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionDecimalNumber(InterviewModelGrammarParser.QuestionDecimalNumberContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.DECIMAL_NUMBER().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));
        return visitChildren(ctx);
    }

    /**
     * Visits the question integer number node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question integer number node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionIntegerNumber(InterviewModelGrammarParser.QuestionIntegerNumberContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.INTEGER_NUMBER().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));
        return visitChildren(ctx);
    }

    /**
     * Visits the question multiple choice node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question multiple choice node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionMultipleChoice(InterviewModelGrammarParser.QuestionMultipleChoiceContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.MULTIPLE_CHOICE().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));
        return visitChildren(ctx);
    }

    /**
     * Visits the question numeric range node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question numeric range node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionNumericRange(InterviewModelGrammarParser.QuestionNumericRangeContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.NUMERIC_RANGE().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));
        return visitChildren(ctx);
    }

    /**
     * Visits the question single choice node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question single choice node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionSingleChoice(InterviewModelGrammarParser.QuestionSingleChoiceContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.SINGLE_CHOICE().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));
        return visitChildren(ctx);
    }

    /**
     * Visits the question short answer node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question short answer node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionShortAnswer(InterviewModelGrammarParser.QuestionShortAnswerContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.SHORT_ANSWER().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));
        return visitChildren(ctx);
    }

    /**
     * Visits the question time node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question time node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitQuestionTime(InterviewModelGrammarParser.QuestionTimeContext ctx) {
        System.out.println(ctx.getText());
        System.out.println("Question Type -> " + ctx.TIME().getText());
        currentScore = Integer.parseInt(visitScore(ctx.score()));
        return visitChildren(ctx);
    }

    /**
     * Visits the question true/false node, prints the question type, and sets the current score.
     *
     * @param ctx the context of the question true/false node
     * @return the result of visiting the child nodes
     */
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

    /**
     * Visits the answer date node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer date node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerDate(InterviewModelGrammarParser.AnswerDateContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer decimal node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer decimal node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerDecimal(InterviewModelGrammarParser.AnswerDecimalContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer integer node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer integer node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerInteger(InterviewModelGrammarParser.AnswerIntegerContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer multiple choice node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer multiple choice node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerMultipleChoice(InterviewModelGrammarParser.AnswerMultipleChoiceContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer numeric range node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer numeric range node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerNumericRange(InterviewModelGrammarParser.AnswerNumericRangeContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer single choice node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer single choice node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerSingleChoice(InterviewModelGrammarParser.AnswerSingleChoiceContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer short text node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer short text node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerShortText(InterviewModelGrammarParser.AnswerShortTextContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer time node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer time node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerTime(InterviewModelGrammarParser.AnswerTimeContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    /**
     * Visits the answer true/false node, checks the answer, and increments the question index.
     *
     * @param ctx the context of the answer true/false node
     * @return the result of visiting the child nodes
     */
    @Override
    public Object visitAnswerTrueFalse(InterviewModelGrammarParser.AnswerTrueFalseContext ctx) {
        checkAnswer(ctx.getText(), candidateResponses.get(currentQuestionIndex));
        currentQuestionIndex++;
        return visitChildren(ctx);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Retrieves the total score computed by this visitor.
     *
     * @return the total score as a percentage (0-100)
     */
    public int findOutTotalScore() {
        return totalScore;
    }

    /**
     * Checks the candidate's answer against the correct answer and updates the total score if correct.
     *
     * @param correctAnswer   the correct answer from the model
     * @param candidateAnswer the candidate's answer
     */
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
