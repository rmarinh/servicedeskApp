package eapli.base.pedidomanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("ConcluidoPedidoEvent")
public class ConcluidoPedidoEvent extends AbstractPedidoEvent {

    private static final long serialVersionUID = 1L;
    @Lob
    private String formularioExecucao;


    // for ORM
    protected ConcluidoPedidoEvent() {
        super();
    }

    public ConcluidoPedidoEvent(final Pedido what) {
        super(what);
        this.formularioExecucao = null;
    }

    public ConcluidoPedidoEvent(final Pedido what, String formularioExecucao) {
        super(what);
        this.formularioExecucao =formularioExecucao ;
    }


}