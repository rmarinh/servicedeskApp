// Generated from /Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.grammars/src/main/java/scriptformulario/grammar/Script.g4 by ANTLR 4.9.1
package script.antl4Gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ScriptParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, Println=3, Print=4, Assert=5, SendEmail=6, CreateEmail=7, 
		Find=8, Replace=9, Match=10, Matcher=11, If=12, Else=13, Do=14, End=15, 
		Null=16, Or=17, And=18, Equals=19, NEquals=20, GTEquals=21, LTEquals=22, 
		Excl=23, GT=24, LT=25, Add=26, Subtract=27, Multiply=28, Divide=29, Modulus=30, 
		OParen=31, CParen=32, SColon=33, Assign=34, Comma=35, Bool=36, Date=37, 
		Number=38, Identificador=39, String=40, Comment=41, Space=42;
	public static final int
		RULE_parse = 0, RULE_bloco = 1, RULE_linha = 2, RULE_declaracao = 3, RULE_comando = 4, 
		RULE_ifBloco = 5, RULE_ifCondicional = 6, RULE_elseCondicional = 7, RULE_idList = 8, 
		RULE_expressao = 9, RULE_indexes = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "bloco", "linha", "declaracao", "comando", "ifBloco", "ifCondicional", 
			"elseCondicional", "idList", "expressao", "indexes"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'['", "']'", "'println'", "'print'", "'assert'", "'sendemail'", 
			"'createemail'", "'find'", "'replace'", "'match'", "'$'", "'if'", "'else'", 
			"'do'", "'end'", "'null'", "'||'", "'&&'", "'=='", "'!='", "'>='", "'<='", 
			"'!'", "'>'", "'<'", "'+'", "'-'", "'*'", "'/'", "'%'", "'('", "')'", 
			"';'", "'='", "','"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "Println", "Print", "Assert", "SendEmail", "CreateEmail", 
			"Find", "Replace", "Match", "Matcher", "If", "Else", "Do", "End", "Null", 
			"Or", "And", "Equals", "NEquals", "GTEquals", "LTEquals", "Excl", "GT", 
			"LT", "Add", "Subtract", "Multiply", "Divide", "Modulus", "OParen", "CParen", 
			"SColon", "Assign", "Comma", "Bool", "Date", "Number", "Identificador", 
			"String", "Comment", "Space"
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
	public String getGrammarFileName() { return "Script.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ScriptParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public TerminalNode EOF() { return getToken(ScriptParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			bloco();
			setState(23);
			match(EOF);
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

	public static class BlocoContext extends ParserRuleContext {
		public List<LinhaContext> linha() {
			return getRuleContexts(LinhaContext.class);
		}
		public LinhaContext linha(int i) {
			return getRuleContext(LinhaContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitBloco(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitBloco(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Println) | (1L << Print) | (1L << Assert) | (1L << SendEmail) | (1L << Find) | (1L << Replace) | (1L << Match) | (1L << If) | (1L << Identificador))) != 0)) {
				{
				{
				setState(25);
				linha();
				}
				}
				setState(30);
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

	public static class LinhaContext extends ParserRuleContext {
		public DeclaracaoContext declaracao() {
			return getRuleContext(DeclaracaoContext.class,0);
		}
		public TerminalNode SColon() { return getToken(ScriptParser.SColon, 0); }
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public IfBlocoContext ifBloco() {
			return getRuleContext(IfBlocoContext.class,0);
		}
		public LinhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterLinha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitLinha(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitLinha(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinhaContext linha() throws RecognitionException {
		LinhaContext _localctx = new LinhaContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_linha);
		try {
			setState(38);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Identificador:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				declaracao();
				setState(32);
				match(SColon);
				}
				break;
			case Println:
			case Print:
			case Assert:
			case SendEmail:
			case Find:
			case Replace:
			case Match:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				comando();
				setState(35);
				match(SColon);
				}
				break;
			case If:
				enterOuterAlt(_localctx, 3);
				{
				setState(37);
				ifBloco();
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

	public static class DeclaracaoContext extends ParserRuleContext {
		public TerminalNode Identificador() { return getToken(ScriptParser.Identificador, 0); }
		public TerminalNode Assign() { return getToken(ScriptParser.Assign, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public DeclaracaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaracao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterDeclaracao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitDeclaracao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitDeclaracao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclaracaoContext declaracao() throws RecognitionException {
		DeclaracaoContext _localctx = new DeclaracaoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_declaracao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(Identificador);
			setState(41);
			match(Assign);
			setState(42);
			expressao(0);
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

	public static class ComandoContext extends ParserRuleContext {
		public ComandoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comando; }
	 
		public ComandoContext() { }
		public void copyFrom(ComandoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssertComandoContext extends ComandoContext {
		public TerminalNode Assert() { return getToken(ScriptParser.Assert, 0); }
		public TerminalNode OParen() { return getToken(ScriptParser.OParen, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode CParen() { return getToken(ScriptParser.CParen, 0); }
		public AssertComandoContext(ComandoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterAssertComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitAssertComando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitAssertComando(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintComandoContext extends ComandoContext {
		public TerminalNode Print() { return getToken(ScriptParser.Print, 0); }
		public TerminalNode OParen() { return getToken(ScriptParser.OParen, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode CParen() { return getToken(ScriptParser.CParen, 0); }
		public PrintComandoContext(ComandoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterPrintComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitPrintComando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitPrintComando(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SendEmailComandoContext extends ComandoContext {
		public TerminalNode SendEmail() { return getToken(ScriptParser.SendEmail, 0); }
		public TerminalNode OParen() { return getToken(ScriptParser.OParen, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ScriptParser.Comma, i);
		}
		public TerminalNode CParen() { return getToken(ScriptParser.CParen, 0); }
		public SendEmailComandoContext(ComandoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterSendEmailComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitSendEmailComando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitSendEmailComando(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FindComandoContext extends ComandoContext {
		public TerminalNode Find() { return getToken(ScriptParser.Find, 0); }
		public TerminalNode OParen() { return getToken(ScriptParser.OParen, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ScriptParser.Comma, i);
		}
		public TerminalNode CParen() { return getToken(ScriptParser.CParen, 0); }
		public FindComandoContext(ComandoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterFindComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitFindComando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitFindComando(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ReplaceComandoContext extends ComandoContext {
		public TerminalNode Replace() { return getToken(ScriptParser.Replace, 0); }
		public TerminalNode OParen() { return getToken(ScriptParser.OParen, 0); }
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(ScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ScriptParser.Comma, i);
		}
		public TerminalNode CParen() { return getToken(ScriptParser.CParen, 0); }
		public ReplaceComandoContext(ComandoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterReplaceComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitReplaceComando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitReplaceComando(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MatchComandoContext extends ComandoContext {
		public TerminalNode Match() { return getToken(ScriptParser.Match, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public MatchComandoContext(ComandoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterMatchComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitMatchComando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitMatchComando(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class PrintlnComandoContext extends ComandoContext {
		public TerminalNode Println() { return getToken(ScriptParser.Println, 0); }
		public TerminalNode OParen() { return getToken(ScriptParser.OParen, 0); }
		public TerminalNode CParen() { return getToken(ScriptParser.CParen, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public PrintlnComandoContext(ComandoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterPrintlnComando(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitPrintlnComando(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitPrintlnComando(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComandoContext comando() throws RecognitionException {
		ComandoContext _localctx = new ComandoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_comando);
		int _la;
		try {
			int _alt;
			setState(101);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Println:
				_localctx = new PrintlnComandoContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				match(Println);
				setState(45);
				match(OParen);
				setState(47);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Println) | (1L << Print) | (1L << Assert) | (1L << SendEmail) | (1L << Find) | (1L << Replace) | (1L << Match) | (1L << Null) | (1L << OParen) | (1L << Bool) | (1L << Date) | (1L << Number) | (1L << Identificador) | (1L << String))) != 0)) {
					{
					setState(46);
					expressao(0);
					}
				}

				setState(49);
				match(CParen);
				}
				break;
			case Print:
				_localctx = new PrintComandoContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				match(Print);
				setState(51);
				match(OParen);
				setState(52);
				expressao(0);
				setState(53);
				match(CParen);
				}
				break;
			case Assert:
				_localctx = new AssertComandoContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(55);
				match(Assert);
				setState(56);
				match(OParen);
				setState(57);
				expressao(0);
				setState(58);
				match(CParen);
				}
				break;
			case SendEmail:
				_localctx = new SendEmailComandoContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(60);
				match(SendEmail);
				setState(61);
				match(OParen);
				setState(62);
				expressao(0);
				setState(63);
				match(Comma);
				setState(64);
				expressao(0);
				setState(65);
				match(Comma);
				setState(66);
				expressao(0);
				setState(67);
				match(CParen);
				}
				break;
			case Find:
				_localctx = new FindComandoContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(69);
				match(Find);
				setState(70);
				match(OParen);
				setState(71);
				expressao(0);
				setState(72);
				match(Comma);
				setState(73);
				expressao(0);
				setState(74);
				match(Comma);
				setState(75);
				expressao(0);
				setState(76);
				match(Comma);
				setState(77);
				expressao(0);
				setState(78);
				match(Comma);
				setState(84);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(79);
						expressao(0);
						setState(80);
						match(Comma);
						}
						} 
					}
					setState(86);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
				}
				setState(87);
				expressao(0);
				setState(88);
				match(CParen);
				}
				break;
			case Replace:
				_localctx = new ReplaceComandoContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(90);
				match(Replace);
				setState(91);
				match(OParen);
				setState(92);
				expressao(0);
				setState(93);
				match(Comma);
				setState(94);
				expressao(0);
				setState(95);
				match(Comma);
				setState(96);
				expressao(0);
				setState(97);
				match(CParen);
				}
				break;
			case Match:
				_localctx = new MatchComandoContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(99);
				match(Match);
				{
				setState(100);
				expressao(0);
				}
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

	public static class IfBlocoContext extends ParserRuleContext {
		public IfCondicionalContext ifCondicional() {
			return getRuleContext(IfCondicionalContext.class,0);
		}
		public TerminalNode End() { return getToken(ScriptParser.End, 0); }
		public ElseCondicionalContext elseCondicional() {
			return getRuleContext(ElseCondicionalContext.class,0);
		}
		public IfBlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterIfBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitIfBloco(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitIfBloco(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfBlocoContext ifBloco() throws RecognitionException {
		IfBlocoContext _localctx = new IfBlocoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_ifBloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			ifCondicional();
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Else) {
				{
				setState(104);
				elseCondicional();
				}
			}

			setState(107);
			match(End);
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

	public static class IfCondicionalContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(ScriptParser.If, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode Do() { return getToken(ScriptParser.Do, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public IfCondicionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCondicional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterIfCondicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitIfCondicional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitIfCondicional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfCondicionalContext ifCondicional() throws RecognitionException {
		IfCondicionalContext _localctx = new IfCondicionalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_ifCondicional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(If);
			setState(110);
			expressao(0);
			setState(111);
			match(Do);
			setState(112);
			bloco();
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

	public static class ElseCondicionalContext extends ParserRuleContext {
		public TerminalNode Else() { return getToken(ScriptParser.Else, 0); }
		public TerminalNode Do() { return getToken(ScriptParser.Do, 0); }
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ElseCondicionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseCondicional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterElseCondicional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitElseCondicional(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitElseCondicional(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ElseCondicionalContext elseCondicional() throws RecognitionException {
		ElseCondicionalContext _localctx = new ElseCondicionalContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_elseCondicional);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(Else);
			setState(115);
			match(Do);
			setState(116);
			bloco();
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

	public static class IdListContext extends ParserRuleContext {
		public List<TerminalNode> Identificador() { return getTokens(ScriptParser.Identificador); }
		public TerminalNode Identificador(int i) {
			return getToken(ScriptParser.Identificador, i);
		}
		public List<TerminalNode> Comma() { return getTokens(ScriptParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(ScriptParser.Comma, i);
		}
		public IdListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterIdList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitIdList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitIdList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdListContext idList() throws RecognitionException {
		IdListContext _localctx = new IdListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_idList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(Identificador);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Comma) {
				{
				{
				setState(119);
				match(Comma);
				setState(120);
				match(Identificador);
				}
				}
				setState(125);
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

	public static class ExpressaoContext extends ParserRuleContext {
		public ExpressaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressao; }
	 
		public ExpressaoContext() { }
		public void copyFrom(ExpressaoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AddExpressaoContext extends ExpressaoContext {
		public Token op;
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode Add() { return getToken(ScriptParser.Add, 0); }
		public TerminalNode Subtract() { return getToken(ScriptParser.Subtract, 0); }
		public AddExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterAddExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitAddExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitAddExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExpressaoExpressaoContext extends ExpressaoContext {
		public TerminalNode OParen() { return getToken(ScriptParser.OParen, 0); }
		public ExpressaoContext expressao() {
			return getRuleContext(ExpressaoContext.class,0);
		}
		public TerminalNode CParen() { return getToken(ScriptParser.CParen, 0); }
		public IndexesContext indexes() {
			return getRuleContext(IndexesContext.class,0);
		}
		public ExpressaoExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterExpressaoExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitExpressaoExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitExpressaoExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NullExpressaoContext extends ExpressaoContext {
		public TerminalNode Null() { return getToken(ScriptParser.Null, 0); }
		public NullExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterNullExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitNullExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitNullExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ComandoExpressaoContext extends ExpressaoContext {
		public ComandoContext comando() {
			return getRuleContext(ComandoContext.class,0);
		}
		public IndexesContext indexes() {
			return getRuleContext(IndexesContext.class,0);
		}
		public ComandoExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterComandoExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitComandoExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitComandoExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MultExpressaoContext extends ExpressaoContext {
		public Token op;
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode Multiply() { return getToken(ScriptParser.Multiply, 0); }
		public TerminalNode Divide() { return getToken(ScriptParser.Divide, 0); }
		public TerminalNode Modulus() { return getToken(ScriptParser.Modulus, 0); }
		public MultExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterMultExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitMultExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitMultExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolExpressaoContext extends ExpressaoContext {
		public TerminalNode Bool() { return getToken(ScriptParser.Bool, 0); }
		public BoolExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterBoolExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitBoolExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitBoolExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DateExpressaoContext extends ExpressaoContext {
		public TerminalNode Date() { return getToken(ScriptParser.Date, 0); }
		public DateExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterDateExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitDateExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitDateExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NumberExpressaoContext extends ExpressaoContext {
		public TerminalNode Number() { return getToken(ScriptParser.Number, 0); }
		public NumberExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterNumberExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitNumberExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitNumberExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StringExpressaoContext extends ExpressaoContext {
		public TerminalNode String() { return getToken(ScriptParser.String, 0); }
		public IndexesContext indexes() {
			return getRuleContext(IndexesContext.class,0);
		}
		public StringExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterStringExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitStringExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitStringExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IdentificadorExpressaoContext extends ExpressaoContext {
		public TerminalNode Identificador() { return getToken(ScriptParser.Identificador, 0); }
		public IndexesContext indexes() {
			return getRuleContext(IndexesContext.class,0);
		}
		public IdentificadorExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterIdentificadorExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitIdentificadorExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitIdentificadorExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class CompExpressaoContext extends ExpressaoContext {
		public Token op;
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode GTEquals() { return getToken(ScriptParser.GTEquals, 0); }
		public TerminalNode LTEquals() { return getToken(ScriptParser.LTEquals, 0); }
		public TerminalNode GT() { return getToken(ScriptParser.GT, 0); }
		public TerminalNode LT() { return getToken(ScriptParser.LT, 0); }
		public CompExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterCompExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitCompExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitCompExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MatchExpressaoContext extends ExpressaoContext {
		public Token op;
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode Matcher() { return getToken(ScriptParser.Matcher, 0); }
		public MatchExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterMatchExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitMatchExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitMatchExpressao(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class EqExpressaoContext extends ExpressaoContext {
		public Token op;
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public TerminalNode Equals() { return getToken(ScriptParser.Equals, 0); }
		public TerminalNode NEquals() { return getToken(ScriptParser.NEquals, 0); }
		public EqExpressaoContext(ExpressaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterEqExpressao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitEqExpressao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitEqExpressao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressaoContext expressao() throws RecognitionException {
		return expressao(0);
	}

	private ExpressaoContext expressao(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressaoContext _localctx = new ExpressaoContext(_ctx, _parentState);
		ExpressaoContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expressao, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				{
				_localctx = new NumberExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(127);
				match(Number);
				}
				break;
			case 2:
				{
				_localctx = new BoolExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(128);
				match(Bool);
				}
				break;
			case 3:
				{
				_localctx = new NullExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				match(Null);
				}
				break;
			case 4:
				{
				_localctx = new DateExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(Date);
				}
				break;
			case 5:
				{
				_localctx = new ComandoExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				comando();
				}
				break;
			case 6:
				{
				_localctx = new ComandoExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				comando();
				setState(134);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(133);
					indexes();
					}
					break;
				}
				}
				break;
			case 7:
				{
				_localctx = new IdentificadorExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(Identificador);
				}
				break;
			case 8:
				{
				_localctx = new IdentificadorExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(137);
				match(Identificador);
				setState(139);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(138);
					indexes();
					}
					break;
				}
				}
				break;
			case 9:
				{
				_localctx = new StringExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(141);
				match(String);
				setState(143);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(142);
					indexes();
					}
					break;
				}
				}
				break;
			case 10:
				{
				_localctx = new ExpressaoExpressaoContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				match(OParen);
				setState(146);
				expressao(0);
				setState(147);
				match(CParen);
				setState(149);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(148);
					indexes();
					}
					break;
				}
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(170);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(168);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new MultExpressaoContext(new ExpressaoContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expressao);
						setState(153);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(154);
						((MultExpressaoContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Multiply) | (1L << Divide) | (1L << Modulus))) != 0)) ) {
							((MultExpressaoContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(155);
						expressao(16);
						}
						break;
					case 2:
						{
						_localctx = new AddExpressaoContext(new ExpressaoContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expressao);
						setState(156);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(157);
						((AddExpressaoContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Add || _la==Subtract) ) {
							((AddExpressaoContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(158);
						expressao(15);
						}
						break;
					case 3:
						{
						_localctx = new CompExpressaoContext(new ExpressaoContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expressao);
						setState(159);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(160);
						((CompExpressaoContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GTEquals) | (1L << LTEquals) | (1L << GT) | (1L << LT))) != 0)) ) {
							((CompExpressaoContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(161);
						expressao(14);
						}
						break;
					case 4:
						{
						_localctx = new EqExpressaoContext(new ExpressaoContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expressao);
						setState(162);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(163);
						((EqExpressaoContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==Equals || _la==NEquals) ) {
							((EqExpressaoContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(164);
						expressao(13);
						}
						break;
					case 5:
						{
						_localctx = new MatchExpressaoContext(new ExpressaoContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expressao);
						setState(165);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(166);
						((MatchExpressaoContext)_localctx).op = match(Matcher);
						setState(167);
						expressao(12);
						}
						break;
					}
					} 
				}
				setState(172);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
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

	public static class IndexesContext extends ParserRuleContext {
		public List<ExpressaoContext> expressao() {
			return getRuleContexts(ExpressaoContext.class);
		}
		public ExpressaoContext expressao(int i) {
			return getRuleContext(ExpressaoContext.class,i);
		}
		public IndexesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).enterIndexes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ScriptListener ) ((ScriptListener)listener).exitIndexes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ScriptVisitor ) return ((ScriptVisitor<? extends T>)visitor).visitIndexes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexesContext indexes() throws RecognitionException {
		IndexesContext _localctx = new IndexesContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_indexes);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(173);
					match(T__0);
					setState(174);
					expressao(0);
					setState(175);
					match(T__1);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(179); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		case 9:
			return expressao_sempred((ExpressaoContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expressao_sempred(ExpressaoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 15);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3,\u00b8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\3\2\3\2\3\2\3\3\7\3\35\n\3\f\3\16\3 \13\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\5\4)\n\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6\62\n\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6U\n\6\f\6\16\6"+
		"X\13\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6h\n"+
		"\6\3\7\3\7\5\7l\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3"+
		"\n\3\n\7\n|\n\n\f\n\16\n\177\13\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\5\13\u0089\n\13\3\13\3\13\3\13\5\13\u008e\n\13\3\13\3\13\5\13\u0092"+
		"\n\13\3\13\3\13\3\13\3\13\5\13\u0098\n\13\5\13\u009a\n\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00ab"+
		"\n\13\f\13\16\13\u00ae\13\13\3\f\3\f\3\f\3\f\6\f\u00b4\n\f\r\f\16\f\u00b5"+
		"\3\f\2\3\24\r\2\4\6\b\n\f\16\20\22\24\26\2\6\3\2\36 \3\2\34\35\4\2\27"+
		"\30\32\33\3\2\25\26\2\u00cc\2\30\3\2\2\2\4\36\3\2\2\2\6(\3\2\2\2\b*\3"+
		"\2\2\2\ng\3\2\2\2\fi\3\2\2\2\16o\3\2\2\2\20t\3\2\2\2\22x\3\2\2\2\24\u0099"+
		"\3\2\2\2\26\u00b3\3\2\2\2\30\31\5\4\3\2\31\32\7\2\2\3\32\3\3\2\2\2\33"+
		"\35\5\6\4\2\34\33\3\2\2\2\35 \3\2\2\2\36\34\3\2\2\2\36\37\3\2\2\2\37\5"+
		"\3\2\2\2 \36\3\2\2\2!\"\5\b\5\2\"#\7#\2\2#)\3\2\2\2$%\5\n\6\2%&\7#\2\2"+
		"&)\3\2\2\2\')\5\f\7\2(!\3\2\2\2($\3\2\2\2(\'\3\2\2\2)\7\3\2\2\2*+\7)\2"+
		"\2+,\7$\2\2,-\5\24\13\2-\t\3\2\2\2./\7\5\2\2/\61\7!\2\2\60\62\5\24\13"+
		"\2\61\60\3\2\2\2\61\62\3\2\2\2\62\63\3\2\2\2\63h\7\"\2\2\64\65\7\6\2\2"+
		"\65\66\7!\2\2\66\67\5\24\13\2\678\7\"\2\28h\3\2\2\29:\7\7\2\2:;\7!\2\2"+
		";<\5\24\13\2<=\7\"\2\2=h\3\2\2\2>?\7\b\2\2?@\7!\2\2@A\5\24\13\2AB\7%\2"+
		"\2BC\5\24\13\2CD\7%\2\2DE\5\24\13\2EF\7\"\2\2Fh\3\2\2\2GH\7\n\2\2HI\7"+
		"!\2\2IJ\5\24\13\2JK\7%\2\2KL\5\24\13\2LM\7%\2\2MN\5\24\13\2NO\7%\2\2O"+
		"P\5\24\13\2PV\7%\2\2QR\5\24\13\2RS\7%\2\2SU\3\2\2\2TQ\3\2\2\2UX\3\2\2"+
		"\2VT\3\2\2\2VW\3\2\2\2WY\3\2\2\2XV\3\2\2\2YZ\5\24\13\2Z[\7\"\2\2[h\3\2"+
		"\2\2\\]\7\13\2\2]^\7!\2\2^_\5\24\13\2_`\7%\2\2`a\5\24\13\2ab\7%\2\2bc"+
		"\5\24\13\2cd\7\"\2\2dh\3\2\2\2ef\7\f\2\2fh\5\24\13\2g.\3\2\2\2g\64\3\2"+
		"\2\2g9\3\2\2\2g>\3\2\2\2gG\3\2\2\2g\\\3\2\2\2ge\3\2\2\2h\13\3\2\2\2ik"+
		"\5\16\b\2jl\5\20\t\2kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2mn\7\21\2\2n\r\3\2\2"+
		"\2op\7\16\2\2pq\5\24\13\2qr\7\20\2\2rs\5\4\3\2s\17\3\2\2\2tu\7\17\2\2"+
		"uv\7\20\2\2vw\5\4\3\2w\21\3\2\2\2x}\7)\2\2yz\7%\2\2z|\7)\2\2{y\3\2\2\2"+
		"|\177\3\2\2\2}{\3\2\2\2}~\3\2\2\2~\23\3\2\2\2\177}\3\2\2\2\u0080\u0081"+
		"\b\13\1\2\u0081\u009a\7(\2\2\u0082\u009a\7&\2\2\u0083\u009a\7\22\2\2\u0084"+
		"\u009a\7\'\2\2\u0085\u009a\5\n\6\2\u0086\u0088\5\n\6\2\u0087\u0089\5\26"+
		"\f\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u009a\3\2\2\2\u008a"+
		"\u009a\7)\2\2\u008b\u008d\7)\2\2\u008c\u008e\5\26\f\2\u008d\u008c\3\2"+
		"\2\2\u008d\u008e\3\2\2\2\u008e\u009a\3\2\2\2\u008f\u0091\7*\2\2\u0090"+
		"\u0092\5\26\f\2\u0091\u0090\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u009a\3"+
		"\2\2\2\u0093\u0094\7!\2\2\u0094\u0095\5\24\13\2\u0095\u0097\7\"\2\2\u0096"+
		"\u0098\5\26\f\2\u0097\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u009a\3"+
		"\2\2\2\u0099\u0080\3\2\2\2\u0099\u0082\3\2\2\2\u0099\u0083\3\2\2\2\u0099"+
		"\u0084\3\2\2\2\u0099\u0085\3\2\2\2\u0099\u0086\3\2\2\2\u0099\u008a\3\2"+
		"\2\2\u0099\u008b\3\2\2\2\u0099\u008f\3\2\2\2\u0099\u0093\3\2\2\2\u009a"+
		"\u00ac\3\2\2\2\u009b\u009c\f\21\2\2\u009c\u009d\t\2\2\2\u009d\u00ab\5"+
		"\24\13\22\u009e\u009f\f\20\2\2\u009f\u00a0\t\3\2\2\u00a0\u00ab\5\24\13"+
		"\21\u00a1\u00a2\f\17\2\2\u00a2\u00a3\t\4\2\2\u00a3\u00ab\5\24\13\20\u00a4"+
		"\u00a5\f\16\2\2\u00a5\u00a6\t\5\2\2\u00a6\u00ab\5\24\13\17\u00a7\u00a8"+
		"\f\r\2\2\u00a8\u00a9\7\r\2\2\u00a9\u00ab\5\24\13\16\u00aa\u009b\3\2\2"+
		"\2\u00aa\u009e\3\2\2\2\u00aa\u00a1\3\2\2\2\u00aa\u00a4\3\2\2\2\u00aa\u00a7"+
		"\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad"+
		"\25\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b0\7\3\2\2\u00b0\u00b1\5\24\13"+
		"\2\u00b1\u00b2\7\4\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00af\3\2\2\2\u00b4\u00b5"+
		"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\27\3\2\2\2\21\36"+
		"(\61Vgk}\u0088\u008d\u0091\u0097\u0099\u00aa\u00ac\u00b5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}