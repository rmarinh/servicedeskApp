// Generated from /Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.grammars/src/main/java/scriptformulario/grammar/Script.g4 by ANTLR 4.9.1
package script.antl4Gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ScriptParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ScriptVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ScriptParser#parse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParse(ScriptParser.ParseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#bloco}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBloco(ScriptParser.BlocoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#linha}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinha(ScriptParser.LinhaContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#declaracao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaracao(ScriptParser.DeclaracaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printlnComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintlnComando(ScriptParser.PrintlnComandoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintComando(ScriptParser.PrintComandoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assertComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssertComando(ScriptParser.AssertComandoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sendEmailComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSendEmailComando(ScriptParser.SendEmailComandoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code findComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFindComando(ScriptParser.FindComandoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code replaceComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReplaceComando(ScriptParser.ReplaceComandoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code matchComando}
	 * labeled alternative in {@link ScriptParser#comando}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchComando(ScriptParser.MatchComandoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#ifBloco}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBloco(ScriptParser.IfBlocoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#ifCondicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCondicional(ScriptParser.IfCondicionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#elseCondicional}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseCondicional(ScriptParser.ElseCondicionalContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#idList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdList(ScriptParser.IdListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpressao(ScriptParser.AddExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expressaoExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressaoExpressao(ScriptParser.ExpressaoExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullExpressao(ScriptParser.NullExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code comandoExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComandoExpressao(ScriptParser.ComandoExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpressao(ScriptParser.MultExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpressao(ScriptParser.BoolExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code dateExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDateExpressao(ScriptParser.DateExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code numberExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpressao(ScriptParser.NumberExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringExpressao(ScriptParser.StringExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identificadorExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentificadorExpressao(ScriptParser.IdentificadorExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code compExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpressao(ScriptParser.CompExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code matchExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchExpressao(ScriptParser.MatchExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqExpressao}
	 * labeled alternative in {@link ScriptParser#expressao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpressao(ScriptParser.EqExpressaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link ScriptParser#indexes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexes(ScriptParser.IndexesContext ctx);
}