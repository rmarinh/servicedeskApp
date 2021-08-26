package eapli.base.pedidomanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import java.util.UUID;

@Embeddable
public class PedidoToken implements ValueObject, Comparable<PedidoToken> {

    private static final long serialVersionUID = -1820803667379631580L;

    private final String token;

    public PedidoToken() {
        token = UUID.randomUUID().toString();
    }

    private PedidoToken(final String value) {
        Preconditions.nonEmpty(value);
        token = UUID.fromString(value).toString();
    }

    public static PedidoToken valueOf(final String value) {
        return new PedidoToken(value);
    }

    @Override
    public String toString() {
        return token;
    }

    public static PedidoToken newToken() {
        return valueOf(UUID.randomUUID().toString());
    }

    @Override
    public int compareTo(final PedidoToken o) {
        return token.compareTo(o.token);
    }

    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof PedidoToken)) {
            return false;
        }
        final PedidoToken other = (PedidoToken) o;
        return token.equals(other.token);
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }
}