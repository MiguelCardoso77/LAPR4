package plugin.interviewModule.autogen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class InterviewModelGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ANY_STRING=1, ARROW=2, DECIMAL=3, INTEGER=4, HASHTAG=5, PARAGRAPH=6, PERCENTAGE=7, 
		SPACE=8, QUESTIONS=9, DATE=10, DATE_ANSWER=11, DECIMAL_NUMBER=12, INTEGER_NUMBER=13, 
		MULTIPLE_CHOICE=14, NUMERIC_RANGE=15, NUMERIC_RANGE_ANSWER=16, SINGLE_CHOICE=17, 
		SHORT_ANSWER=18, TIME=19, TIME_ANSWER=20, TRUE_FALSE=21, TRUE_FALSE_ANWSER=22;
	public static final int
		RULE_start = 0, RULE_questions = 1, RULE_questionItem = 2, RULE_questionBody = 3, 
		RULE_questionType = 4, RULE_questionDate = 5, RULE_answerDate = 6, RULE_questionDecimalNumber = 7, 
		RULE_answerDecimal = 8, RULE_questionIntegerNumber = 9, RULE_answerInteger = 10, 
		RULE_questionMultipleChoice = 11, RULE_answerMultipleChoice = 12, RULE_questionNumericRange = 13, 
		RULE_answerNumericRange = 14, RULE_questionSingleChoice = 15, RULE_answerSingleChoice = 16, 
		RULE_questionShortAnswer = 17, RULE_answerShortText = 18, RULE_questionTime = 19, 
		RULE_answerTime = 20, RULE_questionTrueFalse = 21, RULE_answerTrueFalse = 22, 
		RULE_score = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "questions", "questionItem", "questionBody", "questionType", 
			"questionDate", "answerDate", "questionDecimalNumber", "answerDecimal", 
			"questionIntegerNumber", "answerInteger", "questionMultipleChoice", "answerMultipleChoice", 
			"questionNumericRange", "answerNumericRange", "questionSingleChoice", 
			"answerSingleChoice", "questionShortAnswer", "answerShortText", "questionTime", 
			"answerTime", "questionTrueFalse", "answerTrueFalse", "score"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, "'-> '", null, null, "'# '", null, null, "' '", "'Questions:'", 
			"'Date: '", null, "'Decimal Number: '", "'Integer Number: '", "'Multiple Choice: '", 
			"'Numeric Range: '", null, "'Single Choice: '", "'Short Answer: '", "'Time: '", 
			null, "'True/False: '"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
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

	@Override
	public String getGrammarFileName() { return "InterviewModelGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InterviewModelGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public QuestionsContext questions() {
			return getRuleContext(QuestionsContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			questions();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionsContext extends ParserRuleContext {
		public TerminalNode HASHTAG() { return getToken(InterviewModelGrammarParser.HASHTAG, 0); }
		public TerminalNode QUESTIONS() { return getToken(InterviewModelGrammarParser.QUESTIONS, 0); }
		public TerminalNode PARAGRAPH() { return getToken(InterviewModelGrammarParser.PARAGRAPH, 0); }
		public List<QuestionItemContext> questionItem() {
			return getRuleContexts(QuestionItemContext.class);
		}
		public QuestionItemContext questionItem(int i) {
			return getRuleContext(QuestionItemContext.class,i);
		}
		public QuestionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionsContext questions() throws RecognitionException {
		QuestionsContext _localctx = new QuestionsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_questions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(HASHTAG);
			setState(51);
			match(QUESTIONS);
			setState(52);
			match(PARAGRAPH);
			setState(54); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(53);
				questionItem();
				}
				}
				setState(56); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ARROW );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionItemContext extends ParserRuleContext {
		public TerminalNode ARROW() { return getToken(InterviewModelGrammarParser.ARROW, 0); }
		public QuestionTypeContext questionType() {
			return getRuleContext(QuestionTypeContext.class,0);
		}
		public QuestionItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionItemContext questionItem() throws RecognitionException {
		QuestionItemContext _localctx = new QuestionItemContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_questionItem);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(ARROW);
			setState(59);
			questionType();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionBodyContext extends ParserRuleContext {
		public TerminalNode ANY_STRING() { return getToken(InterviewModelGrammarParser.ANY_STRING, 0); }
		public QuestionBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionBodyContext questionBody() throws RecognitionException {
		QuestionBodyContext _localctx = new QuestionBodyContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_questionBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(ANY_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionTypeContext extends ParserRuleContext {
		public QuestionDateContext questionDate() {
			return getRuleContext(QuestionDateContext.class,0);
		}
		public QuestionDecimalNumberContext questionDecimalNumber() {
			return getRuleContext(QuestionDecimalNumberContext.class,0);
		}
		public QuestionIntegerNumberContext questionIntegerNumber() {
			return getRuleContext(QuestionIntegerNumberContext.class,0);
		}
		public QuestionMultipleChoiceContext questionMultipleChoice() {
			return getRuleContext(QuestionMultipleChoiceContext.class,0);
		}
		public QuestionNumericRangeContext questionNumericRange() {
			return getRuleContext(QuestionNumericRangeContext.class,0);
		}
		public QuestionSingleChoiceContext questionSingleChoice() {
			return getRuleContext(QuestionSingleChoiceContext.class,0);
		}
		public QuestionShortAnswerContext questionShortAnswer() {
			return getRuleContext(QuestionShortAnswerContext.class,0);
		}
		public QuestionTimeContext questionTime() {
			return getRuleContext(QuestionTimeContext.class,0);
		}
		public QuestionTrueFalseContext questionTrueFalse() {
			return getRuleContext(QuestionTrueFalseContext.class,0);
		}
		public QuestionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTypeContext questionType() throws RecognitionException {
		QuestionTypeContext _localctx = new QuestionTypeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questionType);
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DATE:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				questionDate();
				}
				break;
			case DECIMAL_NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(64);
				questionDecimalNumber();
				}
				break;
			case INTEGER_NUMBER:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				questionIntegerNumber();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(66);
				questionMultipleChoice();
				}
				break;
			case NUMERIC_RANGE:
				enterOuterAlt(_localctx, 5);
				{
				setState(67);
				questionNumericRange();
				}
				break;
			case SINGLE_CHOICE:
				enterOuterAlt(_localctx, 6);
				{
				setState(68);
				questionSingleChoice();
				}
				break;
			case SHORT_ANSWER:
				enterOuterAlt(_localctx, 7);
				{
				setState(69);
				questionShortAnswer();
				}
				break;
			case TIME:
				enterOuterAlt(_localctx, 8);
				{
				setState(70);
				questionTime();
				}
				break;
			case TRUE_FALSE:
				enterOuterAlt(_localctx, 9);
				{
				setState(71);
				questionTrueFalse();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionDateContext extends ParserRuleContext {
		public TerminalNode DATE() { return getToken(InterviewModelGrammarParser.DATE, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerDateContext answerDate() {
			return getRuleContext(AnswerDateContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionDateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionDate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionDateContext questionDate() throws RecognitionException {
		QuestionDateContext _localctx = new QuestionDateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_questionDate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(DATE);
			setState(75);
			questionBody();
			setState(76);
			match(SPACE);
			setState(77);
			answerDate();
			setState(78);
			match(SPACE);
			setState(79);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerDateContext extends ParserRuleContext {
		public TerminalNode DATE_ANSWER() { return getToken(InterviewModelGrammarParser.DATE_ANSWER, 0); }
		public AnswerDateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerDate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerDate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerDate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerDate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerDateContext answerDate() throws RecognitionException {
		AnswerDateContext _localctx = new AnswerDateContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_answerDate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(DATE_ANSWER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionDecimalNumberContext extends ParserRuleContext {
		public TerminalNode DECIMAL_NUMBER() { return getToken(InterviewModelGrammarParser.DECIMAL_NUMBER, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerDecimalContext answerDecimal() {
			return getRuleContext(AnswerDecimalContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionDecimalNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionDecimalNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionDecimalNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionDecimalNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionDecimalNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionDecimalNumberContext questionDecimalNumber() throws RecognitionException {
		QuestionDecimalNumberContext _localctx = new QuestionDecimalNumberContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_questionDecimalNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(DECIMAL_NUMBER);
			setState(84);
			questionBody();
			setState(85);
			match(SPACE);
			setState(86);
			answerDecimal();
			setState(87);
			match(SPACE);
			setState(88);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerDecimalContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(InterviewModelGrammarParser.DECIMAL, 0); }
		public AnswerDecimalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerDecimal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerDecimal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerDecimal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerDecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerDecimalContext answerDecimal() throws RecognitionException {
		AnswerDecimalContext _localctx = new AnswerDecimalContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_answerDecimal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			match(DECIMAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionIntegerNumberContext extends ParserRuleContext {
		public TerminalNode INTEGER_NUMBER() { return getToken(InterviewModelGrammarParser.INTEGER_NUMBER, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerIntegerContext answerInteger() {
			return getRuleContext(AnswerIntegerContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionIntegerNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionIntegerNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionIntegerNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionIntegerNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionIntegerNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionIntegerNumberContext questionIntegerNumber() throws RecognitionException {
		QuestionIntegerNumberContext _localctx = new QuestionIntegerNumberContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_questionIntegerNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(INTEGER_NUMBER);
			setState(93);
			questionBody();
			setState(94);
			match(SPACE);
			setState(95);
			answerInteger();
			setState(96);
			match(SPACE);
			setState(97);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerIntegerContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(InterviewModelGrammarParser.INTEGER, 0); }
		public AnswerIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerIntegerContext answerInteger() throws RecognitionException {
		AnswerIntegerContext _localctx = new AnswerIntegerContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_answerInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionMultipleChoiceContext extends ParserRuleContext {
		public TerminalNode MULTIPLE_CHOICE() { return getToken(InterviewModelGrammarParser.MULTIPLE_CHOICE, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerMultipleChoiceContext answerMultipleChoice() {
			return getRuleContext(AnswerMultipleChoiceContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionMultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionMultipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionMultipleChoiceContext questionMultipleChoice() throws RecognitionException {
		QuestionMultipleChoiceContext _localctx = new QuestionMultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_questionMultipleChoice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(MULTIPLE_CHOICE);
			setState(102);
			questionBody();
			setState(103);
			match(SPACE);
			setState(104);
			answerMultipleChoice();
			setState(105);
			match(SPACE);
			setState(106);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerMultipleChoiceContext extends ParserRuleContext {
		public TerminalNode ANY_STRING() { return getToken(InterviewModelGrammarParser.ANY_STRING, 0); }
		public TerminalNode SPACE() { return getToken(InterviewModelGrammarParser.SPACE, 0); }
		public AnswerMultipleChoiceContext answerMultipleChoice() {
			return getRuleContext(AnswerMultipleChoiceContext.class,0);
		}
		public AnswerMultipleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerMultipleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerMultipleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerMultipleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerMultipleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerMultipleChoiceContext answerMultipleChoice() throws RecognitionException {
		AnswerMultipleChoiceContext _localctx = new AnswerMultipleChoiceContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_answerMultipleChoice);
		try {
			setState(112);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				match(ANY_STRING);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				match(ANY_STRING);
				setState(110);
				match(SPACE);
				setState(111);
				answerMultipleChoice();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionNumericRangeContext extends ParserRuleContext {
		public TerminalNode NUMERIC_RANGE() { return getToken(InterviewModelGrammarParser.NUMERIC_RANGE, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerNumericRangeContext answerNumericRange() {
			return getRuleContext(AnswerNumericRangeContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionNumericRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionNumericRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionNumericRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionNumericRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionNumericRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionNumericRangeContext questionNumericRange() throws RecognitionException {
		QuestionNumericRangeContext _localctx = new QuestionNumericRangeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_questionNumericRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(NUMERIC_RANGE);
			setState(115);
			questionBody();
			setState(116);
			match(SPACE);
			setState(117);
			answerNumericRange();
			setState(118);
			match(SPACE);
			setState(119);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerNumericRangeContext extends ParserRuleContext {
		public TerminalNode NUMERIC_RANGE_ANSWER() { return getToken(InterviewModelGrammarParser.NUMERIC_RANGE_ANSWER, 0); }
		public AnswerNumericRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerNumericRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerNumericRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerNumericRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerNumericRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerNumericRangeContext answerNumericRange() throws RecognitionException {
		AnswerNumericRangeContext _localctx = new AnswerNumericRangeContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_answerNumericRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(NUMERIC_RANGE_ANSWER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionSingleChoiceContext extends ParserRuleContext {
		public TerminalNode SINGLE_CHOICE() { return getToken(InterviewModelGrammarParser.SINGLE_CHOICE, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerSingleChoiceContext answerSingleChoice() {
			return getRuleContext(AnswerSingleChoiceContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionSingleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionSingleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionSingleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionSingleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionSingleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionSingleChoiceContext questionSingleChoice() throws RecognitionException {
		QuestionSingleChoiceContext _localctx = new QuestionSingleChoiceContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_questionSingleChoice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(SINGLE_CHOICE);
			setState(124);
			questionBody();
			setState(125);
			match(SPACE);
			setState(126);
			answerSingleChoice();
			setState(127);
			match(SPACE);
			setState(128);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerSingleChoiceContext extends ParserRuleContext {
		public TerminalNode ANY_STRING() { return getToken(InterviewModelGrammarParser.ANY_STRING, 0); }
		public AnswerSingleChoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerSingleChoice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerSingleChoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerSingleChoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerSingleChoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerSingleChoiceContext answerSingleChoice() throws RecognitionException {
		AnswerSingleChoiceContext _localctx = new AnswerSingleChoiceContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_answerSingleChoice);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(ANY_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionShortAnswerContext extends ParserRuleContext {
		public TerminalNode SHORT_ANSWER() { return getToken(InterviewModelGrammarParser.SHORT_ANSWER, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerShortTextContext answerShortText() {
			return getRuleContext(AnswerShortTextContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionShortAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionShortAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionShortAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionShortAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionShortAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionShortAnswerContext questionShortAnswer() throws RecognitionException {
		QuestionShortAnswerContext _localctx = new QuestionShortAnswerContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_questionShortAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(SHORT_ANSWER);
			setState(133);
			questionBody();
			setState(134);
			match(SPACE);
			setState(135);
			answerShortText();
			setState(136);
			match(SPACE);
			setState(137);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerShortTextContext extends ParserRuleContext {
		public TerminalNode ANY_STRING() { return getToken(InterviewModelGrammarParser.ANY_STRING, 0); }
		public AnswerShortTextContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerShortText; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerShortText(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerShortText(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerShortText(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerShortTextContext answerShortText() throws RecognitionException {
		AnswerShortTextContext _localctx = new AnswerShortTextContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_answerShortText);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(ANY_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionTimeContext extends ParserRuleContext {
		public TerminalNode TIME() { return getToken(InterviewModelGrammarParser.TIME, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public AnswerTimeContext answerTime() {
			return getRuleContext(AnswerTimeContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionTimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionTime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionTime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTimeContext questionTime() throws RecognitionException {
		QuestionTimeContext _localctx = new QuestionTimeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_questionTime);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(TIME);
			setState(142);
			questionBody();
			setState(143);
			match(SPACE);
			setState(144);
			answerTime();
			setState(145);
			match(SPACE);
			setState(146);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerTimeContext extends ParserRuleContext {
		public TerminalNode TIME_ANSWER() { return getToken(InterviewModelGrammarParser.TIME_ANSWER, 0); }
		public AnswerTimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerTime; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerTime(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerTime(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerTime(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerTimeContext answerTime() throws RecognitionException {
		AnswerTimeContext _localctx = new AnswerTimeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_answerTime);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(TIME_ANSWER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionTrueFalseContext extends ParserRuleContext {
		public TerminalNode TRUE_FALSE() { return getToken(InterviewModelGrammarParser.TRUE_FALSE, 0); }
		public QuestionBodyContext questionBody() {
			return getRuleContext(QuestionBodyContext.class,0);
		}
		public TerminalNode SPACE() { return getToken(InterviewModelGrammarParser.SPACE, 0); }
		public AnswerTrueFalseContext answerTrueFalse() {
			return getRuleContext(AnswerTrueFalseContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public QuestionTrueFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionTrueFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestionTrueFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestionTrueFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestionTrueFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionTrueFalseContext questionTrueFalse() throws RecognitionException {
		QuestionTrueFalseContext _localctx = new QuestionTrueFalseContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_questionTrueFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(TRUE_FALSE);
			setState(151);
			questionBody();
			setState(152);
			match(SPACE);
			setState(153);
			answerTrueFalse();
			setState(154);
			score();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerTrueFalseContext extends ParserRuleContext {
		public TerminalNode TRUE_FALSE_ANWSER() { return getToken(InterviewModelGrammarParser.TRUE_FALSE_ANWSER, 0); }
		public AnswerTrueFalseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answerTrueFalse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswerTrueFalse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswerTrueFalse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswerTrueFalse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerTrueFalseContext answerTrueFalse() throws RecognitionException {
		AnswerTrueFalseContext _localctx = new AnswerTrueFalseContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_answerTrueFalse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(TRUE_FALSE_ANWSER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScoreContext extends ParserRuleContext {
		public TerminalNode PERCENTAGE() { return getToken(InterviewModelGrammarParser.PERCENTAGE, 0); }
		public TerminalNode PARAGRAPH() { return getToken(InterviewModelGrammarParser.PARAGRAPH, 0); }
		public ScoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_score; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterScore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitScore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitScore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScoreContext score() throws RecognitionException {
		ScoreContext _localctx = new ScoreContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_score);
		try {
			setState(161);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(158);
				match(PERCENTAGE);
				setState(159);
				match(PARAGRAPH);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(160);
				match(PERCENTAGE);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0016\u00a4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007"+
		"\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u00017\b"+
		"\u0001\u000b\u0001\f\u00018\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003\u0004I\b"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0003\fq\b\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u00a2\b\u0017\u0001\u0017\u0000\u0000\u0018"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.\u0000\u0000\u0096\u00000\u0001\u0000\u0000\u0000"+
		"\u00022\u0001\u0000\u0000\u0000\u0004:\u0001\u0000\u0000\u0000\u0006="+
		"\u0001\u0000\u0000\u0000\bH\u0001\u0000\u0000\u0000\nJ\u0001\u0000\u0000"+
		"\u0000\fQ\u0001\u0000\u0000\u0000\u000eS\u0001\u0000\u0000\u0000\u0010"+
		"Z\u0001\u0000\u0000\u0000\u0012\\\u0001\u0000\u0000\u0000\u0014c\u0001"+
		"\u0000\u0000\u0000\u0016e\u0001\u0000\u0000\u0000\u0018p\u0001\u0000\u0000"+
		"\u0000\u001ar\u0001\u0000\u0000\u0000\u001cy\u0001\u0000\u0000\u0000\u001e"+
		"{\u0001\u0000\u0000\u0000 \u0082\u0001\u0000\u0000\u0000\"\u0084\u0001"+
		"\u0000\u0000\u0000$\u008b\u0001\u0000\u0000\u0000&\u008d\u0001\u0000\u0000"+
		"\u0000(\u0094\u0001\u0000\u0000\u0000*\u0096\u0001\u0000\u0000\u0000,"+
		"\u009c\u0001\u0000\u0000\u0000.\u00a1\u0001\u0000\u0000\u000001\u0003"+
		"\u0002\u0001\u00001\u0001\u0001\u0000\u0000\u000023\u0005\u0005\u0000"+
		"\u000034\u0005\t\u0000\u000046\u0005\u0006\u0000\u000057\u0003\u0004\u0002"+
		"\u000065\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u000086\u0001\u0000"+
		"\u0000\u000089\u0001\u0000\u0000\u00009\u0003\u0001\u0000\u0000\u0000"+
		":;\u0005\u0002\u0000\u0000;<\u0003\b\u0004\u0000<\u0005\u0001\u0000\u0000"+
		"\u0000=>\u0005\u0001\u0000\u0000>\u0007\u0001\u0000\u0000\u0000?I\u0003"+
		"\n\u0005\u0000@I\u0003\u000e\u0007\u0000AI\u0003\u0012\t\u0000BI\u0003"+
		"\u0016\u000b\u0000CI\u0003\u001a\r\u0000DI\u0003\u001e\u000f\u0000EI\u0003"+
		"\"\u0011\u0000FI\u0003&\u0013\u0000GI\u0003*\u0015\u0000H?\u0001\u0000"+
		"\u0000\u0000H@\u0001\u0000\u0000\u0000HA\u0001\u0000\u0000\u0000HB\u0001"+
		"\u0000\u0000\u0000HC\u0001\u0000\u0000\u0000HD\u0001\u0000\u0000\u0000"+
		"HE\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HG\u0001\u0000\u0000"+
		"\u0000I\t\u0001\u0000\u0000\u0000JK\u0005\n\u0000\u0000KL\u0003\u0006"+
		"\u0003\u0000LM\u0005\b\u0000\u0000MN\u0003\f\u0006\u0000NO\u0005\b\u0000"+
		"\u0000OP\u0003.\u0017\u0000P\u000b\u0001\u0000\u0000\u0000QR\u0005\u000b"+
		"\u0000\u0000R\r\u0001\u0000\u0000\u0000ST\u0005\f\u0000\u0000TU\u0003"+
		"\u0006\u0003\u0000UV\u0005\b\u0000\u0000VW\u0003\u0010\b\u0000WX\u0005"+
		"\b\u0000\u0000XY\u0003.\u0017\u0000Y\u000f\u0001\u0000\u0000\u0000Z[\u0005"+
		"\u0003\u0000\u0000[\u0011\u0001\u0000\u0000\u0000\\]\u0005\r\u0000\u0000"+
		"]^\u0003\u0006\u0003\u0000^_\u0005\b\u0000\u0000_`\u0003\u0014\n\u0000"+
		"`a\u0005\b\u0000\u0000ab\u0003.\u0017\u0000b\u0013\u0001\u0000\u0000\u0000"+
		"cd\u0005\u0004\u0000\u0000d\u0015\u0001\u0000\u0000\u0000ef\u0005\u000e"+
		"\u0000\u0000fg\u0003\u0006\u0003\u0000gh\u0005\b\u0000\u0000hi\u0003\u0018"+
		"\f\u0000ij\u0005\b\u0000\u0000jk\u0003.\u0017\u0000k\u0017\u0001\u0000"+
		"\u0000\u0000lq\u0005\u0001\u0000\u0000mn\u0005\u0001\u0000\u0000no\u0005"+
		"\b\u0000\u0000oq\u0003\u0018\f\u0000pl\u0001\u0000\u0000\u0000pm\u0001"+
		"\u0000\u0000\u0000q\u0019\u0001\u0000\u0000\u0000rs\u0005\u000f\u0000"+
		"\u0000st\u0003\u0006\u0003\u0000tu\u0005\b\u0000\u0000uv\u0003\u001c\u000e"+
		"\u0000vw\u0005\b\u0000\u0000wx\u0003.\u0017\u0000x\u001b\u0001\u0000\u0000"+
		"\u0000yz\u0005\u0010\u0000\u0000z\u001d\u0001\u0000\u0000\u0000{|\u0005"+
		"\u0011\u0000\u0000|}\u0003\u0006\u0003\u0000}~\u0005\b\u0000\u0000~\u007f"+
		"\u0003 \u0010\u0000\u007f\u0080\u0005\b\u0000\u0000\u0080\u0081\u0003"+
		".\u0017\u0000\u0081\u001f\u0001\u0000\u0000\u0000\u0082\u0083\u0005\u0001"+
		"\u0000\u0000\u0083!\u0001\u0000\u0000\u0000\u0084\u0085\u0005\u0012\u0000"+
		"\u0000\u0085\u0086\u0003\u0006\u0003\u0000\u0086\u0087\u0005\b\u0000\u0000"+
		"\u0087\u0088\u0003$\u0012\u0000\u0088\u0089\u0005\b\u0000\u0000\u0089"+
		"\u008a\u0003.\u0017\u0000\u008a#\u0001\u0000\u0000\u0000\u008b\u008c\u0005"+
		"\u0001\u0000\u0000\u008c%\u0001\u0000\u0000\u0000\u008d\u008e\u0005\u0013"+
		"\u0000\u0000\u008e\u008f\u0003\u0006\u0003\u0000\u008f\u0090\u0005\b\u0000"+
		"\u0000\u0090\u0091\u0003(\u0014\u0000\u0091\u0092\u0005\b\u0000\u0000"+
		"\u0092\u0093\u0003.\u0017\u0000\u0093\'\u0001\u0000\u0000\u0000\u0094"+
		"\u0095\u0005\u0014\u0000\u0000\u0095)\u0001\u0000\u0000\u0000\u0096\u0097"+
		"\u0005\u0015\u0000\u0000\u0097\u0098\u0003\u0006\u0003\u0000\u0098\u0099"+
		"\u0005\b\u0000\u0000\u0099\u009a\u0003,\u0016\u0000\u009a\u009b\u0003"+
		".\u0017\u0000\u009b+\u0001\u0000\u0000\u0000\u009c\u009d\u0005\u0016\u0000"+
		"\u0000\u009d-\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0007\u0000\u0000"+
		"\u009f\u00a2\u0005\u0006\u0000\u0000\u00a0\u00a2\u0005\u0007\u0000\u0000"+
		"\u00a1\u009e\u0001\u0000\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000\u0000"+
		"\u00a2/\u0001\u0000\u0000\u0000\u00048Hp\u00a1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}