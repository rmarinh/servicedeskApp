package eapli.base.formulario;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ScriptValidacao implements ValueObject, Comparable<ScriptValidacao>, Serializable {
    private static final long serialVersionUID = 1L;

    private String scriptValidacao;

    public ScriptValidacao(final String scriptValidacao) {
        //Needed for xml Conversion
        this.scriptValidacao = scriptValidacao==null? "": scriptValidacao.replaceAll("'","&apos")
                .replaceAll("'","&apos;")
                .replaceAll("\"","&quot;" )
                .replaceAll("&","&amp;")
                .replaceAll(">","&gt;")
                .replaceAll("<", "&lt;");

    }

    protected ScriptValidacao() {
        // for ORM
    }

    public static ScriptValidacao valueOf(final String scriptValidacao) {
        return new ScriptValidacao(scriptValidacao);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ScriptValidacao)) {
            return false;
        }

        final ScriptValidacao that = (ScriptValidacao) o;
        return this.scriptValidacao.equals(that.scriptValidacao);
    }

    @Override
    public int hashCode() {
        return this.scriptValidacao.hashCode();
    }

    @Override
    public String toString() {
        return this.scriptValidacao;
    }

    @Override
    public int compareTo(final ScriptValidacao arg0) {
        return scriptValidacao.compareTo(arg0.scriptValidacao);
    }

    public String scriptValidacao(){
        return new String(this.scriptValidacao);
    }
}
