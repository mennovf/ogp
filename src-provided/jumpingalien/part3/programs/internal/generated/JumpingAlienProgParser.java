// Generated from JumpingAlienProg.g by ANTLR 4.5
package jumpingalien.part3.programs.internal.generated;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JumpingAlienProgParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, KIND_MAZUB=40, KIND_BUZAM=41, KIND_SLIME=42, KIND_SHARK=43, 
		KIND_PLANT=44, KIND_TERRAIN=45, KIND_ANY=46, ASCENDING=47, DESCENDING=48, 
		GETX=49, GETY=50, GETWIDTH=51, GETHEIGHT=52, GETHP=53, ISMAZUB=54, ISSHARK=55, 
		ISSLIME=56, ISPLANT=57, ISDEAD=58, ISTERRAIN=59, ISPASSABLE=60, ISWATER=61, 
		ISMAGMA=62, ISAIR=63, ISMOVING=64, ISDUCKING=65, ISJUMPING=66, BOOL_TRUE=67, 
		BOOL_FALSE=68, DIRECTION_LEFT=69, DIRECTION_RIGHT=70, DIRECTION_UP=71, 
		DIRECTION_DOWN=72, IDENTIFIER=73, LETTER=74, NUMBER=75, DIGIT=76, LT=77, 
		LTE=78, EQ=79, NEQ=80, GT=81, GTE=82, PLUS=83, MINUS=84, TIMES=85, DIV=86, 
		EMPTY_PAREN=87, WS=88;
	public static final int
		RULE_program = 0, RULE_declaration = 1, RULE_statement = 2, RULE_singleStatement = 3, 
		RULE_assignStatement = 4, RULE_whileStatement = 5, RULE_foreachStatement = 6, 
		RULE_breakStatement = 7, RULE_ifStatement = 8, RULE_printStatement = 9, 
		RULE_actionStatement = 10, RULE_startRunStatement = 11, RULE_stopRunStatement = 12, 
		RULE_startJumpStatement = 13, RULE_stopJumpStatement = 14, RULE_startDuckStatement = 15, 
		RULE_stopDuckStatement = 16, RULE_waitStatement = 17, RULE_skipStatement = 18, 
		RULE_expression = 19, RULE_literal = 20, RULE_type = 21, RULE_double_type = 22, 
		RULE_bool_type = 23, RULE_object_type = 24, RULE_direction_type = 25, 
		RULE_kind = 26, RULE_sortDirection = 27, RULE_direction = 28;
	public static final String[] ruleNames = {
		"program", "declaration", "statement", "singleStatement", "assignStatement", 
		"whileStatement", "foreachStatement", "breakStatement", "ifStatement", 
		"printStatement", "actionStatement", "startRunStatement", "stopRunStatement", 
		"startJumpStatement", "stopJumpStatement", "startDuckStatement", "stopDuckStatement", 
		"waitStatement", "skipStatement", "expression", "literal", "type", "double_type", 
		"bool_type", "object_type", "direction_type", "kind", "sortDirection", 
		"direction"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':='", "';'", "'while'", "'do'", "'done'", "'foreach'", "'('", 
		"','", "')'", "'where'", "'sort'", "'break'", "'if'", "'then'", "'else'", 
		"'fi'", "'print'", "'start_run'", "'stop_run'", "'start_jump'", "'stop_jump'", 
		"'start_duck'", "'stop_duck'", "'wait'", "'skip'", "'sqrt'", "'random'", 
		"'searchobj'", "'gettile'", "'!'", "'&&'", "'||'", "'.'", "'null'", "'self'", 
		"'double'", "'bool'", "'object'", "'direction'", "'mazub'", "'buzam'", 
		"'slime'", "'shark'", "'plant'", "'terrain'", "'any'", "'ascending'", 
		"'descending'", "'getx'", "'gety'", "'getwidth'", "'getheight'", "'gethp'", 
		"'ismazub'", "'isshark'", "'isslime'", "'isplant'", "'isdead'", "'isterrain'", 
		"'ispassable'", "'iswater'", "'ismagma'", "'isair'", "'ismoving'", "'isducking'", 
		"'isjumping'", "'true'", "'false'", "'left'", "'right'", "'up'", "'down'", 
		null, null, null, null, "'<'", "'<='", "'=='", "'!='", "'>'", "'>='", 
		"'+'", "'-'", "'*'", "'/'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "KIND_MAZUB", "KIND_BUZAM", "KIND_SLIME", "KIND_SHARK", 
		"KIND_PLANT", "KIND_TERRAIN", "KIND_ANY", "ASCENDING", "DESCENDING", "GETX", 
		"GETY", "GETWIDTH", "GETHEIGHT", "GETHP", "ISMAZUB", "ISSHARK", "ISSLIME", 
		"ISPLANT", "ISDEAD", "ISTERRAIN", "ISPASSABLE", "ISWATER", "ISMAGMA", 
		"ISAIR", "ISMOVING", "ISDUCKING", "ISJUMPING", "BOOL_TRUE", "BOOL_FALSE", 
		"DIRECTION_LEFT", "DIRECTION_RIGHT", "DIRECTION_UP", "DIRECTION_DOWN", 
		"IDENTIFIER", "LETTER", "NUMBER", "DIGIT", "LT", "LTE", "EQ", "NEQ", "GT", 
		"GTE", "PLUS", "MINUS", "TIMES", "DIV", "EMPTY_PAREN", "WS"
	};
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
	public String getGrammarFileName() { return "JumpingAlienProg.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JumpingAlienProgParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public StatementContext main;
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
				{
				{
				setState(58);
				declaration();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			((ProgramContext)_localctx).main = statement();
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

	public static class DeclarationContext extends ParserRuleContext {
		public TypeContext variableType;
		public Token variableName;
		public LiteralContext initialValue;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JumpingAlienProgParser.IDENTIFIER, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			((DeclarationContext)_localctx).variableType = type();
			setState(67);
			((DeclarationContext)_localctx).variableName = match(IDENTIFIER);
			setState(70);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(68);
				match(T__0);
				setState(69);
				((DeclarationContext)_localctx).initialValue = literal();
				}
			}

			setState(72);
			match(T__1);
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

	public static class StatementContext extends ParserRuleContext {
		public SingleStatementContext first;
		public SingleStatementContext singleStatement;
		public List<SingleStatementContext> rest = new ArrayList<SingleStatementContext>();
		public List<SingleStatementContext> singleStatement() {
			return getRuleContexts(SingleStatementContext.class);
		}
		public SingleStatementContext singleStatement(int i) {
			return getRuleContext(SingleStatementContext.class,i);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			((StatementContext)_localctx).first = singleStatement();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__5) | (1L << T__11) | (1L << T__12) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << T__22) | (1L << T__23) | (1L << T__24))) != 0) || _la==IDENTIFIER) {
				{
				{
				setState(75);
				((StatementContext)_localctx).singleStatement = singleStatement();
				((StatementContext)_localctx).rest.add(((StatementContext)_localctx).singleStatement);
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class SingleStatementContext extends ParserRuleContext {
		public AssignStatementContext assignStatement() {
			return getRuleContext(AssignStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public ForeachStatementContext foreachStatement() {
			return getRuleContext(ForeachStatementContext.class,0);
		}
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public PrintStatementContext printStatement() {
			return getRuleContext(PrintStatementContext.class,0);
		}
		public ActionStatementContext actionStatement() {
			return getRuleContext(ActionStatementContext.class,0);
		}
		public SingleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterSingleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitSingleStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitSingleStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleStatementContext singleStatement() throws RecognitionException {
		SingleStatementContext _localctx = new SingleStatementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_singleStatement);
		try {
			setState(88);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				assignStatement();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(82);
				whileStatement();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 3);
				{
				setState(83);
				foreachStatement();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(84);
				breakStatement();
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 5);
				{
				setState(85);
				ifStatement();
				}
				break;
			case T__16:
				enterOuterAlt(_localctx, 6);
				{
				setState(86);
				printStatement();
				}
				break;
			case T__17:
			case T__18:
			case T__19:
			case T__20:
			case T__21:
			case T__22:
			case T__23:
			case T__24:
				enterOuterAlt(_localctx, 7);
				{
				setState(87);
				actionStatement();
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

	public static class AssignStatementContext extends ParserRuleContext {
		public Token variableName;
		public ExpressionContext value;
		public TerminalNode IDENTIFIER() { return getToken(JumpingAlienProgParser.IDENTIFIER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterAssignStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitAssignStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitAssignStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignStatementContext assignStatement() throws RecognitionException {
		AssignStatementContext _localctx = new AssignStatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_assignStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			((AssignStatementContext)_localctx).variableName = match(IDENTIFIER);
			setState(91);
			match(T__0);
			setState(92);
			((AssignStatementContext)_localctx).value = expression(0);
			setState(93);
			match(T__1);
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

	public static class WhileStatementContext extends ParserRuleContext {
		public ExpressionContext condition;
		public StatementContext body;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__2);
			setState(96);
			((WhileStatementContext)_localctx).condition = expression(0);
			setState(97);
			match(T__3);
			setState(98);
			((WhileStatementContext)_localctx).body = statement();
			setState(99);
			match(T__4);
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

	public static class ForeachStatementContext extends ParserRuleContext {
		public KindContext variableKind;
		public Token variableName;
		public ExpressionContext whereExpr;
		public ExpressionContext sortExpr;
		public SortDirectionContext sortDir;
		public StatementContext body;
		public KindContext kind() {
			return getRuleContext(KindContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(JumpingAlienProgParser.IDENTIFIER, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SortDirectionContext sortDirection() {
			return getRuleContext(SortDirectionContext.class,0);
		}
		public ForeachStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreachStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterForeachStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitForeachStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitForeachStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeachStatementContext foreachStatement() throws RecognitionException {
		ForeachStatementContext _localctx = new ForeachStatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_foreachStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(T__5);
			setState(102);
			match(T__6);
			setState(103);
			((ForeachStatementContext)_localctx).variableKind = kind();
			setState(104);
			match(T__7);
			setState(105);
			((ForeachStatementContext)_localctx).variableName = match(IDENTIFIER);
			setState(106);
			match(T__8);
			setState(109);
			_la = _input.LA(1);
			if (_la==T__9) {
				{
				setState(107);
				match(T__9);
				setState(108);
				((ForeachStatementContext)_localctx).whereExpr = expression(0);
				}
			}

			setState(115);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(111);
				match(T__10);
				setState(112);
				((ForeachStatementContext)_localctx).sortExpr = expression(0);
				setState(113);
				((ForeachStatementContext)_localctx).sortDir = sortDirection();
				}
			}

			setState(117);
			match(T__3);
			setState(118);
			((ForeachStatementContext)_localctx).body = statement();
			setState(119);
			match(T__4);
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

	public static class BreakStatementContext extends ParserRuleContext {
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitBreakStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitBreakStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_breakStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(T__11);
			setState(122);
			match(T__1);
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

	public static class IfStatementContext extends ParserRuleContext {
		public ExpressionContext condition;
		public StatementContext ifbody;
		public StatementContext elsebody;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_ifStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(T__12);
			setState(125);
			((IfStatementContext)_localctx).condition = expression(0);
			setState(126);
			match(T__13);
			setState(127);
			((IfStatementContext)_localctx).ifbody = statement();
			setState(130);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(128);
				match(T__14);
				setState(129);
				((IfStatementContext)_localctx).elsebody = statement();
				}
			}

			setState(132);
			match(T__15);
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

	public static class PrintStatementContext extends ParserRuleContext {
		public ExpressionContext value;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public PrintStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_printStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterPrintStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitPrintStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitPrintStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrintStatementContext printStatement() throws RecognitionException {
		PrintStatementContext _localctx = new PrintStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_printStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(T__16);
			setState(135);
			((PrintStatementContext)_localctx).value = expression(0);
			setState(136);
			match(T__1);
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

	public static class ActionStatementContext extends ParserRuleContext {
		public StartRunStatementContext startRunStatement() {
			return getRuleContext(StartRunStatementContext.class,0);
		}
		public StopRunStatementContext stopRunStatement() {
			return getRuleContext(StopRunStatementContext.class,0);
		}
		public StartJumpStatementContext startJumpStatement() {
			return getRuleContext(StartJumpStatementContext.class,0);
		}
		public StopJumpStatementContext stopJumpStatement() {
			return getRuleContext(StopJumpStatementContext.class,0);
		}
		public StartDuckStatementContext startDuckStatement() {
			return getRuleContext(StartDuckStatementContext.class,0);
		}
		public StopDuckStatementContext stopDuckStatement() {
			return getRuleContext(StopDuckStatementContext.class,0);
		}
		public WaitStatementContext waitStatement() {
			return getRuleContext(WaitStatementContext.class,0);
		}
		public SkipStatementContext skipStatement() {
			return getRuleContext(SkipStatementContext.class,0);
		}
		public ActionStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actionStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterActionStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitActionStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitActionStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionStatementContext actionStatement() throws RecognitionException {
		ActionStatementContext _localctx = new ActionStatementContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_actionStatement);
		try {
			setState(146);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(138);
				startRunStatement();
				}
				break;
			case T__18:
				enterOuterAlt(_localctx, 2);
				{
				setState(139);
				stopRunStatement();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				startJumpStatement();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 4);
				{
				setState(141);
				stopJumpStatement();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 5);
				{
				setState(142);
				startDuckStatement();
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 6);
				{
				setState(143);
				stopDuckStatement();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 7);
				{
				setState(144);
				waitStatement();
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 8);
				{
				setState(145);
				skipStatement();
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

	public static class StartRunStatementContext extends ParserRuleContext {
		public ExpressionContext runDirection;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StartRunStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startRunStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterStartRunStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitStartRunStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitStartRunStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartRunStatementContext startRunStatement() throws RecognitionException {
		StartRunStatementContext _localctx = new StartRunStatementContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_startRunStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(T__17);
			setState(149);
			((StartRunStatementContext)_localctx).runDirection = expression(0);
			setState(150);
			match(T__1);
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

	public static class StopRunStatementContext extends ParserRuleContext {
		public ExpressionContext runDirection;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StopRunStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stopRunStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterStopRunStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitStopRunStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitStopRunStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StopRunStatementContext stopRunStatement() throws RecognitionException {
		StopRunStatementContext _localctx = new StopRunStatementContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_stopRunStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__18);
			setState(153);
			((StopRunStatementContext)_localctx).runDirection = expression(0);
			setState(154);
			match(T__1);
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

	public static class StartJumpStatementContext extends ParserRuleContext {
		public TerminalNode EMPTY_PAREN() { return getToken(JumpingAlienProgParser.EMPTY_PAREN, 0); }
		public StartJumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startJumpStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterStartJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitStartJumpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitStartJumpStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartJumpStatementContext startJumpStatement() throws RecognitionException {
		StartJumpStatementContext _localctx = new StartJumpStatementContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_startJumpStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(T__19);
			setState(158);
			_la = _input.LA(1);
			if (_la==EMPTY_PAREN) {
				{
				setState(157);
				match(EMPTY_PAREN);
				}
			}

			setState(160);
			match(T__1);
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

	public static class StopJumpStatementContext extends ParserRuleContext {
		public TerminalNode EMPTY_PAREN() { return getToken(JumpingAlienProgParser.EMPTY_PAREN, 0); }
		public StopJumpStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stopJumpStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterStopJumpStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitStopJumpStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitStopJumpStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StopJumpStatementContext stopJumpStatement() throws RecognitionException {
		StopJumpStatementContext _localctx = new StopJumpStatementContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stopJumpStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			match(T__20);
			setState(164);
			_la = _input.LA(1);
			if (_la==EMPTY_PAREN) {
				{
				setState(163);
				match(EMPTY_PAREN);
				}
			}

			setState(166);
			match(T__1);
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

	public static class StartDuckStatementContext extends ParserRuleContext {
		public TerminalNode EMPTY_PAREN() { return getToken(JumpingAlienProgParser.EMPTY_PAREN, 0); }
		public StartDuckStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startDuckStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterStartDuckStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitStartDuckStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitStartDuckStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartDuckStatementContext startDuckStatement() throws RecognitionException {
		StartDuckStatementContext _localctx = new StartDuckStatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_startDuckStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__21);
			setState(170);
			_la = _input.LA(1);
			if (_la==EMPTY_PAREN) {
				{
				setState(169);
				match(EMPTY_PAREN);
				}
			}

			setState(172);
			match(T__1);
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

	public static class StopDuckStatementContext extends ParserRuleContext {
		public TerminalNode EMPTY_PAREN() { return getToken(JumpingAlienProgParser.EMPTY_PAREN, 0); }
		public StopDuckStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stopDuckStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterStopDuckStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitStopDuckStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitStopDuckStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StopDuckStatementContext stopDuckStatement() throws RecognitionException {
		StopDuckStatementContext _localctx = new StopDuckStatementContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stopDuckStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__22);
			setState(176);
			_la = _input.LA(1);
			if (_la==EMPTY_PAREN) {
				{
				setState(175);
				match(EMPTY_PAREN);
				}
			}

			setState(178);
			match(T__1);
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

	public static class WaitStatementContext extends ParserRuleContext {
		public ExpressionContext duration;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WaitStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_waitStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterWaitStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitWaitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitWaitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WaitStatementContext waitStatement() throws RecognitionException {
		WaitStatementContext _localctx = new WaitStatementContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_waitStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(T__23);
			setState(181);
			((WaitStatementContext)_localctx).duration = expression(0);
			setState(182);
			match(T__1);
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

	public static class SkipStatementContext extends ParserRuleContext {
		public SkipStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skipStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterSkipStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitSkipStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitSkipStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkipStatementContext skipStatement() throws RecognitionException {
		SkipStatementContext _localctx = new SkipStatementContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_skipStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			match(T__24);
			setState(185);
			match(T__1);
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

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class RandomExprContext extends ExpressionContext {
		public ExpressionContext maxValue;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RandomExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterRandomExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitRandomExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitRandomExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SqrtExprContext extends ExpressionContext {
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SqrtExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterSqrtExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitSqrtExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitSqrtExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OrExprContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public OrExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterOrExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitOrExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitOrExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode TIMES() { return getToken(JumpingAlienProgParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(JumpingAlienProgParser.DIV, 0); }
		public MultExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterMultExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitMultExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitMultExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExprContext extends ExpressionContext {
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CmpExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode LT() { return getToken(JumpingAlienProgParser.LT, 0); }
		public TerminalNode LTE() { return getToken(JumpingAlienProgParser.LTE, 0); }
		public TerminalNode EQ() { return getToken(JumpingAlienProgParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(JumpingAlienProgParser.NEQ, 0); }
		public TerminalNode GTE() { return getToken(JumpingAlienProgParser.GTE, 0); }
		public TerminalNode GT() { return getToken(JumpingAlienProgParser.GT, 0); }
		public CmpExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterCmpExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitCmpExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitCmpExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarExprContext extends ExpressionContext {
		public Token variableName;
		public TerminalNode IDENTIFIER() { return getToken(JumpingAlienProgParser.IDENTIFIER, 0); }
		public VarExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitVarExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitVarExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NotExprContext extends ExpressionContext {
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public NotExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterNotExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitNotExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitNotExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetExprContext extends ExpressionContext {
		public Token attr;
		public ExpressionContext object;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode GETX() { return getToken(JumpingAlienProgParser.GETX, 0); }
		public TerminalNode GETY() { return getToken(JumpingAlienProgParser.GETY, 0); }
		public TerminalNode GETWIDTH() { return getToken(JumpingAlienProgParser.GETWIDTH, 0); }
		public TerminalNode GETHEIGHT() { return getToken(JumpingAlienProgParser.GETHEIGHT, 0); }
		public TerminalNode GETHP() { return getToken(JumpingAlienProgParser.GETHP, 0); }
		public GetExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterGetExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitGetExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitGetExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class GetTileExprContext extends ExpressionContext {
		public ExpressionContext xpos;
		public ExpressionContext ypos;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public GetTileExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterGetTileExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitGetTileExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitGetTileExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AddExprContext extends ExpressionContext {
		public ExpressionContext left;
		public Token op;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode PLUS() { return getToken(JumpingAlienProgParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(JumpingAlienProgParser.MINUS, 0); }
		public AddExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LiteralExprContext extends ExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public LiteralExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsMovingContext extends ExpressionContext {
		public ExpressionContext obj;
		public ExpressionContext isMovingDirection;
		public TerminalNode ISMOVING() { return getToken(JumpingAlienProgParser.ISMOVING, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IsMovingContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterIsMoving(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitIsMoving(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitIsMoving(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SearchObjExprContext extends ExpressionContext {
		public ExpressionContext searchDirection;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SearchObjExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterSearchObjExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitSearchObjExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitSearchObjExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IsExprContext extends ExpressionContext {
		public Token test;
		public ExpressionContext obj;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ISMAZUB() { return getToken(JumpingAlienProgParser.ISMAZUB, 0); }
		public TerminalNode ISSHARK() { return getToken(JumpingAlienProgParser.ISSHARK, 0); }
		public TerminalNode ISSLIME() { return getToken(JumpingAlienProgParser.ISSLIME, 0); }
		public TerminalNode ISPLANT() { return getToken(JumpingAlienProgParser.ISPLANT, 0); }
		public TerminalNode ISDEAD() { return getToken(JumpingAlienProgParser.ISDEAD, 0); }
		public TerminalNode ISTERRAIN() { return getToken(JumpingAlienProgParser.ISTERRAIN, 0); }
		public TerminalNode ISPASSABLE() { return getToken(JumpingAlienProgParser.ISPASSABLE, 0); }
		public TerminalNode ISWATER() { return getToken(JumpingAlienProgParser.ISWATER, 0); }
		public TerminalNode ISMAGMA() { return getToken(JumpingAlienProgParser.ISMAGMA, 0); }
		public TerminalNode ISAIR() { return getToken(JumpingAlienProgParser.ISAIR, 0); }
		public TerminalNode ISDUCKING() { return getToken(JumpingAlienProgParser.ISDUCKING, 0); }
		public TerminalNode ISJUMPING() { return getToken(JumpingAlienProgParser.ISJUMPING, 0); }
		public IsExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterIsExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitIsExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitIsExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AndExprContext extends ExpressionContext {
		public ExpressionContext left;
		public ExpressionContext right;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public AndExprContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterAndExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitAndExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitAndExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			switch (_input.LA(1)) {
			case T__25:
				{
				_localctx = new SqrtExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(188);
				match(T__25);
				setState(189);
				((SqrtExprContext)_localctx).expr = expression(16);
				}
				break;
			case T__26:
				{
				_localctx = new RandomExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(190);
				match(T__26);
				setState(191);
				((RandomExprContext)_localctx).maxValue = expression(15);
				}
				break;
			case GETX:
			case GETY:
			case GETWIDTH:
			case GETHEIGHT:
			case GETHP:
				{
				_localctx = new GetExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(192);
				((GetExprContext)_localctx).attr = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GETX) | (1L << GETY) | (1L << GETWIDTH) | (1L << GETHEIGHT) | (1L << GETHP))) != 0)) ) {
					((GetExprContext)_localctx).attr = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(193);
				((GetExprContext)_localctx).object = expression(14);
				}
				break;
			case T__27:
				{
				_localctx = new SearchObjExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(194);
				match(T__27);
				setState(195);
				((SearchObjExprContext)_localctx).searchDirection = expression(13);
				}
				break;
			case ISMAZUB:
			case ISSHARK:
			case ISSLIME:
			case ISPLANT:
			case ISDEAD:
			case ISTERRAIN:
			case ISPASSABLE:
			case ISWATER:
			case ISMAGMA:
			case ISAIR:
			case ISDUCKING:
			case ISJUMPING:
				{
				_localctx = new IsExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(196);
				((IsExprContext)_localctx).test = _input.LT(1);
				_la = _input.LA(1);
				if ( !(((((_la - 54)) & ~0x3f) == 0 && ((1L << (_la - 54)) & ((1L << (ISMAZUB - 54)) | (1L << (ISSHARK - 54)) | (1L << (ISSLIME - 54)) | (1L << (ISPLANT - 54)) | (1L << (ISDEAD - 54)) | (1L << (ISTERRAIN - 54)) | (1L << (ISPASSABLE - 54)) | (1L << (ISWATER - 54)) | (1L << (ISMAGMA - 54)) | (1L << (ISAIR - 54)) | (1L << (ISDUCKING - 54)) | (1L << (ISJUMPING - 54)))) != 0)) ) {
					((IsExprContext)_localctx).test = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(197);
				((IsExprContext)_localctx).obj = expression(11);
				}
				break;
			case T__29:
				{
				_localctx = new NotExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(198);
				match(T__29);
				setState(199);
				((NotExprContext)_localctx).expr = expression(6);
				}
				break;
			case T__28:
				{
				_localctx = new GetTileExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(200);
				match(T__28);
				setState(201);
				match(T__6);
				setState(202);
				((GetTileExprContext)_localctx).xpos = expression(0);
				setState(203);
				match(T__7);
				setState(204);
				((GetTileExprContext)_localctx).ypos = expression(0);
				setState(205);
				match(T__8);
				}
				break;
			case ISMOVING:
				{
				_localctx = new IsMovingContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(207);
				match(ISMOVING);
				setState(208);
				match(T__6);
				setState(209);
				((IsMovingContext)_localctx).obj = expression(0);
				setState(210);
				match(T__7);
				setState(211);
				((IsMovingContext)_localctx).isMovingDirection = expression(0);
				setState(212);
				match(T__8);
				}
				break;
			case IDENTIFIER:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(214);
				((VarExprContext)_localctx).variableName = match(IDENTIFIER);
				}
				break;
			case T__33:
			case T__34:
			case BOOL_TRUE:
			case BOOL_FALSE:
			case DIRECTION_LEFT:
			case DIRECTION_RIGHT:
			case DIRECTION_UP:
			case DIRECTION_DOWN:
			case NUMBER:
			case PLUS:
			case MINUS:
				{
				_localctx = new LiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(215);
				literal();
				}
				break;
			case T__6:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(216);
				match(T__6);
				setState(217);
				((ParenExprContext)_localctx).expr = expression(0);
				setState(218);
				match(T__8);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(239);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(237);
					switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
					case 1:
						{
						_localctx = new MultExprContext(new ExpressionContext(_parentctx, _parentState));
						((MultExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(222);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(223);
						((MultExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==TIMES || _la==DIV) ) {
							((MultExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(224);
						((MultExprContext)_localctx).right = expression(10);
						}
						break;
					case 2:
						{
						_localctx = new AddExprContext(new ExpressionContext(_parentctx, _parentState));
						((AddExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(225);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(226);
						((AddExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==PLUS || _la==MINUS) ) {
							((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(227);
						((AddExprContext)_localctx).right = expression(9);
						}
						break;
					case 3:
						{
						_localctx = new CmpExprContext(new ExpressionContext(_parentctx, _parentState));
						((CmpExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(228);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(229);
						((CmpExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & ((1L << (LT - 77)) | (1L << (LTE - 77)) | (1L << (EQ - 77)) | (1L << (NEQ - 77)) | (1L << (GT - 77)) | (1L << (GTE - 77)))) != 0)) ) {
							((CmpExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(230);
						((CmpExprContext)_localctx).right = expression(8);
						}
						break;
					case 4:
						{
						_localctx = new AndExprContext(new ExpressionContext(_parentctx, _parentState));
						((AndExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(231);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(232);
						match(T__30);
						setState(233);
						((AndExprContext)_localctx).right = expression(6);
						}
						break;
					case 5:
						{
						_localctx = new OrExprContext(new ExpressionContext(_parentctx, _parentState));
						((OrExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(234);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(235);
						match(T__31);
						setState(236);
						((OrExprContext)_localctx).right = expression(5);
						}
						break;
					}
					} 
				}
				setState(241);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NullLiteralContext extends LiteralContext {
		public NullLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterNullLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitNullLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitNullLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SelfLiteralContext extends LiteralContext {
		public SelfLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterSelfLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitSelfLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitSelfLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntLiteralContext extends LiteralContext {
		public Token value;
		public TerminalNode NUMBER() { return getToken(JumpingAlienProgParser.NUMBER, 0); }
		public TerminalNode PLUS() { return getToken(JumpingAlienProgParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(JumpingAlienProgParser.MINUS, 0); }
		public IntLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterIntLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitIntLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitIntLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DoubleLiteralContext extends LiteralContext {
		public List<TerminalNode> NUMBER() { return getTokens(JumpingAlienProgParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(JumpingAlienProgParser.NUMBER, i);
		}
		public TerminalNode PLUS() { return getToken(JumpingAlienProgParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(JumpingAlienProgParser.MINUS, 0); }
		public DoubleLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterDoubleLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitDoubleLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitDoubleLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DirectionLiteralContext extends LiteralContext {
		public DirectionContext directionLiteral;
		public DirectionContext direction() {
			return getRuleContext(DirectionContext.class,0);
		}
		public DirectionLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterDirectionLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitDirectionLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitDirectionLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolLiteralContext extends LiteralContext {
		public TerminalNode BOOL_TRUE() { return getToken(JumpingAlienProgParser.BOOL_TRUE, 0); }
		public TerminalNode BOOL_FALSE() { return getToken(JumpingAlienProgParser.BOOL_FALSE, 0); }
		public BoolLiteralContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterBoolLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitBoolLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitBoolLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_literal);
		int _la;
		try {
			setState(256);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				_localctx = new IntLiteralContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(242);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(245);
				((IntLiteralContext)_localctx).value = match(NUMBER);
				}
				break;
			case 2:
				_localctx = new DoubleLiteralContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				_la = _input.LA(1);
				if (_la==PLUS || _la==MINUS) {
					{
					setState(246);
					_la = _input.LA(1);
					if ( !(_la==PLUS || _la==MINUS) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				setState(249);
				match(NUMBER);
				setState(250);
				match(T__32);
				setState(251);
				match(NUMBER);
				}
				break;
			case 3:
				_localctx = new BoolLiteralContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(252);
				_la = _input.LA(1);
				if ( !(_la==BOOL_TRUE || _la==BOOL_FALSE) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 4:
				_localctx = new DirectionLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(253);
				((DirectionLiteralContext)_localctx).directionLiteral = direction();
				}
				break;
			case 5:
				_localctx = new NullLiteralContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(254);
				match(T__33);
				}
				break;
			case 6:
				_localctx = new SelfLiteralContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(255);
				match(T__34);
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

	public static class TypeContext extends ParserRuleContext {
		public Double_typeContext double_type() {
			return getRuleContext(Double_typeContext.class,0);
		}
		public Bool_typeContext bool_type() {
			return getRuleContext(Bool_typeContext.class,0);
		}
		public Object_typeContext object_type() {
			return getRuleContext(Object_typeContext.class,0);
		}
		public Direction_typeContext direction_type() {
			return getRuleContext(Direction_typeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_type);
		try {
			setState(262);
			switch (_input.LA(1)) {
			case T__35:
				enterOuterAlt(_localctx, 1);
				{
				setState(258);
				double_type();
				}
				break;
			case T__36:
				enterOuterAlt(_localctx, 2);
				{
				setState(259);
				bool_type();
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 3);
				{
				setState(260);
				object_type();
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 4);
				{
				setState(261);
				direction_type();
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

	public static class Double_typeContext extends ParserRuleContext {
		public Double_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_double_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterDouble_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitDouble_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitDouble_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Double_typeContext double_type() throws RecognitionException {
		Double_typeContext _localctx = new Double_typeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_double_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(264);
			match(T__35);
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

	public static class Bool_typeContext extends ParserRuleContext {
		public Bool_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterBool_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitBool_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitBool_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_typeContext bool_type() throws RecognitionException {
		Bool_typeContext _localctx = new Bool_typeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_bool_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(T__36);
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

	public static class Object_typeContext extends ParserRuleContext {
		public Object_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_object_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterObject_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitObject_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitObject_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Object_typeContext object_type() throws RecognitionException {
		Object_typeContext _localctx = new Object_typeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_object_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(268);
			match(T__37);
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

	public static class Direction_typeContext extends ParserRuleContext {
		public Direction_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_direction_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterDirection_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitDirection_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitDirection_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Direction_typeContext direction_type() throws RecognitionException {
		Direction_typeContext _localctx = new Direction_typeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_direction_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(T__38);
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

	public static class KindContext extends ParserRuleContext {
		public TerminalNode KIND_MAZUB() { return getToken(JumpingAlienProgParser.KIND_MAZUB, 0); }
		public TerminalNode KIND_BUZAM() { return getToken(JumpingAlienProgParser.KIND_BUZAM, 0); }
		public TerminalNode KIND_SLIME() { return getToken(JumpingAlienProgParser.KIND_SLIME, 0); }
		public TerminalNode KIND_SHARK() { return getToken(JumpingAlienProgParser.KIND_SHARK, 0); }
		public TerminalNode KIND_PLANT() { return getToken(JumpingAlienProgParser.KIND_PLANT, 0); }
		public TerminalNode KIND_TERRAIN() { return getToken(JumpingAlienProgParser.KIND_TERRAIN, 0); }
		public TerminalNode KIND_ANY() { return getToken(JumpingAlienProgParser.KIND_ANY, 0); }
		public KindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_kind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterKind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitKind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitKind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KindContext kind() throws RecognitionException {
		KindContext _localctx = new KindContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_kind);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << KIND_MAZUB) | (1L << KIND_BUZAM) | (1L << KIND_SLIME) | (1L << KIND_SHARK) | (1L << KIND_PLANT) | (1L << KIND_TERRAIN) | (1L << KIND_ANY))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class SortDirectionContext extends ParserRuleContext {
		public TerminalNode ASCENDING() { return getToken(JumpingAlienProgParser.ASCENDING, 0); }
		public TerminalNode DESCENDING() { return getToken(JumpingAlienProgParser.DESCENDING, 0); }
		public SortDirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sortDirection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterSortDirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitSortDirection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitSortDirection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortDirectionContext sortDirection() throws RecognitionException {
		SortDirectionContext _localctx = new SortDirectionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_sortDirection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			_la = _input.LA(1);
			if ( !(_la==ASCENDING || _la==DESCENDING) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class DirectionContext extends ParserRuleContext {
		public TerminalNode DIRECTION_LEFT() { return getToken(JumpingAlienProgParser.DIRECTION_LEFT, 0); }
		public TerminalNode DIRECTION_RIGHT() { return getToken(JumpingAlienProgParser.DIRECTION_RIGHT, 0); }
		public TerminalNode DIRECTION_UP() { return getToken(JumpingAlienProgParser.DIRECTION_UP, 0); }
		public TerminalNode DIRECTION_DOWN() { return getToken(JumpingAlienProgParser.DIRECTION_DOWN, 0); }
		public DirectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_direction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).enterDirection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JumpingAlienProgListener ) ((JumpingAlienProgListener)listener).exitDirection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JumpingAlienProgVisitor ) return ((JumpingAlienProgVisitor<? extends T>)visitor).visitDirection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectionContext direction() throws RecognitionException {
		DirectionContext _localctx = new DirectionContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_direction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			_la = _input.LA(1);
			if ( !(((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & ((1L << (DIRECTION_LEFT - 69)) | (1L << (DIRECTION_RIGHT - 69)) | (1L << (DIRECTION_UP - 69)) | (1L << (DIRECTION_DOWN - 69)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 19:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 9);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 7);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3Z\u0119\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\7\2>\n\2\f\2\16"+
		"\2A\13\2\3\2\3\2\3\3\3\3\3\3\3\3\5\3I\n\3\3\3\3\3\3\4\3\4\7\4O\n\4\f\4"+
		"\16\4R\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5[\n\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\bp\n\b\3\b\3"+
		"\b\3\b\3\b\5\bv\n\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\5\n\u0085\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\5\f\u0095\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\5\17"+
		"\u00a1\n\17\3\17\3\17\3\20\3\20\5\20\u00a7\n\20\3\20\3\20\3\21\3\21\5"+
		"\21\u00ad\n\21\3\21\3\21\3\22\3\22\5\22\u00b3\n\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u00df\n\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\7\25\u00f0\n\25\f\25\16\25\u00f3\13\25\3\26\5\26\u00f6\n\26\3\26\3\26"+
		"\5\26\u00fa\n\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0103\n\26\3"+
		"\27\3\27\3\27\3\27\5\27\u0109\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\2\3(\37\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\13\3\2\63\67\4\28ACD\3\2W"+
		"X\3\2UV\3\2OT\3\2EF\3\2*\60\3\2\61\62\3\2GJ\u012b\2?\3\2\2\2\4D\3\2\2"+
		"\2\6L\3\2\2\2\bZ\3\2\2\2\n\\\3\2\2\2\fa\3\2\2\2\16g\3\2\2\2\20{\3\2\2"+
		"\2\22~\3\2\2\2\24\u0088\3\2\2\2\26\u0094\3\2\2\2\30\u0096\3\2\2\2\32\u009a"+
		"\3\2\2\2\34\u009e\3\2\2\2\36\u00a4\3\2\2\2 \u00aa\3\2\2\2\"\u00b0\3\2"+
		"\2\2$\u00b6\3\2\2\2&\u00ba\3\2\2\2(\u00de\3\2\2\2*\u0102\3\2\2\2,\u0108"+
		"\3\2\2\2.\u010a\3\2\2\2\60\u010c\3\2\2\2\62\u010e\3\2\2\2\64\u0110\3\2"+
		"\2\2\66\u0112\3\2\2\28\u0114\3\2\2\2:\u0116\3\2\2\2<>\5\4\3\2=<\3\2\2"+
		"\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\5\6\4\2C\3\3\2"+
		"\2\2DE\5,\27\2EH\7K\2\2FG\7\3\2\2GI\5*\26\2HF\3\2\2\2HI\3\2\2\2IJ\3\2"+
		"\2\2JK\7\4\2\2K\5\3\2\2\2LP\5\b\5\2MO\5\b\5\2NM\3\2\2\2OR\3\2\2\2PN\3"+
		"\2\2\2PQ\3\2\2\2Q\7\3\2\2\2RP\3\2\2\2S[\5\n\6\2T[\5\f\7\2U[\5\16\b\2V"+
		"[\5\20\t\2W[\5\22\n\2X[\5\24\13\2Y[\5\26\f\2ZS\3\2\2\2ZT\3\2\2\2ZU\3\2"+
		"\2\2ZV\3\2\2\2ZW\3\2\2\2ZX\3\2\2\2ZY\3\2\2\2[\t\3\2\2\2\\]\7K\2\2]^\7"+
		"\3\2\2^_\5(\25\2_`\7\4\2\2`\13\3\2\2\2ab\7\5\2\2bc\5(\25\2cd\7\6\2\2d"+
		"e\5\6\4\2ef\7\7\2\2f\r\3\2\2\2gh\7\b\2\2hi\7\t\2\2ij\5\66\34\2jk\7\n\2"+
		"\2kl\7K\2\2lo\7\13\2\2mn\7\f\2\2np\5(\25\2om\3\2\2\2op\3\2\2\2pu\3\2\2"+
		"\2qr\7\r\2\2rs\5(\25\2st\58\35\2tv\3\2\2\2uq\3\2\2\2uv\3\2\2\2vw\3\2\2"+
		"\2wx\7\6\2\2xy\5\6\4\2yz\7\7\2\2z\17\3\2\2\2{|\7\16\2\2|}\7\4\2\2}\21"+
		"\3\2\2\2~\177\7\17\2\2\177\u0080\5(\25\2\u0080\u0081\7\20\2\2\u0081\u0084"+
		"\5\6\4\2\u0082\u0083\7\21\2\2\u0083\u0085\5\6\4\2\u0084\u0082\3\2\2\2"+
		"\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\22\2\2\u0087\23"+
		"\3\2\2\2\u0088\u0089\7\23\2\2\u0089\u008a\5(\25\2\u008a\u008b\7\4\2\2"+
		"\u008b\25\3\2\2\2\u008c\u0095\5\30\r\2\u008d\u0095\5\32\16\2\u008e\u0095"+
		"\5\34\17\2\u008f\u0095\5\36\20\2\u0090\u0095\5 \21\2\u0091\u0095\5\"\22"+
		"\2\u0092\u0095\5$\23\2\u0093\u0095\5&\24\2\u0094\u008c\3\2\2\2\u0094\u008d"+
		"\3\2\2\2\u0094\u008e\3\2\2\2\u0094\u008f\3\2\2\2\u0094\u0090\3\2\2\2\u0094"+
		"\u0091\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\27\3\2\2"+
		"\2\u0096\u0097\7\24\2\2\u0097\u0098\5(\25\2\u0098\u0099\7\4\2\2\u0099"+
		"\31\3\2\2\2\u009a\u009b\7\25\2\2\u009b\u009c\5(\25\2\u009c\u009d\7\4\2"+
		"\2\u009d\33\3\2\2\2\u009e\u00a0\7\26\2\2\u009f\u00a1\7Y\2\2\u00a0\u009f"+
		"\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\7\4\2\2\u00a3"+
		"\35\3\2\2\2\u00a4\u00a6\7\27\2\2\u00a5\u00a7\7Y\2\2\u00a6\u00a5\3\2\2"+
		"\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\7\4\2\2\u00a9\37"+
		"\3\2\2\2\u00aa\u00ac\7\30\2\2\u00ab\u00ad\7Y\2\2\u00ac\u00ab\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\7\4\2\2\u00af!\3\2\2\2"+
		"\u00b0\u00b2\7\31\2\2\u00b1\u00b3\7Y\2\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\7\4\2\2\u00b5#\3\2\2\2\u00b6"+
		"\u00b7\7\32\2\2\u00b7\u00b8\5(\25\2\u00b8\u00b9\7\4\2\2\u00b9%\3\2\2\2"+
		"\u00ba\u00bb\7\33\2\2\u00bb\u00bc\7\4\2\2\u00bc\'\3\2\2\2\u00bd\u00be"+
		"\b\25\1\2\u00be\u00bf\7\34\2\2\u00bf\u00df\5(\25\22\u00c0\u00c1\7\35\2"+
		"\2\u00c1\u00df\5(\25\21\u00c2\u00c3\t\2\2\2\u00c3\u00df\5(\25\20\u00c4"+
		"\u00c5\7\36\2\2\u00c5\u00df\5(\25\17\u00c6\u00c7\t\3\2\2\u00c7\u00df\5"+
		"(\25\r\u00c8\u00c9\7 \2\2\u00c9\u00df\5(\25\b\u00ca\u00cb\7\37\2\2\u00cb"+
		"\u00cc\7\t\2\2\u00cc\u00cd\5(\25\2\u00cd\u00ce\7\n\2\2\u00ce\u00cf\5("+
		"\25\2\u00cf\u00d0\7\13\2\2\u00d0\u00df\3\2\2\2\u00d1\u00d2\7B\2\2\u00d2"+
		"\u00d3\7\t\2\2\u00d3\u00d4\5(\25\2\u00d4\u00d5\7\n\2\2\u00d5\u00d6\5("+
		"\25\2\u00d6\u00d7\7\13\2\2\u00d7\u00df\3\2\2\2\u00d8\u00df\7K\2\2\u00d9"+
		"\u00df\5*\26\2\u00da\u00db\7\t\2\2\u00db\u00dc\5(\25\2\u00dc\u00dd\7\13"+
		"\2\2\u00dd\u00df\3\2\2\2\u00de\u00bd\3\2\2\2\u00de\u00c0\3\2\2\2\u00de"+
		"\u00c2\3\2\2\2\u00de\u00c4\3\2\2\2\u00de\u00c6\3\2\2\2\u00de\u00c8\3\2"+
		"\2\2\u00de\u00ca\3\2\2\2\u00de\u00d1\3\2\2\2\u00de\u00d8\3\2\2\2\u00de"+
		"\u00d9\3\2\2\2\u00de\u00da\3\2\2\2\u00df\u00f1\3\2\2\2\u00e0\u00e1\f\13"+
		"\2\2\u00e1\u00e2\t\4\2\2\u00e2\u00f0\5(\25\f\u00e3\u00e4\f\n\2\2\u00e4"+
		"\u00e5\t\5\2\2\u00e5\u00f0\5(\25\13\u00e6\u00e7\f\t\2\2\u00e7\u00e8\t"+
		"\6\2\2\u00e8\u00f0\5(\25\n\u00e9\u00ea\f\7\2\2\u00ea\u00eb\7!\2\2\u00eb"+
		"\u00f0\5(\25\b\u00ec\u00ed\f\6\2\2\u00ed\u00ee\7\"\2\2\u00ee\u00f0\5("+
		"\25\7\u00ef\u00e0\3\2\2\2\u00ef\u00e3\3\2\2\2\u00ef\u00e6\3\2\2\2\u00ef"+
		"\u00e9\3\2\2\2\u00ef\u00ec\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1\u00ef\3\2"+
		"\2\2\u00f1\u00f2\3\2\2\2\u00f2)\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4\u00f6"+
		"\t\5\2\2\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7"+
		"\u0103\7M\2\2\u00f8\u00fa\t\5\2\2\u00f9\u00f8\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\7M\2\2\u00fc\u00fd\7#\2\2\u00fd\u0103"+
		"\7M\2\2\u00fe\u0103\t\7\2\2\u00ff\u0103\5:\36\2\u0100\u0103\7$\2\2\u0101"+
		"\u0103\7%\2\2\u0102\u00f5\3\2\2\2\u0102\u00f9\3\2\2\2\u0102\u00fe\3\2"+
		"\2\2\u0102\u00ff\3\2\2\2\u0102\u0100\3\2\2\2\u0102\u0101\3\2\2\2\u0103"+
		"+\3\2\2\2\u0104\u0109\5.\30\2\u0105\u0109\5\60\31\2\u0106\u0109\5\62\32"+
		"\2\u0107\u0109\5\64\33\2\u0108\u0104\3\2\2\2\u0108\u0105\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0108\u0107\3\2\2\2\u0109-\3\2\2\2\u010a\u010b\7&\2\2\u010b"+
		"/\3\2\2\2\u010c\u010d\7\'\2\2\u010d\61\3\2\2\2\u010e\u010f\7(\2\2\u010f"+
		"\63\3\2\2\2\u0110\u0111\7)\2\2\u0111\65\3\2\2\2\u0112\u0113\t\b\2\2\u0113"+
		"\67\3\2\2\2\u0114\u0115\t\t\2\2\u01159\3\2\2\2\u0116\u0117\t\n\2\2\u0117"+
		";\3\2\2\2\25?HPZou\u0084\u0094\u00a0\u00a6\u00ac\u00b2\u00de\u00ef\u00f1"+
		"\u00f5\u00f9\u0102\u0108";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
