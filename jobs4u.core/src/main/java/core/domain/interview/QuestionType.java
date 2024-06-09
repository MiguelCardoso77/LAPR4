package core.domain.interview;
/**
 * Represents the type of a question in an interview.
 * This enum provides different types of questions that can be asked in an interview.
 *
 * @author Miguel Cardoso
 */
public enum QuestionType {
    DATE("Date: "),
    DECIMAL_NUMBER("Decimal Number: "),
    INTEGER_NUMBER("Integer Number: "),
    MULTIPLE_CHOICE("Multiple Choice: "),
    NUMERIC_RANGE("Numeric Range: "),
    SINGLE_CHOICE("Single Choice: "),
    SHORT_TEXT("Short Answer: "),
    TIME("Time: "),
    TRUE_FALSE("True/False: ");
    private final String usage;
    /**
     * Constructs a QuestionType with the specified usage.
     *
     * @param usage The usage of the question type.
     */
    QuestionType(String usage) {
        this.usage = usage;
    }
    /**
     * Returns a string representation of the question type.
     *
     * @return A string representation of the question type.
     */
    @Override
    public String toString() {
        return usage;
    }
}
