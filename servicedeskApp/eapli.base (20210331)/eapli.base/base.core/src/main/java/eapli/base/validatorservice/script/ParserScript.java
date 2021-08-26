package eapli.base.validatorservice.script;


import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import script.antl4Gen.ScriptLexer;
import script.antl4Gen.ScriptParser;

import java.util.Collections;
import java.util.Map;

public class ParserScript {

    public static void parse(String script) {
        CharStream charStream = new ANTLRInputStream(script);

        ///Check errors
        try {
            ScriptLexer lexer = new ScriptLexer(charStream);
            CommonTokenStream tokens = new CommonTokenStream(lexer); //scan stream for tokens
            ScriptParser parser = new ScriptParser(tokens);

            parser.setBuildParseTree(true);

            ParseTree tree = parser.parse();


            Ambito ambito = new Ambito();
            Map<String, Comando> comandos = Collections.emptyMap();
            ScriptVisitor visitor = new ScriptVisitor(ambito, comandos);
            visitor.visit(tree);
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            } else {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }
    static String script1 = "\n" +
            "//Serviço 1 \n" +
            "  //Formulario Pedido   \n" +
            "    //O período é definido por duas datas em que a data fim é igual ou superior à data inicio.\n" +
            "        campo1 =  \"data inicio\";\n" +
            "        campo2 = \"data fim\";\n" +
            "        assert(campo2 >= campo1);\n" +
            "    \n" +
            "    //Campo preenchiido com Expressao Regular\n" +
            "        campo = \"rua\";\n" +
            "        Regex = \"[A-Za-z ]*\";\n" +
            "        assert(campo match Regex);\n" +
            "    \n" +
            "    //O tipo de ausência assume apenas 3 valores possíveis: Férias, Justificada, Não Justificada.\n" +
            "        campo = \"tipo de ausência\";\n" +
            "        Regex = \"[Férias|Justificada|Não Justificada]\";\n" +
            "        assert(campo match Regex);\n" +
            "    \n" +
            "     //Caso o tipo de ausência seja \"Justificada\" é obrigatório preencher o campo \"justificação\".\n" +
            "         campo1 = \"tipo de ausência\";\n" +
            "         campo2 = \"justificação\";\n" +
            "         Regex = \"[A-Za-Z0-9 ]\";\n" +
            "         if (campo==Justificada) do\n" +
            "            assert(campo2 match  Regex);\n" +
            "         end\n" +
            "\n" +
            "\n" +
            "  //Formulário Aprovação:\n" +
            "        //Campo preenchiido com Expressao Regular A fundamentação é obrigatória\n" +
            "        campo = \"fundamentação\";\n" +
            "        Regex = \"[A-Za-z ]*\";\n" +
            "        assert(campo match Regex);\n" +
            "\n" +
            "   //Formulario Execucao\n" +
            "        campo1 = \"férias totais\";\n" +
            "        campo2 = \"dias já gozados\";\n" +
            "        campo3 = \"dias a gozar\";\n" +
            "        assert(campo1 == (campo2+campo3));";


    static String script2 = "        campo1 =  1;" +
            "        campo2 = 2;" +
            "        assert(campo2 >= campo1);" +
            "         campo1 = \"tipo de ausência\";\n" +
            "         campo2 = \"justificação\";\n" +
            "         Regex = \"[A-Za-Z0-9 ]\";\n" +
            "         if (campo==campo2) do\n" +
            "            assert(campo2, Regex);\n" +
            "         end\n" ;

    String script3 =  "    campo1 =  1;\n" +
            "     println(campo1);"+
            "     campo3 = \"Campoformulario\";\n" +
            "     println(campo3);"+
            "     campo2 = 1;\n" +
            "     sendemail(campo3);"+
            "     assert(campo1 >= campo2); \n" +
            "     campo1 = \"tipo de ausência\";\n" +
            "      campo2 = \"justificação\";\n" +
            "      Regex = \"[A-Za-Z0-9 ]\"; \n" +
            "      if (campo1==campo2) do\n" +
            //"      assert(campo2==Regex);\n" +
            "     println(campo1);"+
            "      end\n" +
            "     assert(campo2 != Regex);\n" +
            "     assert(campo2 != 1);" ;

    static String script_SVC1_FORMPEDIDO  ="\t\tcampo1_NOME = \"data inicio\";\n" +
            "\t\tcampo1_TIPODADOS = \"Data\";\n" +
            "\t\tcampo1_VALIDADOR = \"[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\";\n" +
            "\t\tcampo1_PREENCHIMENTO = \"2021-12-12\";\n" +
            //"\t\tcampo1_PREENCHIMENTO = 1;\n" +
            "\n" +
            "\n" +
            "\t\tcampo2_NOME = \"data fim\";\n" +
            "\t\tcampo2_TIPODADOS = \"Data\";\n" +
            "\t\tcampo2_VALIDADOR = \"[1-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\";\n" +
            "\t\tcampo2_PREENCHIMENTO = \"2021-12-13\";\n" +
            //"\t\tcampo2_PREENCHIMENTO = 1;\n" +
            "\n" +
            "\t\tcampo3_NOME = \"tipo ausencia\";\n" +
            "\t\tcampo3_TIPODADOS = \"String\";\n" +
            "\t\tcampo3_VALIDADOR = \"Ferias|Justificada|Nao Justificada\";\n" +
            "\t\tcampo3_PREENCHIMENTO = \"Justificada\";\n" +
            "\n" +
            "\t\tcampo4_NOME = \"justificacao\";\n" +
            "\t\tcampo4_TIPODADOS = \"String\";\n" +
            "\t\tcampo4_VALIDADOR = \"[a-zA-Z ]*\";\n" +
            "\t\tcampo4_PREENCHIMENTO = \"Preciso de ferias\";\n" +
            "\n" +
            "\n" +
            "\n" +
            "\t\t//Validação 1\n" +
            "        assert(campo2_PREENCHIMENTO >= campo1_PREENCHIMENTO);\n"+
            "        //Validação 2: \n" +
            //"        assert(campo3_PREENCHIMENTO match campo3_VALIDADOR);\n" +
            "        match(campo3_PREENCHIMENTO $ campo3_VALIDADOR);\n" +
            "\n" +
            "        //Validaçao 3:\n" +
            "\n" +
            "        if(campo3_PREENCHIMENTO == \"Justificada\") do\n" +
            //"\t\t\t assert(campo4_PREENCHIMENTO match campo4_VALIDADOR);\n" +
            "\t\t\t match(campo4_PREENCHIMENTO $ campo4_VALIDADOR);\n" +
            "        end";

    static String emailscript = "sendemail(\"ruimarinhopt@outlook.com\", \"Atividade send email\", \"Atividade EndEmail\");";
    //replace(varName,textToFind, textToReplace)
    static String scriptReplace = "var = \"substitution test of #1 and #1\"; " +
            "println(var);" +
            "replace(var, \"#1\", \"replaced\""+
            "println(var);"
            ;

    static String scriptFindExample = "\n" +
            "test = \"<products><product><id>XYJ123</id><name>Product One</name><description>This is the description for product one.</description><price>19.99</price></product><product><id>XYJ234</id><name>Product Two</name><description>This is the description for product two.</description><price>19.99</price></product><product><id>XYJ456</id><name>Product Three</name><description>This is the description for product three.</description><price>19.99</price></product><product><id>XYJ789</id><name>Product Four</name><description>This is the description for product four.</description><price>19.99</price></product><product><id>XYH123</id><name>Product Five</name><description>This is the description for product five.</description><price>19.99</price></product></products>\";\n" +
            "find(test,\"product\", \"id\", \"XYJ123\", \"name\", \"price\" );";
    //String scriptFindErro = "find(\"ficheiroXML\",\"product\", \"id\", \"XYJ123\" );";

   // public static void main(String[] args) {


        //    parse(script1);
   //     parse(script_SVC1_FORMPEDIDO);
    //    parse(emailscript);
        //parse(scriptReplace);
    //    parse(scriptFindExample);
/*
        Email email = new Email("ruimarinhopt@outlook.com", "teste1", "teteetetetetetet");
        EmailSender sender = new EmailSender();
        sender.sendEmail(email);

 */
    //}

}
