package eapli.base.pedidomanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("EmAprovacaoPedidoEvent")
public class EmAprovacaoPedidoEvent extends AbstractPedidoEvent {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Colaborador colaborador;

    // for ORM
    protected EmAprovacaoPedidoEvent() {
        super();
    }

    public EmAprovacaoPedidoEvent(final Pedido what, final Colaborador colaborador) {
        super(what);
        this.colaborador = colaborador;
    }

    public Colaborador who() {
        return this.colaborador;
    }
}