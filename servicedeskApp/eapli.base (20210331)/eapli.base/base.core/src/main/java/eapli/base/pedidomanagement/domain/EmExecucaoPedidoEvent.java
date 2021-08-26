package eapli.base.pedidomanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("EmExecucaoPedidoEvent")
public class EmExecucaoPedidoEvent extends AbstractPedidoEvent {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Colaborador colaborador;

    // for ORM
    protected EmExecucaoPedidoEvent() {
        super();
    }

    public EmExecucaoPedidoEvent(final Pedido what, final Colaborador colaborador) {
        super(what);
        this.colaborador = colaborador;
    }

    public Colaborador who() {
        return this.colaborador;
    }
}