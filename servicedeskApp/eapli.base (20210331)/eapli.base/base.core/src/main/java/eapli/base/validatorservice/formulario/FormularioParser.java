package eapli.base.validatorservice.formulario;


import eapli.base.validatorservice.script.ParserScript;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import static org.antlr.v4.runtime.CharStreams.fromFileName;
import gramaticaformulario.antl4Gen.*;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class FormularioParser {
    private String form;
    private GramaticaFormularioParser parser;
    private FormularioListener listener;
    private ParseTreeWalker walker;
    private ParseTree tree;
    public FormularioParser(String form){
        this.form = form;
        CharStream charStream = new ANTLRInputStream(form);
        GramaticaFormularioLexer lexer = new GramaticaFormularioLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        GramaticaFormularioParser parser = new GramaticaFormularioParser(tokens);

        ANTLRErrorListener myerrorListener= new MyAntlrErrorListenner();

        parser.removeErrorListeners();
        parser.addErrorListener(myerrorListener);
         tree = parser.prog();
         listener = new FormularioListener();
         walker = new ParseTreeWalker();
    }
    public  void parse() {


        walker.walk(listener,tree);
       // return listener.getFormularioParsed();
    }
    public boolean parsedwithSuccess(){

         if(listener.hasErrors())
             return false;
         else
             return true;
    }
    public String getParsedForm(){
        return listener.getFormularioParsed();
    }
    public void printErrors(){
        listener.printErrors();
    }

    public void executeScript(){
        ParserScript.parse(listener.getScript());
    }
    /*
    public static void main(String[] args) {
        String  SVC1_PEDIDO = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><formulario><nome>FormularioPedido Pedido do Servico SRV04</nome><atributos><atributo><nome>data inicio</nome><titulo>data inicio</titulo><descricao>data inicio ferias</descricao><tipoDados>Data</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-12</preenchimento></atributo><atributo><nome>data fim</nome><titulo>data fim</titulo><descricao>data fim ferias</descricao><tipoDados>Data</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-13</preenchimento></atributo><atributo><nome>tipo ausencia</nome><titulo>tipo ausencia</titulo><descricao>tipo ausencia ferias</descricao><tipoDados>String</tipoDados><validador>Ferias|Justificada|Nao Justificada</validador><preenchimento>Ferias</preenchimento></atributo><atributo><nome>justificacao</nome><titulo>justificacao</titulo><descricao>justificacao ferias</descricao><tipoDados>String</tipoDados><validador>[a-zA-Z]*</validador><preenchimento>Preciso de ferias</preenchimento></atributo></atributos><scriptvalidacao/></formulario>";
        String  SVC1_PEDIDO_COM_ERROS = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><formulario><nome>FormularioPedido Pedido do Servico SRV04</nome><atributos><atributo><nome>data inicio</nome><titulo>data inicio</titulo><descricao>data inicio ferias</descricao><tipoDados>String</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-12</preenchimento></atributo><atributo><nome>data fim</nome><titulo>data fim</titulo><descricao>data fim ferias</descricao><tipoDados>Data</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-13</preenchimento></atributo><atributo><nome>tipo ausencia</nome><titulo>tipo ausencia</titulo><descricao>tipo ausencia ferias</descricao><tipoDados>String</tipoDados><validador>Ferias|Justificada|Nao Justificada</validador><preenchimento>Ferias</preenchimento></atributo><atributo><nome>justificacao</nome><titulo>justificacao</titulo><descricao>justificacao ferias</descricao><tipoDados>String</tipoDados><validador>[a-zA-Z]*</validador><preenchimento>Preciso de ferias</preenchimento></atributo></atributos><scriptvalidacao/></formulario>";
        String SVC1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><formulario><nome>FormularioPedido Pedido do Servico SRV04</nome><atributos><atributo><nome>Tipo Cliente</nome><titulo>Tipo Cliente</titulo><descricao>Definir tipo cliente</descricao><tipoDados>String</tipoDados><validador>[a-zA-Z0-9]*</validador><preenchimento>teste</preenchimento></atributo><atributo><nome>Codigo do Produto</nome><titulo>Codigo do Produto</titulo><descricao>Codigo do produto</descricao><tipoDados>String</tipoDados><validador>[a-zA-Z0-9]*</validador><preenchimento>teste</preenchimento></atributo><atributo><nome>Quantidade</nome><titulo>Quantiddade</titulo><descricao>Quantidade pretendida</descricao><tipoDados>String</tipoDados><validador>[a-zA-Z0-9]*</validador><preenchimento>teste</preenchimento></atributo></atributos><scriptvalidacao/></formulario>";
        //System.out.println(parse(SVC1));
        String formularioEscaped =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><formulario><nome>FormularioPedido Pedido do Servico SRV04</nome><atributos><atributo><nome>data inicio</nome><titulo>data inicio</titulo><descricao>data inicio ferias</descricao><tipoDados>Data</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-12</preenchimento></atributo><atributo><nome>data fim</nome><titulo>data fim</titulo><descricao>data fim ferias</descricao><tipoDados>Data</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-13</preenchimento></atributo><atributo><nome>tipo ausencia</nome><titulo>tipo ausencia</titulo><descricao>tipo ausencia ferias</descricao><tipoDados>String</tipoDados><validador>Ferias|Justificada|Nao Justificada</validador><preenchimento>Justificada</preenchimento></atributo><atributo><nome>justificacao</nome><titulo>justificacao</titulo><descricao>justificacao ferias</descricao><tipoDados>String</tipoDados><validador>[a-zA-Z ]*</validador><preenchimento>Preciso de ferias</preenchimento></atributo></atributos><scriptvalidacao>campo1_NOME = &quot;data inicio&quot;; campo1_TIPODADOS = &quot;Data&quot;; campo1_VALIDADOR = &quot;[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]&quot;; campo1_PREENCHIMENTO = &quot;2021-12-12&quot;; campo2_NOME = &quot;data fim&quot;; campo2_TIPODADOS = &quot;Data&quot;; campo2_VALIDADOR = &quot;[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]&quot;; campo2_PREENCHIMENTO = &quot;2021-12-13&quot;; campo3_NOME = &quot;tipo ausencia&quot;; campo3_TIPODADOS = &quot;String&quot;; campo3_VALIDADOR = &quot;Ferias|Justificada|Nao Justificada&quot;; campo3_PREENCHIMENTO = &quot;Justificada&quot;; campo4_NOME = &quot;justificacao&quot;; campo4_TIPODADOS = &quot;String&quot;; campo4_VALIDADOR = &quot;[a-zA-Z]*&quot;; campo4_PREENCHIMENTO = &quot;Preciso de ferias&quot;; println(&quot;Validação 1&quot;); assert(campo1_PREENCHIMENTO &amp;gt;= campo1_PREENCHIMENTO); println(&quot;Validação 2&quot;); match (campo3_PREENCHIMENTO $ campo3_VALIDADOR) match(campo3_PREENCHIMENTO $ campo3_VALIDADOR); println(&quot;Validação 3&quot;); if(campo3_PREENCHIMENTO == &quot;Justificada&quot;) do match(campo4_PREENCHIMENTO $ campo4_VALIDADOR); end</scriptvalidacao></formulario>";
        String formEscapedNoVars =  "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><formulario><nome>FormularioPedido Pedido do Servico SRV04</nome><atributos><atributo><nome>data inicio</nome><titulo>data inicio</titulo><descricao>data inicio ferias</descricao><tipoDados>Data</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-12</preenchimento></atributo><atributo><nome>data fim</nome><titulo>data fim</titulo><descricao>data fim ferias</descricao><tipoDados>Data</tipoDados><validador>[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]</validador><preenchimento>2021-12-13</preenchimento></atributo><atributo><nome>tipo ausencia</nome><titulo>tipo ausencia</titulo><descricao>tipo ausencia ferias</descricao><tipoDados>String</tipoDados><validador>Ferias|Justificada|Nao Justificada</validador><preenchimento>Justificada</preenchimento></atributo><atributo><nome>justificacao</nome><titulo>justificacao</titulo><descricao>justificacao ferias</descricao><tipoDados>String</tipoDados><validador>[a-zA-Z ]*</validador><preenchimento>Preciso de ferias</preenchimento></atributo></atributos><scriptvalidacao>println(&quot;Validação 1&quot;); assert(campo1_PREENCHIMENTO &amp;gt;= campo1_PREENCHIMENTO); println(&quot;Validação 2&quot;); match (campo3_PREENCHIMENTO $ campo3_VALIDADOR); match(campo3_PREENCHIMENTO $ campo3_VALIDADOR); println(&quot;Validação 3&quot;); if(campo3_PREENCHIMENTO == &quot;Justificada&quot;) do match(campo4_PREENCHIMENTO $ campo4_VALIDADOR); end</scriptvalidacao></formulario>";
        FormularioParser parser = new FormularioParser(formEscapedNoVars);
        parser.parse();
        ParserScript.parse(parser.getScript());
        System.out.println(parser.parsedwithSuccess());
        parser.printErrors();
        //System.out.println(parser.getParsedForm());
        //Formulario form =  Formulario.xmlToFormulario(formularioEscaped);
        //System.out.println(form.scriptValidacao().scriptValidacao());
        //System.out.println(SVC1_PEDIDO_COM_ERROS);

    }
*/

}
