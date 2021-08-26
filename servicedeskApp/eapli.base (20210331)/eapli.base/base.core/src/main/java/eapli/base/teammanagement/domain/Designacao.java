package eapli.base.teammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * A designacao do Equipa e Tipo de Equipa.
 *
 * Esta class representa um desginacao ou descricao que identifica um Nivel de Criticidade.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Embeddable
public class Designacao implements ValueObject, Comparable<Designacao>{

    private static final long serialVersionUID = 1L;

    private String descricao;

    public Designacao(final String designacao) {
        if (StringPredicates.isNullOrEmpty(designacao)) {
            throw new IllegalArgumentException(
                    "Identifier designacao should neither be null nor empty");
        }
        // TODO validate invariants, i.e., identifier designacao regular
        // expression
        this.descricao = designacao;
    }

    protected Designacao() {
        // for ORM
    }

    public static Designacao valueOf(final String designacao) {
        return new Designacao(designacao);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Designacao)) {
            return false;
        }

        final Designacao that = (Designacao) o;
        return this.descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
        return this.descricao.hashCode();
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public int compareTo(final Designacao arg0) {
        return descricao.compareTo(arg0.descricao);
    }
}
