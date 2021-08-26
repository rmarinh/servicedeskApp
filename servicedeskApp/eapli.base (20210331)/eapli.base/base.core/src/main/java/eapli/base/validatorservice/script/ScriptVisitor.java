package eapli.base.validatorservice.script;

import eapli.base.utils.emailsender.Email;
import eapli.base.utils.emailsender.EmailSender;
import eapli.base.utils.filesearch.FileSearchXML;
import org.antlr.v4.runtime.ParserRuleContext;
import script.antl4Gen.ScriptBaseVisitor;
import script.antl4Gen.ScriptLexer;
import script.antl4Gen.ScriptParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ScriptVisitor extends ScriptBaseVisitor<Valor> {
    private Ambito ambito;
    private Map<String, Comando> functions;
    
    ScriptVisitor(Ambito ambito, Map<String, Comando> functions) {
        this.ambito = ambito;
        this.functions = new HashMap<>(functions);
    }

    // expressao op=( '*' | '/' | '%' ) expressao         #multExpressao
    @Override
    public Valor visitMultExpressao(ScriptParser.MultExpressaoContext ctx) {
        switch (ctx.op.getType()) {
            case ScriptLexer.Multiply:
                return multiply(ctx);
            case ScriptLexer.Divide:
                return divisao(ctx);
            case ScriptLexer.Modulus:
                return modulo(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    // expressao op=( '+' | '-' ) expressao               #addExpressao
    @Override
    public Valor visitAddExpressao(ScriptParser.AddExpressaoContext ctx) {
        switch (ctx.op.getType()) {
            case ScriptLexer.Add:
                return soma(ctx);
            case ScriptLexer.Subtract:
                return subtracao(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    // expressao op=( '>=' | '<=' | '>' | '<' ) expressao #compExpressao
    @Override
    public Valor visitCompExpressao(ScriptParser.CompExpressaoContext ctx) {
        switch (ctx.op.getType()) {
            case ScriptLexer.LT:
                return lt(ctx);
            case ScriptLexer.LTEquals:
                return ltEq(ctx);
            case ScriptLexer.GT:
                return gt(ctx);
            case ScriptLexer.GTEquals:
                return gtEq(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    // expressao op=( '==' | '!=' ) expressao             #eqExpressao
    @Override
    public Valor visitEqExpressao(ScriptParser.EqExpressaoContext ctx) {
        switch (ctx.op.getType()) {
            case ScriptLexer.Equals:
                return eq(ctx);
            case ScriptLexer.NEquals:
                return nEq(ctx);
            default:
                throw new RuntimeException("unknown operator type: " + ctx.op.getType());
        }
    }

    //expressao op='$' expressao                     #matchExpressao
    @Override public Valor visitMatchExpressao(ScriptParser.MatchExpressaoContext ctx) {
        return match(ctx);

    }

    //Operacao Multiplicacao
    public Valor multiply(ScriptParser.MultExpressaoContext ctx) {
    	Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if(lhs == null || rhs == null) {
    		System.err.println("lhs "+ lhs+ " rhs "+rhs);
    	    throw new scriptException(ctx);
    	}
    	
    	// number * number
        if(lhs.isNumber() && rhs.isNumber()) {
            return new Valor(lhs.asDouble() * rhs.asDouble());
        }

        // string * number
        if(lhs.isString() && rhs.isNumber()) {
            StringBuilder str = new StringBuilder();
            int stop = rhs.asDouble().intValue();
            for(int i = 0; i < stop; i++) {
                str.append(lhs.asString());
            }
            return new Valor(str.toString());
        }

        // list * number
        if(lhs.isList() && rhs.isNumber()) {
            List<Valor> total = new ArrayList<>();
            int stop = rhs.asDouble().intValue();
            for(int i = 0; i < stop; i++) {
                total.addAll(lhs.asList());
            }
            return new Valor(total);
        }   
         	
    	throw new scriptException(ctx);
    }


    //Operacao Divisao
    private Valor divisao(ScriptParser.MultExpressaoContext ctx) {
    	Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if (lhs.isNumber() && rhs.isNumber()) {
    		return new Valor(lhs.asDouble() / rhs.asDouble());
    	}
    	throw new scriptException(ctx);
    }

    //operacao modulo
	private Valor modulo(ScriptParser.MultExpressaoContext ctx) {
		Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if (lhs.isNumber() && rhs.isNumber()) {
    		return new Valor(lhs.asDouble() % rhs.asDouble());
    	}
    	throw new scriptException(ctx);
	}

    private Valor soma(ScriptParser.AddExpressaoContext ctx) {
        Valor lhs = this.visit(ctx.expressao(0));
        Valor rhs = this.visit(ctx.expressao(1));
        
        if(lhs == null || rhs == null) {
            throw new scriptException(ctx);
        }
        
        // number + number
        if(lhs.isNumber() && rhs.isNumber()) {
            return new Valor(lhs.asDouble() + rhs.asDouble());
        }
        
        // list + any
        if(lhs.isList()) {
            List<Valor> list = lhs.asList();
            list.add(rhs);
            return new Valor(list);
        }

        // string + any
        if(lhs.isString()) {
            return new Valor(lhs.asString() + "" + rhs.toString());
        }

        // any + string
        if(rhs.isString()) {
            return new Valor(lhs.toString() + "" + rhs.asString());
        }
        
        return new Valor(lhs.toString() + rhs.toString());
    }

    private Valor subtracao(ScriptParser.AddExpressaoContext ctx) {
    	Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if (lhs.isNumber() && rhs.isNumber()) {
    		return new Valor(lhs.asDouble() - rhs.asDouble());
    	}
    	if (lhs.isList()) {
            List<Valor> list = lhs.asList();
            list.remove(rhs);
            return new Valor(list);
        }
    	throw new scriptException(ctx);
    }

    //Maior ou igual
    private Valor gtEq(ScriptParser.CompExpressaoContext ctx) {
    	Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if (lhs.isNumber() && rhs.isNumber()) {
    		return new Valor(lhs.asDouble() >= rhs.asDouble());
    	}
        if (lhs.isDate() && rhs.isDate()) {
            return new Valor(lhs.asDate().compareTo(rhs.asDate()) >=0 );
        }
    	if(lhs.isString() && rhs.isString()) {
            return new Valor(lhs.asString().compareTo(rhs.asString()) >= 0);
        }
    	throw new scriptException(ctx);
    }

    //Menor ou igual
    private Valor ltEq(ScriptParser.CompExpressaoContext ctx) {
    	Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if (lhs.isNumber() && rhs.isNumber()) {
    		return new Valor(lhs.asDouble() <= rhs.asDouble());
    	}
        if (lhs.isDate() && rhs.isDate()) {
            return new Valor(lhs.asDate().compareTo(rhs.asDate()) <=0 );
        }
    	if(lhs.isString() && rhs.isString()) {
            return new Valor(lhs.asString().compareTo(rhs.asString()) <= 0);
        }
    	throw new scriptException(ctx);
    }

    //Maior
    private Valor gt(ScriptParser.CompExpressaoContext ctx) {
    	Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if (lhs.isNumber() && rhs.isNumber()) {
    		return new Valor(lhs.asDouble() > rhs.asDouble());
    	}
        if (lhs.isDate() && rhs.isDate()) {
            return new Valor(lhs.asDate().compareTo(rhs.asDate()) >0 );
        }
    	if(lhs.isString() && rhs.isString()) {
            return new Valor(lhs.asString().compareTo(rhs.asString()) > 0);
        }
    	throw new scriptException(ctx);
    }

    //Menor
    private Valor lt(ScriptParser.CompExpressaoContext ctx) {
    	Valor lhs = this.visit(ctx.expressao(0));
    	Valor rhs = this.visit(ctx.expressao(1));
    	if (lhs.isNumber() && rhs.isNumber()) {
    		return new Valor(lhs.asDouble() < rhs.asDouble());
    	}
        if (lhs.isDate() && rhs.isDate()) {
            return new Valor(lhs.asDate().compareTo(rhs.asDate()) <0 );
        }
    	if(lhs.isString() && rhs.isString()) {
            return new Valor(lhs.asString().compareTo(rhs.asString()) < 0);
        }
    	throw new scriptException(ctx);
    }

    private Valor match(ScriptParser.MatchExpressaoContext ctx) {
        Valor lhs = this.visit(ctx.expressao(0)); //Preenchimento
        Valor rhs = this.visit(ctx.expressao(1)); //Validador
        return new Valor(lhs.toString().replaceAll("\"","").matches(rhs.toString().replaceAll("\"","")));

    }

//Igualdade
    private Valor eq(ScriptParser.EqExpressaoContext ctx) {
        Valor lhs = this.visit(ctx.expressao(0));
        Valor rhs = this.visit(ctx.expressao(1));
        if (lhs == null) {
        	throw new scriptException(ctx);
        }
        return new Valor(lhs.equals(rhs));
    }

    private Valor nEq(ScriptParser.EqExpressaoContext ctx) {
        Valor lhs = this.visit(ctx.expressao(0));
        Valor rhs = this.visit(ctx.expressao(1));
        return new Valor(!lhs.equals(rhs));
    }


    //Number                                               #numberExpressao
    @Override
    public Valor visitNumberExpressao(ScriptParser.NumberExpressaoContext ctx) {
        return new Valor(Double.valueOf(ctx.getText()));
    }

    //Bool                                                 #boolExpressao
    @Override
    public Valor visitBoolExpressao(ScriptParser.BoolExpressaoContext ctx) {
        return new Valor(Boolean.valueOf(ctx.getText()));
    }
    @Override public Valor visitDateExpressao(ScriptParser.DateExpressaoContext ctx) {

        Date data1 = null;
        String expectedPattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        try
        {
            data1 = formatter.parse(ctx.Date().getText().replaceAll("\"","" ));
            System.out.println(data1);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            //e.printStackTrace();
        }

        return new Valor(data1);
    }


    // Null                                                 #nullExpressao
    //Usada nos retornos null
    @Override
    public Valor visitNullExpressao(ScriptParser.NullExpressaoContext ctx) {
        return Valor.NULL;
    }

    private Valor resolveIndexes(Valor val, List<ScriptParser.ExpressaoContext> indexes) {
    	for (ScriptParser.ExpressaoContext ec: indexes) {
    		Valor idx = this.visit(ec);
    		if (!idx.isNumber() || (!val.isList() && !val.isString()) ) {
        		throw new scriptException("Problem resolving indexes on "+val+" at "+idx, ec);
    		}
    		int i = idx.asDouble().intValue();
    		if (val.isString()) {
    			val = new Valor(val.asString().substring(i, i+1));
    		} else {
    			val = val.asList().get(i);
    		}
    	}
    	return val;
    }
    
    private void setAtIndex(ParserRuleContext ctx, List<ScriptParser.ExpressaoContext> indexes, Valor val, Valor newVal) {
    	if (!val.isList()) {
    		throw new scriptException(ctx);
    	}
    	for (int i = 0; i < indexes.size() - 1; i++) {
    		Valor idx = this.visit(indexes.get(i));
    		if (!idx.isNumber()) {
        		throw new scriptException(ctx);
    		}
    		val = val.asList().get(idx.asDouble().intValue());
    	}
    	Valor idx = this.visit(indexes.get(indexes.size() - 1));
		if (!idx.isNumber()) {
    		throw new scriptException(ctx);
		}
    	val.asList().set(idx.asDouble().intValue(), newVal);
    }
    


    // Identificador indexes?                                  #identificadorExpressao
    @Override
    public Valor visitIdentificadorExpressao(ScriptParser.IdentificadorExpressaoContext ctx) {
        String id = ctx.Identificador().getText();
        Valor val = ambito.resolve(id);

        if (ctx.indexes() != null) {
        	List<ScriptParser.ExpressaoContext> exps = ctx.indexes().expressao();
        	val = resolveIndexes(val, exps);
        }
        return val;
    }

    // String                       #stringExpression
    @Override
    public Valor visitStringExpressao(ScriptParser.StringExpressaoContext ctx) {
        String text = ctx.getText();
        text = text.substring(1, text.length() - 1).replaceAll("\\\\(.)", "$1");
        Valor val = new Valor(text);


        if (ctx.indexes() != null) {
        	List<ScriptParser.ExpressaoContext> exps = ctx.indexes().expressao();
        	val = resolveIndexes(val, exps);
        }
        return val;
    }

    //  '(' expressao ')' indexes?                          #expressaoExpressao
    //Uma ou mais expressoes segiudas com variaveis
    @Override
    public Valor visitExpressaoExpressao(ScriptParser.ExpressaoExpressaoContext ctx) {
        Valor val = this.visit(ctx.expressao());
        if (ctx.indexes() != null) {
        	List<ScriptParser.ExpressaoContext> expressoes = ctx.indexes().expressao();
        	val = resolveIndexes(val, expressoes);
        }
        return val;
    }

    // declaracao
    //  Identificador  '=' expressao
    @Override
    public Valor visitDeclaracao(ScriptParser.DeclaracaoContext ctx) {
        Valor newVal = this.visit(ctx.expressao());

        String id = ctx.Identificador().getText();
        ambito.declarar(id, newVal);
        return Valor.VOID;
    }

    //Println '(' expressao? ')'  #printlnComando//Comando Println
    //Mostrar output na consola
    @Override
    public Valor visitPrintlnComando(ScriptParser.PrintlnComandoContext ctx) {
        if (ctx.expressao() != null) {
            System.out.println(this.visit(ctx.expressao()));
        } else {
            System.out.println();
        }
        return Valor.VOID;
    }

    //replace(varName,textToFind, textToReplace)
    @Override public Valor visitReplaceComando(ScriptParser.ReplaceComandoContext ctx) {
        if (ctx.expressao() != null) {
            String varName = ctx.expressao().get(0).getText();
            String textToFind = ctx.expressao().get(1).getText();
            String textToReplace = ctx.expressao().get(2).getText();

            this.ambito.replace(varName,textToFind, textToReplace);
        }
        return Valor.VOID;
    }

    // Assert '(' expressao ')'    #assertComando //Comando assert
    // comando assert com retorno True ou False
    @Override
    public Valor visitAssertComando(ScriptParser.AssertComandoContext ctx) {
    	Valor value = this.visit(ctx.expressao());

        if(!value.isBoolean()) {
            System.out.println(ctx.getText());
            throw new scriptException(ctx);

        }

        if(!value.asBoolean()) {
            System.out.println(ctx.getText());
            throw new AssertionError("Failed Assertion "+ctx.expressao().getText()+" line:"+ctx.start.getLine());
        }

        return Valor.VOID;
    }

    //  Match (expressao ) #matchComando // comando match regex
    //Comando especifico para o match de um regex
    @Override
    public Valor visitMatchComando(ScriptParser.MatchComandoContext ctx) {

        Valor value = this.visit(ctx.expressao());

        if(!value.isBoolean()) {
            System.out.println(ctx.getText());
            throw new scriptException(ctx);

        }

        if(!value.asBoolean()) {
            System.out.println(ctx.getText());
            throw new AssertionError("Failed Assertion "+ctx.expressao().getText()+" line:"+ctx.start.getLine());
        }

        return Valor.VOID;
    }


    //Bloco condicional (If else)
    @Override
    public Valor visitIfBloco(ScriptParser.IfBlocoContext ctx) {

        //Visita ifCondicional
        if(this.visit(ctx.ifCondicional().expressao()).asBoolean()) {
            return this.visit(ctx.ifCondicional().bloco());
        }

        // visita  elseCondicional
        if(ctx.elseCondicional() != null) {
            return this.visit(ctx.elseCondicional().bloco());
        }

        return Valor.VOID;
    }

    //bloco //Um bloco  Tem 0 ou mais linhas
     //: (linha)*
      //  ;
    @Override
    public Valor visitBloco(ScriptParser.BlocoContext ctx) {
    		
    	ambito = new Ambito(ambito, false); // Cria novo ambito por cada bloco
        for (ScriptParser.LinhaContext sx: ctx.linha()) { // Entra nos filhos linhas
            this.visit(sx);
        }
        ambito = ambito.parent();
        return Valor.VOID;
    }

    @Override
    public Valor visitSendEmailComando(ScriptParser.SendEmailComandoContext ctx) {
        if (ctx.expressao() != null) {
            Email email = new Email(ctx.expressao().get(0).getText(), ctx.expressao().get(1).getText(), ctx.expressao().get(2).getText());
            System.out.println(email.toString());
            //EmailSender emailSender = new EmailSender();
           ///emailSender.sendEmail(email);
        } else {
            System.out.println();
        }
        return Valor.VOID;
    }

////file,String rootTag ,String tagToSearch, String tagToSearchValue, List<String> listTagsToBeReturned
// | Find '(' expressao',' expressao',' expressao',' expressao',' (expressao',')* expressao ')' #findComando //comando find in xml
    @Override
    public Valor visitFindComando(ScriptParser.FindComandoContext ctx) {


        if (ctx.expressao() != null) {
            Valor file = this.visit(ctx.expressao().get(0));
            Valor rootTag = this.visit(ctx.expressao().get(1));
            Valor tagToSearch = this.visit(ctx.expressao().get(2));
            Valor tagToSearchValue = this.visit(ctx.expressao().get(3));

           ArrayList<String> listTagsToBeReturned = new ArrayList<>();
            for (int i = 4; i < ctx.expressao().size(); i++) {
                Valor tempVal = this.visit(ctx.expressao().get(i));
                listTagsToBeReturned.add(tempVal.asString().replaceAll("\"",""));
            }
            FileSearchXML.findInXML(file.asString().replaceAll("\"",""), rootTag.asString().replaceAll("\"",""), tagToSearch.asString().replaceAll("\"",""), tagToSearchValue.asString().replaceAll("\"",""), listTagsToBeReturned);

        } else {
            System.out.println();
        }
        return Valor.VOID;

    }




}
