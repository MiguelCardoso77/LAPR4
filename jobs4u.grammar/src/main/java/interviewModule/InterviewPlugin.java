package interviewModule;

import interviewModule.autogen.InterviewModelGrammarLexer;
import interviewModule.autogen.InterviewModelGrammarParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InterviewPlugin {

    public int grammarChecker(String path, List<String> cResponses) {
        try {
            // Read the input file
            String input = new String(Files.readAllBytes(Paths.get(path)));

            System.out.println("Interview Model Used:");
            System.out.println(input);
            System.out.println();

            // Create a lexer and parser for the input
            InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

            // Parse the input file
            ParseTree tree = parser.start(); // Assuming 'start' is the entry point of your grammar

            // Create a custom listener
            InterviewVisitor visitor = new InterviewVisitor(cResponses);
            visitor.visit(tree);

            System.out.println("-------------------------------------------");
            int totalScore = visitor.findOutTotalScore();
            System.out.println("Total score: " + totalScore + "% out of 100%");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
