package eapli.base.validatorservice.script;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class Comando {
    /*
    Criada uma nova Parsetree por cada bloco e comando
     */

    private Ambito parentAmbito;
    private List<TerminalNode> parametros;
    private ParseTree bloco;

    Comando(Ambito parentAmbito, List<TerminalNode> parametros, ParseTree bloco) {
        this.parentAmbito = parentAmbito;
        this.parametros = parametros;
        this.bloco = bloco;
    }


    @Override
    public String toString() {
        return "Comando{" +
                "parentAmbito=" + parentAmbito +
                ", params=" + parametros +
                ", bloco=" + bloco +
                '}';
    }
}
