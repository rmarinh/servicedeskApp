package eapli.base.formulario;


import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FormularioBuilder {
    private String nome;
    private Set<Atributo> atributos = new LinkedHashSet<>() ;
    private ScriptValidacao scriptValidacao;
    private Formulario formulario;


    public FormularioBuilder withNome (String nome) {
        this.nome = nome;
        return this;
    }
    public FormularioBuilder withAtributo(String nome, String titulo, String descricao, String tipoDados, String validador, String preenchimento ) {
        this.atributos.add( new Atributo(nome, titulo, descricao, tipoDados,validador, preenchimento));
        return this;
    }
    public FormularioBuilder withAtributos(Set<Atributo> atributos) {
        this.atributos.addAll(atributos);
        return this;
    }

    public FormularioBuilder withScriptValidacao (String scriptValidacao) {
        this.scriptValidacao = new ScriptValidacao(scriptValidacao);
        return this;
    }

    public Formulario build() {
        Formulario formulario =  new Formulario(this.nome, this.atributos, this.scriptValidacao);
        return formulario;
    }

}
