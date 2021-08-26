package eapli.base.pedidomanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("RejeitarPedidoEvent")
public class RejeitarPedidoEvent extends AbstractPedidoEvent {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Colaborador colaborador;

    // for ORM
    protected RejeitarPedidoEvent() {
        super();
    }

    public RejeitarPedidoEvent(final Pedido what, final Colaborador colaborador) {
        super(what);
        this.colaborador = colaborador;
    }

    public Colaborador who() {
        return this.colaborador;
    }
}