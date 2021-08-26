
package eapli.base.servicomanagement.domain.servico;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;

/**
 * A palavra-chaves de um Servico.
 * <p>
 * Esta class representa uma palavra chave relacionada um Servico.
 *
 * @author Rui Marinho
 */
@Embeddable
public class PalavraChave implements ValueObject, Comparable<PalavraChave> {

    private static final long serialVersionUID = 1L;

    private String palavraChave;

    public PalavraChave(final String palavraChave) {
        if (StringPredicates.isNullOrEmpty(palavraChave)) {
            throw new IllegalArgumentException(
                    "keywords for Service should neither be null nor empty");
        }
        // TODO  RM - Validar Palavras chave tipo alfanumerico ?
        // expression
        this.palavraChave = palavraChave;
    }

    protected PalavraChave() {
        // for ORM
    }

    public static PalavraChave valueOf(final String palavraChave) {
        return new PalavraChave(palavraChave);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PalavraChave)) {
            return false;
        }

        final PalavraChave that = (PalavraChave) o;
        return this.palavraChave.equals(that.palavraChave);
    }

    @Override
    public int hashCode() {
        return this.palavraChave.hashCode();
    }

    @Override
    public String toString() {
        return this.palavraChave;
    }

    @Override
    public int compareTo(final PalavraChave arg0) {
        return palavraChave.compareTo(arg0.palavraChave);
    }
}