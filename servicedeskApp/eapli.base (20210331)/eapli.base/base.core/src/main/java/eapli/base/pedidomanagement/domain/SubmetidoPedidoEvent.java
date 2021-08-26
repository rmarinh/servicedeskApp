package eapli.base.pedidomanagement.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SubmetidoPedidoEvent")
public class SubmetidoPedidoEvent extends AbstractPedidoEvent {
    private static final long serialVersionUID = 1L;

    // for ORM
    protected SubmetidoPedidoEvent() {
        super();
    }

    public SubmetidoPedidoEvent(final Pedido what) {
        super(what);
    }
}