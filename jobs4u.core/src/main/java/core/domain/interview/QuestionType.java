package core.domain.interview;

public enum QuestionType {
    DATE("Date: "),
    DECIMAL_NUMBER("Decimal Number: "),
    INTEGER_NUMBER("Integer Number: "),
    MULTIPLE_CHOICE("Multiple Choice: "),
    NUMERIC_RANGE("Numeric Range: "),
    SINGLE_CHOICE("Single Choice: "),
    SHORT_TEXT("Short Text: "),
    TIME("Time: "),
    TRUE_FALSE("True/False: ");

    private final String usage;

    QuestionType(String usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return usage;
    }
}
