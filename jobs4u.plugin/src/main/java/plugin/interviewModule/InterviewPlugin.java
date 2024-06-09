package plugin.interviewModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plugin.interviewModule.autogen.InterviewModelGrammarLexer;
import plugin.interviewModule.autogen.InterviewModelGrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The InterviewPlugin class provides methods for checking the grammar of an interview model and retrieving answers from it.
 * It uses ANTLR-generated lexer and parser to parse the input files.
 *
 * @author Miguel Cardoso
 */
public class InterviewPlugin {
    private static final Logger LOGGER = LoggerFactory.getLogger(InterviewPlugin.class);

    /**
     * Checks the grammar of the interview model specified by the path and computes a total score.
     *
     * @param path       the path to the interview model file
     * @param cResponses the list of candidate responses
     * @return the total score as a percentage (0-100)
     */
    public int grammarChecker(String path, List<String> cResponses) {
        try {
            String input = new String(Files.readAllBytes(Paths.get(path)));

            System.out.println("Interview Model Used:");
            System.out.println(input);
            System.out.println();

            InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

            ParseTree tree = parser.start();

            InterviewVisitor visitor = new InterviewVisitor(cResponses);
            visitor.visit(tree);

            System.out.println("-------------------------------------------");
            int totalScore = visitor.findOutTotalScore();
            System.out.println("Total score: " + totalScore + "% out of 100%");
            return totalScore;

        } catch (IOException e) {
            LOGGER.error("Error checking grammar: {}", e.getMessage());
        }

        return 0;
    }

    /**
     * Retrieves answers from the interview model specified by the path.
     *
     * @param path the path to the interview model file
     * @return a list of retrieved answers, or null if an error occurs
     */
    public List<String> retrieveAnswers(String path) {
        try {
            String input = new String(Files.readAllBytes(Paths.get(path)));

            InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

            ParseTree tree = parser.start();

            ResponsesVisitor visitor = new ResponsesVisitor();
            visitor.visit(tree);

            return visitor.retrieveResponses();

        } catch (IOException e) {
            LOGGER.error("Error retrieving answers: {}", e.getMessage());
        }

        return null;
    }
}
