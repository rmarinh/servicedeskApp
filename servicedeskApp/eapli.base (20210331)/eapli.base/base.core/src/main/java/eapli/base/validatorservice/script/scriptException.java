package eapli.base.validatorservice.script;

import org.antlr.v4.runtime.ParserRuleContext;

/*
Exceçoes lançadas quando não respeita nenhuma das regras de interpretaçao
 */
public class scriptException extends RuntimeException {
    public scriptException(ParserRuleContext ctx) {
        this("Illegal expression: " + ctx.getText(), ctx);
    }
    
    public scriptException(String msg, ParserRuleContext ctx) {
        super(msg + " line:" + ctx.start.getLine());
    }

    @Override
    public String toString() {
        return "scriptException{}";
    }
}
