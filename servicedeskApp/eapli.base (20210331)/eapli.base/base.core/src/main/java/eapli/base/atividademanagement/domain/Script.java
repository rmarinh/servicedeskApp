package eapli.base.atividademanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Script implements ValueObject, Comparable<Script> {
    private static final long serialVersionUID = 1L;

    @Lob
    private String script;

    public Script(final String script) {
        if (StringPredicates.isNullOrEmpty(script)) {
            throw new IllegalArgumentException(
                    "Identifier script for Atividade should neither be null nor empty");
        }
        this.script = script;
    }

    protected Script() {
        // for ORM
    }

    public static Script valueOf(final String script) {
        return new Script(script);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Script)) {
            return false;
        }

        final Script that = (Script) o;
        return this.script.equals(that.script);
    }
    public String getScript(){
        return this.script;
    }

    @Override
    public int hashCode() {
        return this.script.hashCode();
    }

    @Override
    public String toString() {
        return this.script;
    }

    @Override
    public int compareTo(final Script arg0) {
        return script.compareTo(arg0.script);
    }
}
