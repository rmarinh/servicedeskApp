package eapli.base.pedidomanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("AprovadoPedidoEvent")
public class AprovadoPedidoEvent extends AbstractPedidoEvent {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Colaborador colaborador;
    @Lob
    private String formularioAprovacao;


    // for ORM
    protected AprovadoPedidoEvent() {
        super();
    }

    public AprovadoPedidoEvent(final Pedido what, final Colaborador colaborador) {
        super(what);
        this.colaborador = colaborador;
        this.formularioAprovacao = null;
    }
    public AprovadoPedidoEvent(final Pedido what, final Colaborador colaborador, String formularioAprovacao) {
        super(what);
        this.colaborador = colaborador;

        this.formularioAprovacao =formularioAprovacao ;
    }

    public Colaborador who() {
        return this.colaborador;
    }
}