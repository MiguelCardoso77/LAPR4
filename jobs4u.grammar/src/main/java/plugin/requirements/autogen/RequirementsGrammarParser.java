package plugin.requirements.autogen;// Generated from C:/Users/migue/IdeaProjects/lprog-plugin-2dd5/requirements/src/main/resources/RequirementsGrammar.g4 by ANTLR 4.13.1

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
public class RequirementsGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ARROW=1, COMMA=2, INTEGER=3, HASHTAG=4, PARAGRAPH=5, SPACE=6, REQUIREMENTS=7, 
		ACADEMIC_DEGREE=8, LANGUAGES=9, PROGRAMMING_LANGUAGES=10, YEARS_OF_EXPERIENCE=11, 
		NONE=12, BACHELOR=13, MASTER=14, DOCTORATE=15, ENGLISH=16, FRENCH=17, 
		GERMAN=18, ITALIAN=19, JAPANESE=20, PORTUGUESE=21, SPANISH=22, JAVA=23, 
		JAVASCRIPT=24, PYTHON=25, TYPESCRIPT=26, PHP=27, CHASH=28;
	public static final int
		RULE_start = 0, RULE_requirements = 1, RULE_requirementsList = 2, RULE_requirementType = 3, 
		RULE_academicDegree = 4, RULE_academicDegreeType = 5, RULE_languages = 6, 
		RULE_languagesSelection = 7, RULE_languagesType = 8, RULE_programmingLanguages = 9, 
		RULE_programmingLanguagesType = 10, RULE_programmingTypes = 11, RULE_yearsOfExperience = 12, 
		RULE_yearsOfExperienceType = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "plugin/requirements", "requirementsList", "requirementType", "academicDegree",
			"academicDegreeType", "languages", "languagesSelection", "languagesType", 
			"programmingLanguages", "programmingLanguagesType", "programmingTypes", 
			"yearsOfExperience", "yearsOfExperienceType"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-> '", "','", null, "'# '", null, "' '", "'Requirements:'", "'Academic Degree: '", 
			"'Languages: '", "'Programming Languages: '", "'Years of Experience: '", 
			"'None'", "'Bachelor'", "'Master'", "'Doctorate'", "'English'", "'French'", 
			"'German'", "'Italian'", "'Japanese'", "'Portuguese'", "'Spanish'", "'Java'", 
			"'JavaScript'", "'Python'", "'TypeScript'", "'PHP'", "'C#'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ARROW", "COMMA", "INTEGER", "HASHTAG", "PARAGRAPH", "SPACE", "REQUIREMENTS", 
			"ACADEMIC_DEGREE", "LANGUAGES", "PROGRAMMING_LANGUAGES", "YEARS_OF_EXPERIENCE", 
			"NONE", "BACHELOR", "MASTER", "DOCTORATE", "ENGLISH", "FRENCH", "GERMAN", 
			"ITALIAN", "JAPANESE", "PORTUGUESE", "SPANISH", "JAVA", "JAVASCRIPT", 
			"PYTHON", "TYPESCRIPT", "PHP", "CHASH"
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
	public String getGrammarFileName() { return "RequirementsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RequirementsGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public RequirementsContext requirements() {
			return getRuleContext(RequirementsContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			requirements();
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
	public static class RequirementsContext extends ParserRuleContext {
		public TerminalNode HASHTAG() { return getToken(RequirementsGrammarParser.HASHTAG, 0); }
		public TerminalNode REQUIREMENTS() { return getToken(RequirementsGrammarParser.REQUIREMENTS, 0); }
		public TerminalNode PARAGRAPH() { return getToken(RequirementsGrammarParser.PARAGRAPH, 0); }
		public List<RequirementsListContext> requirementsList() {
			return getRuleContexts(RequirementsListContext.class);
		}
		public RequirementsListContext requirementsList(int i) {
			return getRuleContext(RequirementsListContext.class,i);
		}
		public RequirementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterRequirements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitRequirements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitRequirements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequirementsContext requirements() throws RecognitionException {
		RequirementsContext _localctx = new RequirementsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_requirements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(HASHTAG);
			setState(31);
			match(REQUIREMENTS);
			setState(32);
			match(PARAGRAPH);
			setState(34); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(33);
				requirementsList();
				}
				}
				setState(36); 
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
	public static class RequirementsListContext extends ParserRuleContext {
		public TerminalNode ARROW() { return getToken(RequirementsGrammarParser.ARROW, 0); }
		public RequirementTypeContext requirementType() {
			return getRuleContext(RequirementTypeContext.class,0);
		}
		public TerminalNode PARAGRAPH() { return getToken(RequirementsGrammarParser.PARAGRAPH, 0); }
		public RequirementsListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirementsList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterRequirementsList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitRequirementsList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitRequirementsList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequirementsListContext requirementsList() throws RecognitionException {
		RequirementsListContext _localctx = new RequirementsListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_requirementsList);
		try {
			setState(44);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				match(ARROW);
				setState(39);
				requirementType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(40);
				match(ARROW);
				setState(41);
				requirementType();
				setState(42);
				match(PARAGRAPH);
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
	public static class RequirementTypeContext extends ParserRuleContext {
		public AcademicDegreeContext academicDegree() {
			return getRuleContext(AcademicDegreeContext.class,0);
		}
		public LanguagesContext languages() {
			return getRuleContext(LanguagesContext.class,0);
		}
		public ProgrammingLanguagesContext programmingLanguages() {
			return getRuleContext(ProgrammingLanguagesContext.class,0);
		}
		public YearsOfExperienceContext yearsOfExperience() {
			return getRuleContext(YearsOfExperienceContext.class,0);
		}
		public RequirementTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirementType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterRequirementType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitRequirementType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitRequirementType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequirementTypeContext requirementType() throws RecognitionException {
		RequirementTypeContext _localctx = new RequirementTypeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_requirementType);
		try {
			setState(50);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ACADEMIC_DEGREE:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				academicDegree();
				}
				break;
			case LANGUAGES:
				enterOuterAlt(_localctx, 2);
				{
				setState(47);
				languages();
				}
				break;
			case PROGRAMMING_LANGUAGES:
				enterOuterAlt(_localctx, 3);
				{
				setState(48);
				programmingLanguages();
				}
				break;
			case YEARS_OF_EXPERIENCE:
				enterOuterAlt(_localctx, 4);
				{
				setState(49);
				yearsOfExperience();
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
	public static class AcademicDegreeContext extends ParserRuleContext {
		public TerminalNode ACADEMIC_DEGREE() { return getToken(RequirementsGrammarParser.ACADEMIC_DEGREE, 0); }
		public AcademicDegreeTypeContext academicDegreeType() {
			return getRuleContext(AcademicDegreeTypeContext.class,0);
		}
		public AcademicDegreeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_academicDegree; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterAcademicDegree(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitAcademicDegree(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitAcademicDegree(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcademicDegreeContext academicDegree() throws RecognitionException {
		AcademicDegreeContext _localctx = new AcademicDegreeContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_academicDegree);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(ACADEMIC_DEGREE);
			setState(53);
			academicDegreeType();
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
	public static class AcademicDegreeTypeContext extends ParserRuleContext {
		public TerminalNode NONE() { return getToken(RequirementsGrammarParser.NONE, 0); }
		public TerminalNode BACHELOR() { return getToken(RequirementsGrammarParser.BACHELOR, 0); }
		public TerminalNode MASTER() { return getToken(RequirementsGrammarParser.MASTER, 0); }
		public TerminalNode DOCTORATE() { return getToken(RequirementsGrammarParser.DOCTORATE, 0); }
		public AcademicDegreeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_academicDegreeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterAcademicDegreeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitAcademicDegreeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitAcademicDegreeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AcademicDegreeTypeContext academicDegreeType() throws RecognitionException {
		AcademicDegreeTypeContext _localctx = new AcademicDegreeTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_academicDegreeType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 61440L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class LanguagesContext extends ParserRuleContext {
		public TerminalNode LANGUAGES() { return getToken(RequirementsGrammarParser.LANGUAGES, 0); }
		public LanguagesTypeContext languagesType() {
			return getRuleContext(LanguagesTypeContext.class,0);
		}
		public LanguagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languages; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterLanguages(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitLanguages(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitLanguages(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguagesContext languages() throws RecognitionException {
		LanguagesContext _localctx = new LanguagesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_languages);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(LANGUAGES);
			setState(58);
			languagesType();
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
	public static class LanguagesSelectionContext extends ParserRuleContext {
		public LanguagesTypeContext languagesType() {
			return getRuleContext(LanguagesTypeContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(RequirementsGrammarParser.COMMA, 0); }
		public TerminalNode SPACE() { return getToken(RequirementsGrammarParser.SPACE, 0); }
		public LanguagesSelectionContext languagesSelection() {
			return getRuleContext(LanguagesSelectionContext.class,0);
		}
		public LanguagesSelectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languagesSelection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterLanguagesSelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitLanguagesSelection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitLanguagesSelection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguagesSelectionContext languagesSelection() throws RecognitionException {
		LanguagesSelectionContext _localctx = new LanguagesSelectionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_languagesSelection);
		try {
			setState(66);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				languagesType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				languagesType();
				setState(62);
				match(COMMA);
				setState(63);
				match(SPACE);
				setState(64);
				languagesSelection();
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
	public static class LanguagesTypeContext extends ParserRuleContext {
		public TerminalNode ENGLISH() { return getToken(RequirementsGrammarParser.ENGLISH, 0); }
		public TerminalNode GERMAN() { return getToken(RequirementsGrammarParser.GERMAN, 0); }
		public TerminalNode JAPANESE() { return getToken(RequirementsGrammarParser.JAPANESE, 0); }
		public TerminalNode SPANISH() { return getToken(RequirementsGrammarParser.SPANISH, 0); }
		public TerminalNode FRENCH() { return getToken(RequirementsGrammarParser.FRENCH, 0); }
		public TerminalNode ITALIAN() { return getToken(RequirementsGrammarParser.ITALIAN, 0); }
		public TerminalNode PORTUGUESE() { return getToken(RequirementsGrammarParser.PORTUGUESE, 0); }
		public LanguagesTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_languagesType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterLanguagesType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitLanguagesType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitLanguagesType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LanguagesTypeContext languagesType() throws RecognitionException {
		LanguagesTypeContext _localctx = new LanguagesTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_languagesType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8323072L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class ProgrammingLanguagesContext extends ParserRuleContext {
		public TerminalNode PROGRAMMING_LANGUAGES() { return getToken(RequirementsGrammarParser.PROGRAMMING_LANGUAGES, 0); }
		public ProgrammingLanguagesTypeContext programmingLanguagesType() {
			return getRuleContext(ProgrammingLanguagesTypeContext.class,0);
		}
		public ProgrammingLanguagesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programmingLanguages; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterProgrammingLanguages(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitProgrammingLanguages(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitProgrammingLanguages(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgrammingLanguagesContext programmingLanguages() throws RecognitionException {
		ProgrammingLanguagesContext _localctx = new ProgrammingLanguagesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_programmingLanguages);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(PROGRAMMING_LANGUAGES);
			setState(71);
			programmingLanguagesType();
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
	public static class ProgrammingLanguagesTypeContext extends ParserRuleContext {
		public ProgrammingTypesContext programmingTypes() {
			return getRuleContext(ProgrammingTypesContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(RequirementsGrammarParser.COMMA, 0); }
		public TerminalNode SPACE() { return getToken(RequirementsGrammarParser.SPACE, 0); }
		public ProgrammingLanguagesTypeContext programmingLanguagesType() {
			return getRuleContext(ProgrammingLanguagesTypeContext.class,0);
		}
		public ProgrammingLanguagesTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programmingLanguagesType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterProgrammingLanguagesType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitProgrammingLanguagesType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitProgrammingLanguagesType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgrammingLanguagesTypeContext programmingLanguagesType() throws RecognitionException {
		ProgrammingLanguagesTypeContext _localctx = new ProgrammingLanguagesTypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_programmingLanguagesType);
		try {
			setState(79);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				programmingTypes();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74);
				programmingTypes();
				setState(75);
				match(COMMA);
				setState(76);
				match(SPACE);
				setState(77);
				programmingLanguagesType();
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
	public static class ProgrammingTypesContext extends ParserRuleContext {
		public TerminalNode JAVA() { return getToken(RequirementsGrammarParser.JAVA, 0); }
		public TerminalNode JAVASCRIPT() { return getToken(RequirementsGrammarParser.JAVASCRIPT, 0); }
		public TerminalNode PYTHON() { return getToken(RequirementsGrammarParser.PYTHON, 0); }
		public TerminalNode TYPESCRIPT() { return getToken(RequirementsGrammarParser.TYPESCRIPT, 0); }
		public TerminalNode PHP() { return getToken(RequirementsGrammarParser.PHP, 0); }
		public TerminalNode CHASH() { return getToken(RequirementsGrammarParser.CHASH, 0); }
		public ProgrammingTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programmingTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterProgrammingTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitProgrammingTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitProgrammingTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgrammingTypesContext programmingTypes() throws RecognitionException {
		ProgrammingTypesContext _localctx = new ProgrammingTypesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_programmingTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 528482304L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
	public static class YearsOfExperienceContext extends ParserRuleContext {
		public TerminalNode YEARS_OF_EXPERIENCE() { return getToken(RequirementsGrammarParser.YEARS_OF_EXPERIENCE, 0); }
		public YearsOfExperienceTypeContext yearsOfExperienceType() {
			return getRuleContext(YearsOfExperienceTypeContext.class,0);
		}
		public YearsOfExperienceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yearsOfExperience; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterYearsOfExperience(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitYearsOfExperience(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitYearsOfExperience(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YearsOfExperienceContext yearsOfExperience() throws RecognitionException {
		YearsOfExperienceContext _localctx = new YearsOfExperienceContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_yearsOfExperience);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(YEARS_OF_EXPERIENCE);
			setState(84);
			yearsOfExperienceType();
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
	public static class YearsOfExperienceTypeContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(RequirementsGrammarParser.INTEGER, 0); }
		public YearsOfExperienceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_yearsOfExperienceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).enterYearsOfExperienceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RequirementsGrammarListener ) ((RequirementsGrammarListener)listener).exitYearsOfExperienceType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RequirementsGrammarVisitor ) return ((RequirementsGrammarVisitor<? extends T>)visitor).visitYearsOfExperienceType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final YearsOfExperienceTypeContext yearsOfExperienceType() throws RecognitionException {
		YearsOfExperienceTypeContext _localctx = new YearsOfExperienceTypeContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_yearsOfExperienceType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
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

	public static final String _serializedATN =
		"\u0004\u0001\u001cY\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0004\u0001#\b\u0001\u000b\u0001\f\u0001$\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002-\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003"+
		"\u00033\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007C\b\u0007\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\nP\b\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\r\u0001\r\u0001\r\u0000\u0000\u000e\u0000\u0002\u0004\u0006\b"+
		"\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0003\u0001\u0000"+
		"\f\u000f\u0001\u0000\u0010\u0016\u0001\u0000\u0017\u001cQ\u0000\u001c"+
		"\u0001\u0000\u0000\u0000\u0002\u001e\u0001\u0000\u0000\u0000\u0004,\u0001"+
		"\u0000\u0000\u0000\u00062\u0001\u0000\u0000\u0000\b4\u0001\u0000\u0000"+
		"\u0000\n7\u0001\u0000\u0000\u0000\f9\u0001\u0000\u0000\u0000\u000eB\u0001"+
		"\u0000\u0000\u0000\u0010D\u0001\u0000\u0000\u0000\u0012F\u0001\u0000\u0000"+
		"\u0000\u0014O\u0001\u0000\u0000\u0000\u0016Q\u0001\u0000\u0000\u0000\u0018"+
		"S\u0001\u0000\u0000\u0000\u001aV\u0001\u0000\u0000\u0000\u001c\u001d\u0003"+
		"\u0002\u0001\u0000\u001d\u0001\u0001\u0000\u0000\u0000\u001e\u001f\u0005"+
		"\u0004\u0000\u0000\u001f \u0005\u0007\u0000\u0000 \"\u0005\u0005\u0000"+
		"\u0000!#\u0003\u0004\u0002\u0000\"!\u0001\u0000\u0000\u0000#$\u0001\u0000"+
		"\u0000\u0000$\"\u0001\u0000\u0000\u0000$%\u0001\u0000\u0000\u0000%\u0003"+
		"\u0001\u0000\u0000\u0000&\'\u0005\u0001\u0000\u0000\'-\u0003\u0006\u0003"+
		"\u0000()\u0005\u0001\u0000\u0000)*\u0003\u0006\u0003\u0000*+\u0005\u0005"+
		"\u0000\u0000+-\u0001\u0000\u0000\u0000,&\u0001\u0000\u0000\u0000,(\u0001"+
		"\u0000\u0000\u0000-\u0005\u0001\u0000\u0000\u0000.3\u0003\b\u0004\u0000"+
		"/3\u0003\f\u0006\u000003\u0003\u0012\t\u000013\u0003\u0018\f\u00002.\u0001"+
		"\u0000\u0000\u00002/\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u0000"+
		"21\u0001\u0000\u0000\u00003\u0007\u0001\u0000\u0000\u000045\u0005\b\u0000"+
		"\u000056\u0003\n\u0005\u00006\t\u0001\u0000\u0000\u000078\u0007\u0000"+
		"\u0000\u00008\u000b\u0001\u0000\u0000\u00009:\u0005\t\u0000\u0000:;\u0003"+
		"\u0010\b\u0000;\r\u0001\u0000\u0000\u0000<C\u0003\u0010\b\u0000=>\u0003"+
		"\u0010\b\u0000>?\u0005\u0002\u0000\u0000?@\u0005\u0006\u0000\u0000@A\u0003"+
		"\u000e\u0007\u0000AC\u0001\u0000\u0000\u0000B<\u0001\u0000\u0000\u0000"+
		"B=\u0001\u0000\u0000\u0000C\u000f\u0001\u0000\u0000\u0000DE\u0007\u0001"+
		"\u0000\u0000E\u0011\u0001\u0000\u0000\u0000FG\u0005\n\u0000\u0000GH\u0003"+
		"\u0014\n\u0000H\u0013\u0001\u0000\u0000\u0000IP\u0003\u0016\u000b\u0000"+
		"JK\u0003\u0016\u000b\u0000KL\u0005\u0002\u0000\u0000LM\u0005\u0006\u0000"+
		"\u0000MN\u0003\u0014\n\u0000NP\u0001\u0000\u0000\u0000OI\u0001\u0000\u0000"+
		"\u0000OJ\u0001\u0000\u0000\u0000P\u0015\u0001\u0000\u0000\u0000QR\u0007"+
		"\u0002\u0000\u0000R\u0017\u0001\u0000\u0000\u0000ST\u0005\u000b\u0000"+
		"\u0000TU\u0003\u001a\r\u0000U\u0019\u0001\u0000\u0000\u0000VW\u0005\u0003"+
		"\u0000\u0000W\u001b\u0001\u0000\u0000\u0000\u0005$,2BO";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}