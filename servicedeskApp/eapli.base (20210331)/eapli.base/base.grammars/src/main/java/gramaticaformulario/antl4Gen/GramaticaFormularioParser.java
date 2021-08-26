// Generated from /Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.grammars/src/main/java/gramaticaformulario/grammar/GramaticaFormulario.g4 by ANTLR 4.9.1
package gramaticaformulario.antl4Gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GramaticaFormularioParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, WS=13, NEWLINE=14, TIPOSTRING=15, TIPOBOOLEANO=16, 
		TIPONUMERICO=17, TIPODATA=18, Prologo=19, Epilogo=20, DATA=21, NUMERICO=22, 
		YEAR=23, MONTH=24, DAY=25, BOOLEANO=26, TRUE=27, FALSE=28, TEXTO=29, REGEX=30;
	public static final int
		RULE_prog = 0, RULE_init = 1, RULE_formularioID = 2, RULE_atributos = 3, 
		RULE_atributo = 4, RULE_abrirAtributo = 5, RULE_fecharAtributo = 6, RULE_campos = 7, 
		RULE_nome = 8, RULE_titulo = 9, RULE_descricao = 10, RULE_validadores = 11, 
		RULE_validador = 12, RULE_preenchimentoString = 13, RULE_preenchimentoBooleano = 14, 
		RULE_preenchimentoNumerico = 15, RULE_preenchimentoData = 16, RULE_error = 17, 
		RULE_epilogo = 18;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "init", "formularioID", "atributos", "atributo", "abrirAtributo", 
			"fecharAtributo", "campos", "nome", "titulo", "descricao", "validadores", 
			"validador", "preenchimentoString", "preenchimentoBooleano", "preenchimentoNumerico", 
			"preenchimentoData", "error", "epilogo"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'<formulario><nome>'", "'</nome><atributos>'", "'<atributo>'", 
			"'</atributo>'", "'<nome>'", "'</nome>'", "'<titulo>'", "'</titulo>'", 
			"'<descricao>'", "'</descricao>'", "'</validador><preenchimento>'", "'</preenchimento>'", 
			null, null, "'<tipoDados>String</tipoDados><validador>'", "'<tipoDados>Boolean</tipoDados><validador>'", 
			"'<tipoDados>Numerico</tipoDados><validador>'", "'<tipoDados>Data</tipoDados><validador>'", 
			"'<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "WS", "NEWLINE", "TIPOSTRING", "TIPOBOOLEANO", "TIPONUMERICO", 
			"TIPODATA", "Prologo", "Epilogo", "DATA", "NUMERICO", "YEAR", "MONTH", 
			"DAY", "BOOLEANO", "TRUE", "FALSE", "TEXTO", "REGEX"
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
	public String getGrammarFileName() { return "GramaticaFormulario.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public GramaticaFormularioParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public InitContext init() {
			return getRuleContext(InitContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			init();
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

	public static class InitContext extends ParserRuleContext {
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
	 
		public InitContext() { }
		public void copyFrom(InitContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrintAtributoContext extends InitContext {
		public TerminalNode Prologo() { return getToken(GramaticaFormularioParser.Prologo, 0); }
		public FormularioIDContext formularioID() {
			return getRuleContext(FormularioIDContext.class,0);
		}
		public AtributosContext atributos() {
			return getRuleContext(AtributosContext.class,0);
		}
		public EpilogoContext epilogo() {
			return getRuleContext(EpilogoContext.class,0);
		}
		public PrintAtributoContext(InitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterPrintAtributo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitPrintAtributo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitPrintAtributo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_init);
		try {
			_localctx = new PrintAtributoContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(Prologo);
			setState(41);
			formularioID();
			setState(42);
			atributos();
			setState(43);
			epilogo();
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

	public static class FormularioIDContext extends ParserRuleContext {
		public TerminalNode TEXTO() { return getToken(GramaticaFormularioParser.TEXTO, 0); }
		public FormularioIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formularioID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterFormularioID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitFormularioID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitFormularioID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormularioIDContext formularioID() throws RecognitionException {
		FormularioIDContext _localctx = new FormularioIDContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_formularioID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			match(T__0);
			setState(46);
			match(TEXTO);
			setState(47);
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

	public static class AtributosContext extends ParserRuleContext {
		public List<AtributoContext> atributo() {
			return getRuleContexts(AtributoContext.class);
		}
		public AtributoContext atributo(int i) {
			return getRuleContext(AtributoContext.class,i);
		}
		public AtributosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterAtributos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitAtributos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitAtributos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtributosContext atributos() throws RecognitionException {
		AtributosContext _localctx = new AtributosContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_atributos);
		int _la;
		try {
			setState(57);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(49);
				atributo();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(50);
				atributo();
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(51);
					atributo();
					}
					}
					setState(56);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class AtributoContext extends ParserRuleContext {
		public AtributoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atributo; }
	 
		public AtributoContext() { }
		public void copyFrom(AtributoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtributoSingularContext extends AtributoContext {
		public AbrirAtributoContext abrirAtributo() {
			return getRuleContext(AbrirAtributoContext.class,0);
		}
		public CamposContext campos() {
			return getRuleContext(CamposContext.class,0);
		}
		public FecharAtributoContext fecharAtributo() {
			return getRuleContext(FecharAtributoContext.class,0);
		}
		public AtributoSingularContext(AtributoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterAtributoSingular(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitAtributoSingular(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitAtributoSingular(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtributoContext atributo() throws RecognitionException {
		AtributoContext _localctx = new AtributoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_atributo);
		try {
			_localctx = new AtributoSingularContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(59);
			abrirAtributo();
			setState(60);
			campos();
			setState(61);
			fecharAtributo();
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

	public static class AbrirAtributoContext extends ParserRuleContext {
		public AbrirAtributoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abrirAtributo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterAbrirAtributo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitAbrirAtributo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitAbrirAtributo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbrirAtributoContext abrirAtributo() throws RecognitionException {
		AbrirAtributoContext _localctx = new AbrirAtributoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_abrirAtributo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__2);
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

	public static class FecharAtributoContext extends ParserRuleContext {
		public FecharAtributoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fecharAtributo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterFecharAtributo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitFecharAtributo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitFecharAtributo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FecharAtributoContext fecharAtributo() throws RecognitionException {
		FecharAtributoContext _localctx = new FecharAtributoContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fecharAtributo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(T__3);
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

	public static class CamposContext extends ParserRuleContext {
		public NomeContext nome() {
			return getRuleContext(NomeContext.class,0);
		}
		public TituloContext titulo() {
			return getRuleContext(TituloContext.class,0);
		}
		public DescricaoContext descricao() {
			return getRuleContext(DescricaoContext.class,0);
		}
		public ValidadoresContext validadores() {
			return getRuleContext(ValidadoresContext.class,0);
		}
		public CamposContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_campos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterCampos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitCampos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitCampos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CamposContext campos() throws RecognitionException {
		CamposContext _localctx = new CamposContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_campos);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			nome();
			setState(68);
			titulo();
			setState(69);
			descricao();
			setState(70);
			validadores();
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

	public static class NomeContext extends ParserRuleContext {
		public TerminalNode TEXTO() { return getToken(GramaticaFormularioParser.TEXTO, 0); }
		public NomeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nome; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterNome(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitNome(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitNome(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NomeContext nome() throws RecognitionException {
		NomeContext _localctx = new NomeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nome);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__4);
			setState(73);
			match(TEXTO);
			setState(74);
			match(T__5);
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

	public static class TituloContext extends ParserRuleContext {
		public TerminalNode TEXTO() { return getToken(GramaticaFormularioParser.TEXTO, 0); }
		public TituloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_titulo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterTitulo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitTitulo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitTitulo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TituloContext titulo() throws RecognitionException {
		TituloContext _localctx = new TituloContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_titulo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(T__6);
			setState(77);
			match(TEXTO);
			setState(78);
			match(T__7);
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

	public static class DescricaoContext extends ParserRuleContext {
		public TerminalNode TEXTO() { return getToken(GramaticaFormularioParser.TEXTO, 0); }
		public DescricaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_descricao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterDescricao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitDescricao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitDescricao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescricaoContext descricao() throws RecognitionException {
		DescricaoContext _localctx = new DescricaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_descricao);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__8);
			setState(81);
			match(TEXTO);
			setState(82);
			match(T__9);
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

	public static class ValidadoresContext extends ParserRuleContext {
		public TerminalNode TIPOSTRING() { return getToken(GramaticaFormularioParser.TIPOSTRING, 0); }
		public ValidadorContext validador() {
			return getRuleContext(ValidadorContext.class,0);
		}
		public PreenchimentoStringContext preenchimentoString() {
			return getRuleContext(PreenchimentoStringContext.class,0);
		}
		public ErrorContext error() {
			return getRuleContext(ErrorContext.class,0);
		}
		public TerminalNode TIPOBOOLEANO() { return getToken(GramaticaFormularioParser.TIPOBOOLEANO, 0); }
		public PreenchimentoBooleanoContext preenchimentoBooleano() {
			return getRuleContext(PreenchimentoBooleanoContext.class,0);
		}
		public TerminalNode TIPONUMERICO() { return getToken(GramaticaFormularioParser.TIPONUMERICO, 0); }
		public PreenchimentoNumericoContext preenchimentoNumerico() {
			return getRuleContext(PreenchimentoNumericoContext.class,0);
		}
		public TerminalNode TIPODATA() { return getToken(GramaticaFormularioParser.TIPODATA, 0); }
		public PreenchimentoDataContext preenchimentoData() {
			return getRuleContext(PreenchimentoDataContext.class,0);
		}
		public ValidadoresContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validadores; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterValidadores(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitValidadores(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitValidadores(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValidadoresContext validadores() throws RecognitionException {
		ValidadoresContext _localctx = new ValidadoresContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_validadores);
		int _la;
		try {
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TIPOSTRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(84);
				match(TIPOSTRING);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TEXTO || _la==REGEX) {
					{
					setState(85);
					validador();
					}
				}

				setState(88);
				match(T__10);
				setState(90);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(89);
					preenchimentoString();
					}
					break;
				}
				setState(93);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(92);
					error();
					}
					break;
				}
				setState(95);
				match(T__11);
				}
				break;
			case TIPOBOOLEANO:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				match(TIPOBOOLEANO);
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TEXTO || _la==REGEX) {
					{
					setState(97);
					validador();
					}
				}

				setState(100);
				match(T__10);
				setState(102);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(101);
					preenchimentoBooleano();
					}
					break;
				}
				setState(105);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(104);
					error();
					}
					break;
				}
				setState(107);
				match(T__11);
				}
				break;
			case TIPONUMERICO:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				match(TIPONUMERICO);
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TEXTO || _la==REGEX) {
					{
					setState(109);
					validador();
					}
				}

				setState(112);
				match(T__10);
				setState(114);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(113);
					preenchimentoNumerico();
					}
					break;
				}
				setState(117);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(116);
					error();
					}
					break;
				}
				setState(119);
				match(T__11);
				}
				break;
			case TIPODATA:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				match(TIPODATA);
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==TEXTO || _la==REGEX) {
					{
					setState(121);
					validador();
					}
				}

				setState(124);
				match(T__10);
				setState(126);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
				case 1:
					{
					setState(125);
					preenchimentoData();
					}
					break;
				}
				setState(129);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
				case 1:
					{
					setState(128);
					error();
					}
					break;
				}
				setState(131);
				match(T__11);
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

	public static class ValidadorContext extends ParserRuleContext {
		public TerminalNode TEXTO() { return getToken(GramaticaFormularioParser.TEXTO, 0); }
		public TerminalNode REGEX() { return getToken(GramaticaFormularioParser.REGEX, 0); }
		public ValidadorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_validador; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterValidador(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitValidador(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitValidador(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValidadorContext validador() throws RecognitionException {
		ValidadorContext _localctx = new ValidadorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_validador);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			_la = _input.LA(1);
			if ( !(_la==TEXTO || _la==REGEX) ) {
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

	public static class PreenchimentoStringContext extends ParserRuleContext {
		public PreenchimentoStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preenchimentoString; }
	 
		public PreenchimentoStringContext() { }
		public void copyFrom(PreenchimentoStringContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UserInputStringContext extends PreenchimentoStringContext {
		public TerminalNode TEXTO() { return getToken(GramaticaFormularioParser.TEXTO, 0); }
		public UserInputStringContext(PreenchimentoStringContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterUserInputString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitUserInputString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitUserInputString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreenchimentoStringContext preenchimentoString() throws RecognitionException {
		PreenchimentoStringContext _localctx = new PreenchimentoStringContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_preenchimentoString);
		try {
			_localctx = new UserInputStringContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(TEXTO);
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

	public static class PreenchimentoBooleanoContext extends ParserRuleContext {
		public PreenchimentoBooleanoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preenchimentoBooleano; }
	 
		public PreenchimentoBooleanoContext() { }
		public void copyFrom(PreenchimentoBooleanoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UserInputBooleanContext extends PreenchimentoBooleanoContext {
		public TerminalNode BOOLEANO() { return getToken(GramaticaFormularioParser.BOOLEANO, 0); }
		public UserInputBooleanContext(PreenchimentoBooleanoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterUserInputBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitUserInputBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitUserInputBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreenchimentoBooleanoContext preenchimentoBooleano() throws RecognitionException {
		PreenchimentoBooleanoContext _localctx = new PreenchimentoBooleanoContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_preenchimentoBooleano);
		try {
			_localctx = new UserInputBooleanContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(BOOLEANO);
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

	public static class PreenchimentoNumericoContext extends ParserRuleContext {
		public PreenchimentoNumericoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preenchimentoNumerico; }
	 
		public PreenchimentoNumericoContext() { }
		public void copyFrom(PreenchimentoNumericoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UserInputNumericoContext extends PreenchimentoNumericoContext {
		public TerminalNode NUMERICO() { return getToken(GramaticaFormularioParser.NUMERICO, 0); }
		public UserInputNumericoContext(PreenchimentoNumericoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterUserInputNumerico(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitUserInputNumerico(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitUserInputNumerico(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreenchimentoNumericoContext preenchimentoNumerico() throws RecognitionException {
		PreenchimentoNumericoContext _localctx = new PreenchimentoNumericoContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_preenchimentoNumerico);
		try {
			_localctx = new UserInputNumericoContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(NUMERICO);
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

	public static class PreenchimentoDataContext extends ParserRuleContext {
		public PreenchimentoDataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preenchimentoData; }
	 
		public PreenchimentoDataContext() { }
		public void copyFrom(PreenchimentoDataContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UserInputDataContext extends PreenchimentoDataContext {
		public TerminalNode DATA() { return getToken(GramaticaFormularioParser.DATA, 0); }
		public UserInputDataContext(PreenchimentoDataContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterUserInputData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitUserInputData(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitUserInputData(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreenchimentoDataContext preenchimentoData() throws RecognitionException {
		PreenchimentoDataContext _localctx = new PreenchimentoDataContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_preenchimentoData);
		try {
			_localctx = new UserInputDataContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(DATA);
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

	public static class ErrorContext extends ParserRuleContext {
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			matchWildcard();
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

	public static class EpilogoContext extends ParserRuleContext {
		public EpilogoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_epilogo; }
	 
		public EpilogoContext() { }
		public void copyFrom(EpilogoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ScriptValidacaoContext extends EpilogoContext {
		public TerminalNode Epilogo() { return getToken(GramaticaFormularioParser.Epilogo, 0); }
		public ScriptValidacaoContext(EpilogoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).enterScriptValidacao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof GramaticaFormularioListener ) ((GramaticaFormularioListener)listener).exitScriptValidacao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof GramaticaFormularioVisitor ) return ((GramaticaFormularioVisitor<? extends T>)visitor).visitScriptValidacao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EpilogoContext epilogo() throws RecognitionException {
		EpilogoContext _localctx = new EpilogoContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_epilogo);
		try {
			_localctx = new ScriptValidacaoContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(Epilogo);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u0097\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\7\5\67\n\5\f\5\16\5:\13\5\5\5<\n\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\5\rY\n\r\3\r\3\r\5\r]\n\r\3\r\5\r`\n\r\3\r\3\r\3\r\5\r"+
		"e\n\r\3\r\3\r\5\ri\n\r\3\r\5\rl\n\r\3\r\3\r\3\r\5\rq\n\r\3\r\3\r\5\ru"+
		"\n\r\3\r\5\rx\n\r\3\r\3\r\3\r\5\r}\n\r\3\r\3\r\5\r\u0081\n\r\3\r\5\r\u0084"+
		"\n\r\3\r\5\r\u0087\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3"+
		"\22\3\23\3\23\3\24\3\24\3\24\2\2\25\2\4\6\b\n\f\16\20\22\24\26\30\32\34"+
		"\36 \"$&\2\3\3\2\37 \2\u0094\2(\3\2\2\2\4*\3\2\2\2\6/\3\2\2\2\b;\3\2\2"+
		"\2\n=\3\2\2\2\fA\3\2\2\2\16C\3\2\2\2\20E\3\2\2\2\22J\3\2\2\2\24N\3\2\2"+
		"\2\26R\3\2\2\2\30\u0086\3\2\2\2\32\u0088\3\2\2\2\34\u008a\3\2\2\2\36\u008c"+
		"\3\2\2\2 \u008e\3\2\2\2\"\u0090\3\2\2\2$\u0092\3\2\2\2&\u0094\3\2\2\2"+
		"()\5\4\3\2)\3\3\2\2\2*+\7\25\2\2+,\5\6\4\2,-\5\b\5\2-.\5&\24\2.\5\3\2"+
		"\2\2/\60\7\3\2\2\60\61\7\37\2\2\61\62\7\4\2\2\62\7\3\2\2\2\63<\5\n\6\2"+
		"\648\5\n\6\2\65\67\5\n\6\2\66\65\3\2\2\2\67:\3\2\2\28\66\3\2\2\289\3\2"+
		"\2\29<\3\2\2\2:8\3\2\2\2;\63\3\2\2\2;\64\3\2\2\2<\t\3\2\2\2=>\5\f\7\2"+
		">?\5\20\t\2?@\5\16\b\2@\13\3\2\2\2AB\7\5\2\2B\r\3\2\2\2CD\7\6\2\2D\17"+
		"\3\2\2\2EF\5\22\n\2FG\5\24\13\2GH\5\26\f\2HI\5\30\r\2I\21\3\2\2\2JK\7"+
		"\7\2\2KL\7\37\2\2LM\7\b\2\2M\23\3\2\2\2NO\7\t\2\2OP\7\37\2\2PQ\7\n\2\2"+
		"Q\25\3\2\2\2RS\7\13\2\2ST\7\37\2\2TU\7\f\2\2U\27\3\2\2\2VX\7\21\2\2WY"+
		"\5\32\16\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z\\\7\r\2\2[]\5\34\17\2\\[\3\2"+
		"\2\2\\]\3\2\2\2]_\3\2\2\2^`\5$\23\2_^\3\2\2\2_`\3\2\2\2`a\3\2\2\2a\u0087"+
		"\7\16\2\2bd\7\22\2\2ce\5\32\16\2dc\3\2\2\2de\3\2\2\2ef\3\2\2\2fh\7\r\2"+
		"\2gi\5\36\20\2hg\3\2\2\2hi\3\2\2\2ik\3\2\2\2jl\5$\23\2kj\3\2\2\2kl\3\2"+
		"\2\2lm\3\2\2\2m\u0087\7\16\2\2np\7\23\2\2oq\5\32\16\2po\3\2\2\2pq\3\2"+
		"\2\2qr\3\2\2\2rt\7\r\2\2su\5 \21\2ts\3\2\2\2tu\3\2\2\2uw\3\2\2\2vx\5$"+
		"\23\2wv\3\2\2\2wx\3\2\2\2xy\3\2\2\2y\u0087\7\16\2\2z|\7\24\2\2{}\5\32"+
		"\16\2|{\3\2\2\2|}\3\2\2\2}~\3\2\2\2~\u0080\7\r\2\2\177\u0081\5\"\22\2"+
		"\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0084"+
		"\5$\23\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0087\7\16\2\2\u0086V\3\2\2\2\u0086b\3\2\2\2\u0086n\3\2\2\2\u0086z\3"+
		"\2\2\2\u0087\31\3\2\2\2\u0088\u0089\t\2\2\2\u0089\33\3\2\2\2\u008a\u008b"+
		"\7\37\2\2\u008b\35\3\2\2\2\u008c\u008d\7\34\2\2\u008d\37\3\2\2\2\u008e"+
		"\u008f\7\30\2\2\u008f!\3\2\2\2\u0090\u0091\7\27\2\2\u0091#\3\2\2\2\u0092"+
		"\u0093\13\2\2\2\u0093%\3\2\2\2\u0094\u0095\7\26\2\2\u0095\'\3\2\2\2\21"+
		"8;X\\_dhkptw|\u0080\u0083\u0086";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}