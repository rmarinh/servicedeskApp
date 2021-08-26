// Generated from /Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.grammars/src/main/java/gramaticaformulario/grammar/GramaticaFormulario.g4 by ANTLR 4.9.1
package gramaticaformulario.antl4Gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link GramaticaFormularioParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface GramaticaFormularioVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(GramaticaFormularioParser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printAtributo}
	 * labeled alternative in {@link GramaticaFormularioParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintAtributo(GramaticaFormularioParser.PrintAtributoContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#formularioID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormularioID(GramaticaFormularioParser.FormularioIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#atributos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributos(GramaticaFormularioParser.AtributosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atributoSingular}
	 * labeled alternative in {@link GramaticaFormularioParser#atributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtributoSingular(GramaticaFormularioParser.AtributoSingularContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#abrirAtributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbrirAtributo(GramaticaFormularioParser.AbrirAtributoContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#fecharAtributo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFecharAtributo(GramaticaFormularioParser.FecharAtributoContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#campos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCampos(GramaticaFormularioParser.CamposContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#nome}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNome(GramaticaFormularioParser.NomeContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#titulo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitulo(GramaticaFormularioParser.TituloContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#descricao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDescricao(GramaticaFormularioParser.DescricaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#validadores}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValidadores(GramaticaFormularioParser.ValidadoresContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#validador}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValidador(GramaticaFormularioParser.ValidadorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userInputString}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserInputString(GramaticaFormularioParser.UserInputStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userInputBoolean}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoBooleano}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserInputBoolean(GramaticaFormularioParser.UserInputBooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userInputNumerico}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoNumerico}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserInputNumerico(GramaticaFormularioParser.UserInputNumericoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code userInputData}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoData}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUserInputData(GramaticaFormularioParser.UserInputDataContext ctx);
	/**
	 * Visit a parse tree produced by {@link GramaticaFormularioParser#error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitError(GramaticaFormularioParser.ErrorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code scriptValidacao}
	 * labeled alternative in {@link GramaticaFormularioParser#epilogo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScriptValidacao(GramaticaFormularioParser.ScriptValidacaoContext ctx);
}