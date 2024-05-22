import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class InterviewModelGrammarLexer extends Lexer {
    static {
        RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            ANY_STRING = 1, ARROW = 2, DECIMAL = 3, INTEGER = 4, HASHTAG = 5, PARAGRAPH = 6, PERCENTAGE = 7,
            SPACE = 8, QUESTIONS = 9, DATE = 10, DATE_ANSWER = 11, DECIMAL_NUMBER = 12, INTEGER_NUMBER = 13,
            MULTIPLE_CHOICE = 14, NUMERIC_RANGE = 15, NUMERIC_RANGE_ANSWER = 16, SINGLE_CHOICE = 17,
            SHORT_ANSWER = 18, TIME = 19, TIME_ANSWER = 20, TRUE_FALSE = 21, TRUE_FALSE_ANWSER = 22;
    public static String[] channelNames = {
            "DEFAULT_TOKEN_CHANNEL", "HIDDEN"
    };

    public static String[] modeNames = {
            "DEFAULT_MODE"
    };

    private static String[] makeRuleNames() {
        return new String[]{
                "ANY_STRING", "ARROW", "DECIMAL", "INTEGER", "HASHTAG", "PARAGRAPH",
                "PERCENTAGE", "SPACE", "QUESTIONS", "DATE", "DATE_ANSWER", "DECIMAL_NUMBER",
                "INTEGER_NUMBER", "MULTIPLE_CHOICE", "NUMERIC_RANGE", "NUMERIC_RANGE_ANSWER",
                "SINGLE_CHOICE", "SHORT_ANSWER", "TIME", "TIME_ANSWER", "TRUE_FALSE",
                "TRUE_FALSE_ANWSER"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, null, "'-> '", null, null, "'# '", null, null, "' '", "'Questions:'",
                "'Date: '", null, "'Decimal Number: '", "'Integer Number: '", "'Multiple Choice: '",
                "'Numeric Range: '", null, "'Single Choice: '", "'Short Answer: '", "'Time: '",
                null, "'True/False: '"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, "ANY_STRING", "ARROW", "DECIMAL", "INTEGER", "HASHTAG", "PARAGRAPH",
                "PERCENTAGE", "SPACE", "QUESTIONS", "DATE", "DATE_ANSWER", "DECIMAL_NUMBER",
                "INTEGER_NUMBER", "MULTIPLE_CHOICE", "NUMERIC_RANGE", "NUMERIC_RANGE_ANSWER",
                "SINGLE_CHOICE", "SHORT_ANSWER", "TIME", "TIME_ANSWER", "TRUE_FALSE",
                "TRUE_FALSE_ANWSER"
        };
    }

    private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
    public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

    /**
     * @deprecated Use {@link #VOCABULARY} instead.
     */
    @Deprecated
    public static final String[] tokenNames;

    static {
        tokenNames = new String[_SYMBOLIC_NAMES.length];
        for (int i = 0; i < tokenNames.length; i++) {
            tokenNames[i] = VOCABULARY.getLiteralName(i);
            if (tokenNames[i] == null) {
                tokenNames[i] = VOCABULARY.getSymbolicName(i);
            }

            if (tokenNames[i] == null) {
                tokenNames[i] = "<INVALID>";
            }
        }
    }

    @Override
    @Deprecated
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override

    public Vocabulary getVocabulary() {
        return VOCABULARY;
    }


    public InterviewModelGrammarLexer(CharStream input) {
        super(input);
        _interp = new LexerATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @Override
    public String getGrammarFileName() {
        return "InterviewModelGrammar.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public String[] getChannelNames() {
        return channelNames;
    }

    @Override
    public String[] getModeNames() {
        return modeNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public static final String _serializedATN =
            "\u0004\u0000\u0016\u0134\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002" +
                    "\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002" +
                    "\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002" +
                    "\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002" +
                    "\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e" +
                    "\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011" +
                    "\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014" +
                    "\u0002\u0015\u0007\u0015\u0001\u0000\u0001\u0000\u0005\u00000\b\u0000" +
                    "\n\u0000\f\u00003\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001" +
                    "\u0001\u0001\u0001\u0001\u0001\u0002\u0004\u0002<\b\u0002\u000b\u0002" +
                    "\f\u0002=\u0001\u0002\u0001\u0002\u0004\u0002B\b\u0002\u000b\u0002\f\u0002" +
                    "C\u0001\u0002\u0004\u0002G\b\u0002\u000b\u0002\f\u0002H\u0001\u0002\u0001" +
                    "\u0002\u0004\u0002M\b\u0002\u000b\u0002\f\u0002N\u0003\u0002Q\b\u0002" +
                    "\u0001\u0003\u0004\u0003T\b\u0003\u000b\u0003\f\u0003U\u0001\u0004\u0001" +
                    "\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0004\u0006^\b" +
                    "\u0006\u000b\u0006\f\u0006_\u0001\u0006\u0001\u0006\u0001\u0007\u0001" +
                    "\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b" +
                    "\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001" +
                    "\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001" +
                    "\n\u0003\n\u0080\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0087" +
                    "\b\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001" +
                    "\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001" +
                    "\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001" +
                    "\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001" +
                    "\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001" +
                    "\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001" +
                    "\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001" +
                    "\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e" +
                    "\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e" +
                    "\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e" +
                    "\u0001\u000e\u0001\u000f\u0004\u000f\u00d4\b\u000f\u000b\u000f\f\u000f" +
                    "\u00d5\u0001\u000f\u0001\u000f\u0004\u000f\u00da\b\u000f\u000b\u000f\f" +
                    "\u000f\u00db\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
                    "\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
                    "\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011" +
                    "\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011" +
                    "\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011" +
                    "\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012" +
                    "\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013" +
                    "\u0001\u0013\u0001\u0013\u0001\u0013\u0003\u0013\u010a\b\u0013\u0001\u0013" +
                    "\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013" +
                    "\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013" +
                    "\u0003\u0013\u0119\b\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014" +
                    "\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014" +
                    "\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015" +
                    "\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015" +
                    "\u0001\u0015\u0001\u0015\u0003\u0015\u0133\b\u0015\u0000\u0000\u0016\u0001" +
                    "\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007" +
                    "\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d" +
                    "\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016\u0001\u0000" +
                    "\n\u0002\u0000<<>>\u0001\u000009\u0003\u0000..0099\u0002\u0000\n\n\r\r" +
                    "\u0001\u000019\u0001\u000001\u0001\u000002\u0001\u000004\u0002\u0000T" +
                    "Ttt\u0002\u0000FFff\u0149\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003" +
                    "\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007" +
                    "\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001" +
                    "\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000" +
                    "\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000" +
                    "\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000" +
                    "\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000" +
                    "\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000" +
                    "\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000" +
                    "\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000" +
                    ")\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0001-\u0001" +
                    "\u0000\u0000\u0000\u00036\u0001\u0000\u0000\u0000\u0005P\u0001\u0000\u0000" +
                    "\u0000\u0007S\u0001\u0000\u0000\u0000\tW\u0001\u0000\u0000\u0000\u000b" +
                    "Z\u0001\u0000\u0000\u0000\r]\u0001\u0000\u0000\u0000\u000fc\u0001\u0000" +
                    "\u0000\u0000\u0011e\u0001\u0000\u0000\u0000\u0013p\u0001\u0000\u0000\u0000" +
                    "\u0015\u007f\u0001\u0000\u0000\u0000\u0017\u008e\u0001\u0000\u0000\u0000" +
                    "\u0019\u009f\u0001\u0000\u0000\u0000\u001b\u00b0\u0001\u0000\u0000\u0000" +
                    "\u001d\u00c2\u0001\u0000\u0000\u0000\u001f\u00d3\u0001\u0000\u0000\u0000" +
                    "!\u00dd\u0001\u0000\u0000\u0000#\u00ed\u0001\u0000\u0000\u0000%\u00fc" +
                    "\u0001\u0000\u0000\u0000\'\u0109\u0001\u0000\u0000\u0000)\u011a\u0001" +
                    "\u0000\u0000\u0000+\u0132\u0001\u0000\u0000\u0000-1\u0005<\u0000\u0000" +
                    ".0\b\u0000\u0000\u0000/.\u0001\u0000\u0000\u000003\u0001\u0000\u0000\u0000" +
                    "1/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000024\u0001\u0000\u0000" +
                    "\u000031\u0001\u0000\u0000\u000045\u0005>\u0000\u00005\u0002\u0001\u0000" +
                    "\u0000\u000067\u0005-\u0000\u000078\u0005>\u0000\u000089\u0005 \u0000" +
                    "\u00009\u0004\u0001\u0000\u0000\u0000:<\u0007\u0001\u0000\u0000;:\u0001" +
                    "\u0000\u0000\u0000<=\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000" +
                    "=>\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?A\u0005.\u0000\u0000" +
                    "@B\u0007\u0001\u0000\u0000A@\u0001\u0000\u0000\u0000BC\u0001\u0000\u0000" +
                    "\u0000CA\u0001\u0000\u0000\u0000CD\u0001\u0000\u0000\u0000DQ\u0001\u0000" +
                    "\u0000\u0000EG\u0007\u0002\u0000\u0000FE\u0001\u0000\u0000\u0000GH\u0001" +
                    "\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000" +
                    "IJ\u0001\u0000\u0000\u0000JL\u0005,\u0000\u0000KM\u0007\u0001\u0000\u0000" +
                    "LK\u0001\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000" +
                    "\u0000NO\u0001\u0000\u0000\u0000OQ\u0001\u0000\u0000\u0000P;\u0001\u0000" +
                    "\u0000\u0000PF\u0001\u0000\u0000\u0000Q\u0006\u0001\u0000\u0000\u0000" +
                    "RT\u0007\u0001\u0000\u0000SR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000" +
                    "\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\b\u0001\u0000" +
                    "\u0000\u0000WX\u0005#\u0000\u0000XY\u0005 \u0000\u0000Y\n\u0001\u0000" +
                    "\u0000\u0000Z[\u0007\u0003\u0000\u0000[\f\u0001\u0000\u0000\u0000\\^\u0007" +
                    "\u0001\u0000\u0000]\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000" +
                    "_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000" +
                    "\u0000ab\u0005%\u0000\u0000b\u000e\u0001\u0000\u0000\u0000cd\u0005 \u0000" +
                    "\u0000d\u0010\u0001\u0000\u0000\u0000ef\u0005Q\u0000\u0000fg\u0005u\u0000" +
                    "\u0000gh\u0005e\u0000\u0000hi\u0005s\u0000\u0000ij\u0005t\u0000\u0000" +
                    "jk\u0005i\u0000\u0000kl\u0005o\u0000\u0000lm\u0005n\u0000\u0000mn\u0005" +
                    "s\u0000\u0000no\u0005:\u0000\u0000o\u0012\u0001\u0000\u0000\u0000pq\u0005" +
                    "D\u0000\u0000qr\u0005a\u0000\u0000rs\u0005t\u0000\u0000st\u0005e\u0000" +
                    "\u0000tu\u0005:\u0000\u0000uv\u0005 \u0000\u0000v\u0014\u0001\u0000\u0000" +
                    "\u0000wx\u00050\u0000\u0000x\u0080\u0007\u0004\u0000\u0000yz\u00051\u0000" +
                    "\u0000z\u0080\u0007\u0001\u0000\u0000{|\u00052\u0000\u0000|\u0080\u0007" +
                    "\u0001\u0000\u0000}~\u00053\u0000\u0000~\u0080\u0007\u0005\u0000\u0000" +
                    "\u007fw\u0001\u0000\u0000\u0000\u007fy\u0001\u0000\u0000\u0000\u007f{" +
                    "\u0001\u0000\u0000\u0000\u007f}\u0001\u0000\u0000\u0000\u0080\u0081\u0001" +
                    "\u0000\u0000\u0000\u0081\u0086\u0005/\u0000\u0000\u0082\u0083\u00050\u0000" +
                    "\u0000\u0083\u0087\u0007\u0004\u0000\u0000\u0084\u0085\u00051\u0000\u0000" +
                    "\u0085\u0087\u0007\u0006\u0000\u0000\u0086\u0082\u0001\u0000\u0000\u0000" +
                    "\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000" +
                    "\u0088\u0089\u0005/\u0000\u0000\u0089\u008a\u0007\u0004\u0000\u0000\u008a" +
                    "\u008b\u0007\u0001\u0000\u0000\u008b\u008c\u0007\u0001\u0000\u0000\u008c" +
                    "\u008d\u0007\u0001\u0000\u0000\u008d\u0016\u0001\u0000\u0000\u0000\u008e" +
                    "\u008f\u0005D\u0000\u0000\u008f\u0090\u0005e\u0000\u0000\u0090\u0091\u0005" +
                    "c\u0000\u0000\u0091\u0092\u0005i\u0000\u0000\u0092\u0093\u0005m\u0000" +
                    "\u0000\u0093\u0094\u0005a\u0000\u0000\u0094\u0095\u0005l\u0000\u0000\u0095" +
                    "\u0096\u0005 \u0000\u0000\u0096\u0097\u0005N\u0000\u0000\u0097\u0098\u0005" +
                    "u\u0000\u0000\u0098\u0099\u0005m\u0000\u0000\u0099\u009a\u0005b\u0000" +
                    "\u0000\u009a\u009b\u0005e\u0000\u0000\u009b\u009c\u0005r\u0000\u0000\u009c" +
                    "\u009d\u0005:\u0000\u0000\u009d\u009e\u0005 \u0000\u0000\u009e\u0018\u0001" +
                    "\u0000\u0000\u0000\u009f\u00a0\u0005I\u0000\u0000\u00a0\u00a1\u0005n\u0000" +
                    "\u0000\u00a1\u00a2\u0005t\u0000\u0000\u00a2\u00a3\u0005e\u0000\u0000\u00a3" +
                    "\u00a4\u0005g\u0000\u0000\u00a4\u00a5\u0005e\u0000\u0000\u00a5\u00a6\u0005" +
                    "r\u0000\u0000\u00a6\u00a7\u0005 \u0000\u0000\u00a7\u00a8\u0005N\u0000" +
                    "\u0000\u00a8\u00a9\u0005u\u0000\u0000\u00a9\u00aa\u0005m\u0000\u0000\u00aa" +
                    "\u00ab\u0005b\u0000\u0000\u00ab\u00ac\u0005e\u0000\u0000\u00ac\u00ad\u0005" +
                    "r\u0000\u0000\u00ad\u00ae\u0005:\u0000\u0000\u00ae\u00af\u0005 \u0000" +
                    "\u0000\u00af\u001a\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005M\u0000\u0000" +
                    "\u00b1\u00b2\u0005u\u0000\u0000\u00b2\u00b3\u0005l\u0000\u0000\u00b3\u00b4" +
                    "\u0005t\u0000\u0000\u00b4\u00b5\u0005i\u0000\u0000\u00b5\u00b6\u0005p" +
                    "\u0000\u0000\u00b6\u00b7\u0005l\u0000\u0000\u00b7\u00b8\u0005e\u0000\u0000" +
                    "\u00b8\u00b9\u0005 \u0000\u0000\u00b9\u00ba\u0005C\u0000\u0000\u00ba\u00bb" +
                    "\u0005h\u0000\u0000\u00bb\u00bc\u0005o\u0000\u0000\u00bc\u00bd\u0005i" +
                    "\u0000\u0000\u00bd\u00be\u0005c\u0000\u0000\u00be\u00bf\u0005e\u0000\u0000" +
                    "\u00bf\u00c0\u0005:\u0000\u0000\u00c0\u00c1\u0005 \u0000\u0000\u00c1\u001c" +
                    "\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005N\u0000\u0000\u00c3\u00c4\u0005" +
                    "u\u0000\u0000\u00c4\u00c5\u0005m\u0000\u0000\u00c5\u00c6\u0005e\u0000" +
                    "\u0000\u00c6\u00c7\u0005r\u0000\u0000\u00c7\u00c8\u0005i\u0000\u0000\u00c8" +
                    "\u00c9\u0005c\u0000\u0000\u00c9\u00ca\u0005 \u0000\u0000\u00ca\u00cb\u0005" +
                    "R\u0000\u0000\u00cb\u00cc\u0005a\u0000\u0000\u00cc\u00cd\u0005n\u0000" +
                    "\u0000\u00cd\u00ce\u0005g\u0000\u0000\u00ce\u00cf\u0005e\u0000\u0000\u00cf" +
                    "\u00d0\u0005:\u0000\u0000\u00d0\u00d1\u0005 \u0000\u0000\u00d1\u001e\u0001" +
                    "\u0000\u0000\u0000\u00d2\u00d4\u0007\u0001\u0000\u0000\u00d3\u00d2\u0001" +
                    "\u0000\u0000\u0000\u00d4\u00d5\u0001\u0000\u0000\u0000\u00d5\u00d3\u0001" +
                    "\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000\u0000\u00d6\u00d7\u0001" +
                    "\u0000\u0000\u0000\u00d7\u00d9\u0005-\u0000\u0000\u00d8\u00da\u0007\u0001" +
                    "\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000" +
                    "\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000\u00db\u00dc\u0001\u0000" +
                    "\u0000\u0000\u00dc \u0001\u0000\u0000\u0000\u00dd\u00de\u0005S\u0000\u0000" +
                    "\u00de\u00df\u0005i\u0000\u0000\u00df\u00e0\u0005n\u0000\u0000\u00e0\u00e1" +
                    "\u0005g\u0000\u0000\u00e1\u00e2\u0005l\u0000\u0000\u00e2\u00e3\u0005e" +
                    "\u0000\u0000\u00e3\u00e4\u0005 \u0000\u0000\u00e4\u00e5\u0005C\u0000\u0000" +
                    "\u00e5\u00e6\u0005h\u0000\u0000\u00e6\u00e7\u0005o\u0000\u0000\u00e7\u00e8" +
                    "\u0005i\u0000\u0000\u00e8\u00e9\u0005c\u0000\u0000\u00e9\u00ea\u0005e" +
                    "\u0000\u0000\u00ea\u00eb\u0005:\u0000\u0000\u00eb\u00ec\u0005 \u0000\u0000" +
                    "\u00ec\"\u0001\u0000\u0000\u0000\u00ed\u00ee\u0005S\u0000\u0000\u00ee" +
                    "\u00ef\u0005h\u0000\u0000\u00ef\u00f0\u0005o\u0000\u0000\u00f0\u00f1\u0005" +
                    "r\u0000\u0000\u00f1\u00f2\u0005t\u0000\u0000\u00f2\u00f3\u0005 \u0000" +
                    "\u0000\u00f3\u00f4\u0005A\u0000\u0000\u00f4\u00f5\u0005n\u0000\u0000\u00f5" +
                    "\u00f6\u0005s\u0000\u0000\u00f6\u00f7\u0005w\u0000\u0000\u00f7\u00f8\u0005" +
                    "e\u0000\u0000\u00f8\u00f9\u0005r\u0000\u0000\u00f9\u00fa\u0005:\u0000" +
                    "\u0000\u00fa\u00fb\u0005 \u0000\u0000\u00fb$\u0001\u0000\u0000\u0000\u00fc" +
                    "\u00fd\u0005T\u0000\u0000\u00fd\u00fe\u0005i\u0000\u0000\u00fe\u00ff\u0005" +
                    "m\u0000\u0000\u00ff\u0100\u0005e\u0000\u0000\u0100\u0101\u0005:\u0000" +
                    "\u0000\u0101\u0102\u0005 \u0000\u0000\u0102&\u0001\u0000\u0000\u0000\u0103" +
                    "\u0104\u00050\u0000\u0000\u0104\u010a\u0007\u0001\u0000\u0000\u0105\u0106" +
                    "\u00051\u0000\u0000\u0106\u010a\u0007\u0001\u0000\u0000\u0107\u0108\u0005" +
                    "2\u0000\u0000\u0108\u010a\u0007\u0007\u0000\u0000\u0109\u0103\u0001\u0000" +
                    "\u0000\u0000\u0109\u0105\u0001\u0000\u0000\u0000\u0109\u0107\u0001\u0000" +
                    "\u0000\u0000\u010a\u010b\u0001\u0000\u0000\u0000\u010b\u0118\u0005:\u0000" +
                    "\u0000\u010c\u010d\u00050\u0000\u0000\u010d\u0119\u0007\u0001\u0000\u0000" +
                    "\u010e\u010f\u00051\u0000\u0000\u010f\u0119\u0007\u0001\u0000\u0000\u0110" +
                    "\u0111\u00052\u0000\u0000\u0111\u0119\u0007\u0001\u0000\u0000\u0112\u0113" +
                    "\u00053\u0000\u0000\u0113\u0119\u0007\u0001\u0000\u0000\u0114\u0115\u0005" +
                    "4\u0000\u0000\u0115\u0119\u0007\u0001\u0000\u0000\u0116\u0117\u00055\u0000" +
                    "\u0000\u0117\u0119\u0007\u0001\u0000\u0000\u0118\u010c\u0001\u0000\u0000" +
                    "\u0000\u0118\u010e\u0001\u0000\u0000\u0000\u0118\u0110\u0001\u0000\u0000" +
                    "\u0000\u0118\u0112\u0001\u0000\u0000\u0000\u0118\u0114\u0001\u0000\u0000" +
                    "\u0000\u0118\u0116\u0001\u0000\u0000\u0000\u0119(\u0001\u0000\u0000\u0000" +
                    "\u011a\u011b\u0005T\u0000\u0000\u011b\u011c\u0005r\u0000\u0000\u011c\u011d" +
                    "\u0005u\u0000\u0000\u011d\u011e\u0005e\u0000\u0000\u011e\u011f\u0005/" +
                    "\u0000\u0000\u011f\u0120\u0005F\u0000\u0000\u0120\u0121\u0005a\u0000\u0000" +
                    "\u0121\u0122\u0005l\u0000\u0000\u0122\u0123\u0005s\u0000\u0000\u0123\u0124" +
                    "\u0005e\u0000\u0000\u0124\u0125\u0005:\u0000\u0000\u0125\u0126\u0005 " +
                    "\u0000\u0000\u0126*\u0001\u0000\u0000\u0000\u0127\u0128\u0007\b\u0000" +
                    "\u0000\u0128\u0129\u0005r\u0000\u0000\u0129\u012a\u0005u\u0000\u0000\u012a" +
                    "\u012b\u0005e\u0000\u0000\u012b\u0133\u0005 \u0000\u0000\u012c\u012d\u0007" +
                    "\t\u0000\u0000\u012d\u012e\u0005a\u0000\u0000\u012e\u012f\u0005l\u0000" +
                    "\u0000\u012f\u0130\u0005s\u0000\u0000\u0130\u0131\u0005e\u0000\u0000\u0131" +
                    "\u0133\u0005 \u0000\u0000\u0132\u0127\u0001\u0000\u0000\u0000\u0132\u012c" +
                    "\u0001\u0000\u0000\u0000\u0133,\u0001\u0000\u0000\u0000\u0010\u00001=" +
                    "CHNPU_\u007f\u0086\u00d5\u00db\u0109\u0118\u0132\u0000";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}