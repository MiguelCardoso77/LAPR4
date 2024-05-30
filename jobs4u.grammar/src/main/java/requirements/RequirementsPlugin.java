package requirements;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import requirements.autogen.RequirementsGrammarLexer;
import requirements.autogen.RequirementsGrammarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class RequirementsPlugin {

    public boolean checkRequirements(String path, Map<String, String> cResponses) {
        try {
            // Read the input file
            String input = new String(Files.readAllBytes(Paths.get(path)));

            System.out.println("Requirements Used:");
            System.out.println(input);
            System.out.println();

            // Create a lexer and parser for the input
            RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);

            // Parse the input file and start
            ParseTree tree = parser.start(); // Assuming 'start' is the entry point of your grammar

            // Create a custom listener
            RequirementsVisitor visitor = new RequirementsVisitor(cResponses);
            visitor.visit(tree);

            System.out.println("-------------------------------------------");
            boolean requirementsMet = visitor.checkRequirements();
            System.out.println("Requirements met: " + requirementsMet);

            return requirementsMet;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
