package eapli.base.validatorservice.formulario;

import eapli.base.formulario.Formulario;
import eapli.base.formulario.FormularioBuilder;
import gramaticaformulario.antl4Gen.GramaticaFormularioBaseListener;
import gramaticaformulario.antl4Gen.GramaticaFormularioParser;

import java.util.ArrayList;

public class FormularioListener  extends GramaticaFormularioBaseListener {
    private FormularioBuilder formBuilder; // inicia um formBuilder para ir construindo à medida que avança o wlaker
    private String nomeAtributo;
    private String tituloAtributo;
    private String descricaoAtributo;
    private String tipoDadosAtributo;
    private String validadorAtributo;
    private String preenchimentoAtributo;
    private ArrayList<Exception>  exceptions;
    private int atributoCounter = 1 ;
    private StringBuilder sb = new StringBuilder();
    private String script = "";

    @Override
    public void enterProg(GramaticaFormularioParser.ProgContext ctx) {
        //Entry
       super.enterProg(ctx);
    }

    @Override
    public void enterPrintAtributo(GramaticaFormularioParser.PrintAtributoContext ctx) {
        //ROOT
         this.formBuilder = new FormularioBuilder();
         this.exceptions = new ArrayList<>();

    }

    @Override
    public void enterFormularioID(GramaticaFormularioParser.FormularioIDContext ctx) {
        //Nome formulario
        //this.formBuilder.withNome();
        //System.out.println("Nome Formulario");
        //System.out.println(ctx.TEXTO().getText());
        formBuilder.withNome(ctx.TEXTO().getText());


    }

    @Override
    public void enterAtributos(GramaticaFormularioParser.AtributosContext ctx)
    {
        //System.out.println("Atributos");
        //System.out.println(ctx.getText());

    }
    @Override public void enterError(GramaticaFormularioParser.ErrorContext ctx) {

        Exception e = new Exception("Preenchimento errado:"+ ctx.getText() );
        exceptions.add(e);
        //System.out.println("Erro no Atrbituot");
        //System.out.println(ctx.getText());
    }

    @Override public void enterCampos(GramaticaFormularioParser.CamposContext ctx) {
        //System.out.println("Campos Atributo");
        //System.out.println(ctx.nome().getText());

        //System.out.println(ctx.titulo().getText());
        //System.out.println(ctx.descricao().getText());
        //System.out.println(ctx.validadores().getText());

        //Tipo de dados
        //Regex
        //Preenchimento
        //formBuilder.withNome(ctx.TEXTO().getText());
    }
    @Override public void enterNome(GramaticaFormularioParser.NomeContext ctx) {
        this.nomeAtributo = ctx.TEXTO().getText();
    }
    @Override public void enterTitulo(GramaticaFormularioParser.TituloContext ctx) {
        this.tituloAtributo = ctx.TEXTO().getText();
    }
    @Override public void enterDescricao(GramaticaFormularioParser.DescricaoContext ctx) {
        this.descricaoAtributo = ctx.TEXTO().getText();
    }
    //Enter in Validacao ->
    @Override public void enterValidadores(GramaticaFormularioParser.ValidadoresContext ctx) {
        //System.out.println("Tipo de dados");
        //String
        //Numerico
        //Data
        if (ctx.children.contains(ctx.TIPONUMERICO())){
            //System.out.println("Tipo TIPONUMERICO");
            this.tipoDadosAtributo = "Numerico";
        }
            //this.tipoDadosAtributo =

        if (ctx.children.contains(ctx.TIPOSTRING())){
            //System.out.println("Tipo TIPOSTRING");
            this.tipoDadosAtributo = "String";
            //System.out.println(ctx.TIPOSTRING().getText());
        }


        if (ctx.children.contains(ctx.TIPOBOOLEANO())){
            //System.out.println("Tipo TIPOBOOLEANO");
            this.tipoDadosAtributo = "Booleano";
            //System.out.println(ctx.TIPOBOOLEANO().getText());
        }


        if (ctx.children.contains(ctx.TIPODATA())){
            //System.out.println("Tipo TIPODATA");
            this.tipoDadosAtributo = "Data";
            //System.out.println(ctx.TIPODATA().getText());
        }


    }

    @Override public void enterValidador(GramaticaFormularioParser.ValidadorContext ctx) {
        //System.out.println("Regex Validador");
        //System.out.println(ctx.getText());
        this.validadorAtributo = ctx.getText();
    }

    @Override public void enterUserInputString(GramaticaFormularioParser.UserInputStringContext ctx) {
        //System.out.println("UserInputString");
        //System.out.println(ctx.getText());
        this.preenchimentoAtributo = ctx.getText();
    }
    @Override public void enterUserInputBoolean(GramaticaFormularioParser.UserInputBooleanContext ctx) {
        //System.out.println("UserInputBoolean");
        //System.out.println(ctx.getText());
        this.preenchimentoAtributo = ctx.getText();
    }
    @Override public void enterUserInputNumerico(GramaticaFormularioParser.UserInputNumericoContext ctx) {
        //System.out.println("UserInputNumerico");
        //System.out.println(ctx.getText());
        this.preenchimentoAtributo = ctx.getText();
    }
    @Override public void enterUserInputData(GramaticaFormularioParser.UserInputDataContext ctx) {
        //System.out.println("UserInputData");
        //System.out.println(ctx.getText());
        this.preenchimentoAtributo = ctx.getText();
    }
    @Override public void enterFecharAtributo(GramaticaFormularioParser.FecharAtributoContext ctx) {
        sb.append("campo" + atributoCounter + "_NOME = \"" + nomeAtributo + "\";" );
        sb.append("campo" + atributoCounter + "_TIPODADOS = \"" + tipoDadosAtributo + "\";" );
        sb.append("campo" + atributoCounter + "_VALIDADOR = \"" + validadorAtributo + "\";" );
        sb.append("campo" + atributoCounter + "_PREENCHIMENTO = \"" + preenchimentoAtributo + "\";" );
        formBuilder.withAtributo(nomeAtributo, tituloAtributo, descricaoAtributo, tipoDadosAtributo, validadorAtributo, preenchimentoAtributo);
        atributoCounter++;
    }
    @Override public void exitProg(GramaticaFormularioParser.ProgContext ctx) {
        //System.out.println("FIMFIMFIMFIMFIMFIFMIFM");
        /*
        if(!exceptions.isEmpty()){
            for (Exception e :exceptions){
                System.out.println(e.getMessage());
            }
        }else{
            String formFinal = Formulario.formularioToXML( formBuilder.build());
            System.out.println(formFinal);
        }
        */

    }

    @Override public void enterScriptValidacao(GramaticaFormularioParser.ScriptValidacaoContext ctx) {
        System.out.println("Enter" );
        System.out.println(ctx.getChildCount());
    }
    @Override public void exitScriptValidacao(GramaticaFormularioParser.ScriptValidacaoContext ctx) {
        System.out.println("Exit" );
        String before = "</atributos><scriptvalidacao>";
        String after = "</scriptvalidacao></formulario>";
        String other = "</atributos><scriptvalidacao/></formulario>";
        String scriptNotParsed = ctx.getText().replaceAll(before, "").replaceAll(after, "").replaceAll(other, "");
        String scriptParsed = scriptNotParsed.replaceAll("&apos", "'")
        .replaceAll("&apos;", "'")
        .replaceAll("&quot;", "\"")
        .replaceAll("&amp;", "&")
        .replaceAll("&gt;", ">")
        .replaceAll("&lt;", "<");
        /*
            ' is replaced with &apos;
            " is replaced with &quot;
            & is replaced with &amp;
            < is replaced with &lt;
            > is replaced with &gt;
         */
        sb.append("\n" + scriptParsed);
        this.script = sb.toString();
    }


    public boolean hasErrors(){
        if(exceptions.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    public void printErrors(){
        if(!exceptions.isEmpty()){
            for (Exception e :exceptions){
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public String getFormularioParsed(){
        if(!hasErrors()) {
            String formFinal = Formulario.formularioToXML(formBuilder.build());
            return formFinal;
        }else
            return "";


    }
    public String getScript(){
        return this.script;

    }


}
