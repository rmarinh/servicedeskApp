// Generated from /Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.grammars/src/main/java/scriptformulario/grammar/Script.g4 by ANTLR 4.9.1
package script.antl4Gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ScriptParser}.
 */
public interface ScriptListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ScriptParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(ScriptParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(ScriptParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#bloco}.
	 * @param ctx the parse tree
	 */
	void enterBloco(ScriptParser.BlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#bloco}.
	 * @param ctx the parse tree
	 */
	void exitBloco(ScriptParser.BlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#linha}.
	 * @param ctx the parse tree
	 */
	void enterLinha(ScriptParser.LinhaContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#linha}.
	 * @param ctx the parse tree
	 */
	void exitLinha(ScriptParser.LinhaContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void enterDeclaracao(ScriptParser.DeclaracaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#declaracao}.
	 * @param ctx the parse tree
	 */
	void exitDeclaracao(ScriptParser.DeclaracaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printlnComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterPrintlnComando(ScriptParser.PrintlnComandoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printlnComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitPrintlnComando(ScriptParser.PrintlnComandoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterPrintComando(ScriptParser.PrintComandoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitPrintComando(ScriptParser.PrintComandoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assertComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterAssertComando(ScriptParser.AssertComandoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assertComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitAssertComando(ScriptParser.AssertComandoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sendEmailComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterSendEmailComando(ScriptParser.SendEmailComandoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sendEmailComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitSendEmailComando(ScriptParser.SendEmailComandoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code findComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterFindComando(ScriptParser.FindComandoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code findComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitFindComando(ScriptParser.FindComandoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code replaceComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterReplaceComando(ScriptParser.ReplaceComandoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code replaceComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitReplaceComando(ScriptParser.ReplaceComandoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code matchComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterMatchComando(ScriptParser.MatchComandoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code matchComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitMatchComando(ScriptParser.MatchComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#ifBloco}.
	 * @param ctx the parse tree
	 */
	void enterIfBloco(ScriptParser.IfBlocoContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#ifBloco}.
	 * @param ctx the parse tree
	 */
	void exitIfBloco(ScriptParser.IfBlocoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#ifCondicional}.
	 * @param ctx the parse tree
	 */
	void enterIfCondicional(ScriptParser.IfCondicionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#ifCondicional}.
	 * @param ctx the parse tree
	 */
	void exitIfCondicional(ScriptParser.IfCondicionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#elseCondicional}.
	 * @param ctx the parse tree
	 */
	void enterElseCondicional(ScriptParser.ElseCondicionalContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#elseCondicional}.
	 * @param ctx the parse tree
	 */
	void exitElseCondicional(ScriptParser.ElseCondicionalContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#idList}.
	 * @param ctx the parse tree
	 */
	void enterIdList(ScriptParser.IdListContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#idList}.
	 * @param ctx the parse tree
	 */
	void exitIdList(ScriptParser.IdListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterAddExpressao(ScriptParser.AddExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitAddExpressao(ScriptParser.AddExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expressaoExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterExpressaoExpressao(ScriptParser.ExpressaoExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expressaoExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitExpressaoExpressao(ScriptParser.ExpressaoExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterNullExpressao(ScriptParser.NullExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitNullExpressao(ScriptParser.NullExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code comandoExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterComandoExpressao(ScriptParser.ComandoExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code comandoExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitComandoExpressao(ScriptParser.ComandoExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterMultExpressao(ScriptParser.MultExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitMultExpressao(ScriptParser.MultExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpressao(ScriptParser.BoolExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpressao(ScriptParser.BoolExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code dateExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterDateExpressao(ScriptParser.DateExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code dateExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitDateExpressao(ScriptParser.DateExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code numberExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpressao(ScriptParser.NumberExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code numberExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpressao(ScriptParser.NumberExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterStringExpressao(ScriptParser.StringExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitStringExpressao(ScriptParser.StringExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code identificadorExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterIdentificadorExpressao(ScriptParser.IdentificadorExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code identificadorExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitIdentificadorExpressao(ScriptParser.IdentificadorExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code compExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterCompExpressao(ScriptParser.CompExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code compExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitCompExpressao(ScriptParser.CompExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code matchExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterMatchExpressao(ScriptParser.MatchExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code matchExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitMatchExpressao(ScriptParser.MatchExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eqExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void enterEqExpressao(ScriptParser.EqExpressaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code eqExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 */
	void exitEqExpressao(ScriptParser.EqExpressaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link ScriptParser#indexes}.
	 * @param ctx the parse tree
	 */
	void enterIndexes(ScriptParser.IndexesContext ctx);
	/**
	 * Exit a parse tree produced by {@link ScriptParser#indexes}.
	 * @param ctx the parse tree
	 */
	void exitIndexes(ScriptParser.IndexesContext ctx);
}