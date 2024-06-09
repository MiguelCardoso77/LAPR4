package plugin.requirements;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plugin.requirements.autogen.RequirementsGrammarLexer;
import plugin.requirements.autogen.RequirementsGrammarParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * The RequirementsPlugin class is responsible for checking if a candidate's responses
 * meet the specified requirements from a given file. It uses ANTLR-generated lexer and
 * parser to process the requirements file.
 *
 * @author Tomás Gonçalves
 */
public class RequirementsPlugin {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequirementsPlugin.class);

    /**
     * Checks if the candidate's responses meet the requirements specified in the file.
     *
     * @param path The path to the requirements file.
     * @param cResponses A map of candidate responses where the key is the requirement type and the value is the response.
     * @return true if the candidate's responses meet the requirements, false otherwise.
     */
    public boolean checkRequirements(String path, Map<String, String> cResponses) {
        try {
            String input = new String(Files.readAllBytes(Paths.get(path)));

            System.out.println("Requirements Used:");
            System.out.println(input);
            System.out.println();

            RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(CharStreams.fromString(input));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            RequirementsGrammarParser parser = new RequirementsGrammarParser(tokens);

            ParseTree tree = parser.start();

            RequirementsVisitor visitor = new RequirementsVisitor(cResponses);
            visitor.visit(tree);

            System.out.println("-------------------------------------------");
            boolean requirementsMet = visitor.checkRequirements();
            System.out.println("Requirements met: " + requirementsMet);

            return requirementsMet;

        } catch (IOException e) {
            LOGGER.error("Error checking requirements: {}", e.getMessage());
        }

        return false;
    }

    /**
     * Retrieves the answers to the requirements specified in the file.
     *
     * @param path The path to the requirements file.
     * @return A list of responses required by the requirements file.
     */
    public List<String> retrieveAnswersRequirements(String path){
        try {
            String input = new String(Files.readAllBytes(Paths.get(path)));

            RequirementsGrammarLexer lexer = new RequirementsGrammarLexer(CharStreams.fromString(input));
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            RequirementsGrammarParser parser = new RequirementsGrammarParser(tokenStream);

            ParseTree tree = parser.start();

            ResponseRequirementsVisitor visitor = new ResponseRequirementsVisitor();
            visitor.visit(tree);

            return visitor.retrieveResponseRequirements();

        } catch (IOException e){
            System.out.println("Invalid Path!");
        }

        return null;
    }


}
