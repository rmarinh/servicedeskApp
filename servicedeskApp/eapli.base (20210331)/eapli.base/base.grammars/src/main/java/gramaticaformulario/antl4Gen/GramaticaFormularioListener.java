// Generated from /Users/ruimarinho/OneDrive - Instituto Superior de Engenharia do Porto/ISEP_2020_2021/LAPR4/REPO/lei20_21_s4_2na_2/eapli.base (20210331)/eapli.base/base.grammars/src/main/java/gramaticaformulario/grammar/GramaticaFormulario.g4 by ANTLR 4.9.1
package gramaticaformulario.antl4Gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link GramaticaFormularioParser}.
 */
public interface GramaticaFormularioListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(GramaticaFormularioParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(GramaticaFormularioParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printAtributo}
	 * labeled alternative in {@link GramaticaFormularioParser#init}.
	 * @param ctx the parse tree
	 */
	void enterPrintAtributo(GramaticaFormularioParser.PrintAtributoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printAtributo}
	 * labeled alternative in {@link GramaticaFormularioParser#init}.
	 * @param ctx the parse tree
	 */
	void exitPrintAtributo(GramaticaFormularioParser.PrintAtributoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#formularioID}.
	 * @param ctx the parse tree
	 */
	void enterFormularioID(GramaticaFormularioParser.FormularioIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#formularioID}.
	 * @param ctx the parse tree
	 */
	void exitFormularioID(GramaticaFormularioParser.FormularioIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#atributos}.
	 * @param ctx the parse tree
	 */
	void enterAtributos(GramaticaFormularioParser.AtributosContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#atributos}.
	 * @param ctx the parse tree
	 */
	void exitAtributos(GramaticaFormularioParser.AtributosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atributoSingular}
	 * labeled alternative in {@link GramaticaFormularioParser#atributo}.
	 * @param ctx the parse tree
	 */
	void enterAtributoSingular(GramaticaFormularioParser.AtributoSingularContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atributoSingular}
	 * labeled alternative in {@link GramaticaFormularioParser#atributo}.
	 * @param ctx the parse tree
	 */
	void exitAtributoSingular(GramaticaFormularioParser.AtributoSingularContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#abrirAtributo}.
	 * @param ctx the parse tree
	 */
	void enterAbrirAtributo(GramaticaFormularioParser.AbrirAtributoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#abrirAtributo}.
	 * @param ctx the parse tree
	 */
	void exitAbrirAtributo(GramaticaFormularioParser.AbrirAtributoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#fecharAtributo}.
	 * @param ctx the parse tree
	 */
	void enterFecharAtributo(GramaticaFormularioParser.FecharAtributoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#fecharAtributo}.
	 * @param ctx the parse tree
	 */
	void exitFecharAtributo(GramaticaFormularioParser.FecharAtributoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#campos}.
	 * @param ctx the parse tree
	 */
	void enterCampos(GramaticaFormularioParser.CamposContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#campos}.
	 * @param ctx the parse tree
	 */
	void exitCampos(GramaticaFormularioParser.CamposContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#nome}.
	 * @param ctx the parse tree
	 */
	void enterNome(GramaticaFormularioParser.NomeContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#nome}.
	 * @param ctx the parse tree
	 */
	void exitNome(GramaticaFormularioParser.NomeContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#titulo}.
	 * @param ctx the parse tree
	 */
	void enterTitulo(GramaticaFormularioParser.TituloContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#titulo}.
	 * @param ctx the parse tree
	 */
	void exitTitulo(GramaticaFormularioParser.TituloContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#descricao}.
	 * @param ctx the parse tree
	 */
	void enterDescricao(GramaticaFormularioParser.DescricaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#descricao}.
	 * @param ctx the parse tree
	 */
	void exitDescricao(GramaticaFormularioParser.DescricaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#validadores}.
	 * @param ctx the parse tree
	 */
	void enterValidadores(GramaticaFormularioParser.ValidadoresContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#validadores}.
	 * @param ctx the parse tree
	 */
	void exitValidadores(GramaticaFormularioParser.ValidadoresContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#validador}.
	 * @param ctx the parse tree
	 */
	void enterValidador(GramaticaFormularioParser.ValidadorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#validador}.
	 * @param ctx the parse tree
	 */
	void exitValidador(GramaticaFormularioParser.ValidadorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userInputString}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoString}.
	 * @param ctx the parse tree
	 */
	void enterUserInputString(GramaticaFormularioParser.UserInputStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userInputString}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoString}.
	 * @param ctx the parse tree
	 */
	void exitUserInputString(GramaticaFormularioParser.UserInputStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userInputBoolean}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoBooleano}.
	 * @param ctx the parse tree
	 */
	void enterUserInputBoolean(GramaticaFormularioParser.UserInputBooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userInputBoolean}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoBooleano}.
	 * @param ctx the parse tree
	 */
	void exitUserInputBoolean(GramaticaFormularioParser.UserInputBooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userInputNumerico}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoNumerico}.
	 * @param ctx the parse tree
	 */
	void enterUserInputNumerico(GramaticaFormularioParser.UserInputNumericoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userInputNumerico}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoNumerico}.
	 * @param ctx the parse tree
	 */
	void exitUserInputNumerico(GramaticaFormularioParser.UserInputNumericoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code userInputData}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoData}.
	 * @param ctx the parse tree
	 */
	void enterUserInputData(GramaticaFormularioParser.UserInputDataContext ctx);
	/**
	 * Exit a parse tree produced by the {@code userInputData}
	 * labeled alternative in {@link GramaticaFormularioParser#preenchimentoData}.
	 * @param ctx the parse tree
	 */
	void exitUserInputData(GramaticaFormularioParser.UserInputDataContext ctx);
	/**
	 * Enter a parse tree produced by {@link GramaticaFormularioParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(GramaticaFormularioParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link GramaticaFormularioParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(GramaticaFormularioParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code scriptValidacao}
	 * labeled alternative in {@link GramaticaFormularioParser#epilogo}.
	 * @param ctx the parse tree
	 */
	void enterScriptValidacao(GramaticaFormularioParser.ScriptValidacaoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code scriptValidacao}
	 * labeled alternative in {@link GramaticaFormularioParser#epilogo}.
	 * @param ctx the parse tree
	 */
	void exitScriptValidacao(GramaticaFormularioParser.ScriptValidacaoContext ctx);
}