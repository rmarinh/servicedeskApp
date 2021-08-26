package eapli.base.pedidomanagement.domain;

import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.time.util.Calendars;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "AbstractPedidoEvent")

public class AbstractPedidoEvent extends DomainEventBase implements DomainEvent {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;

    @ManyToOne
    private final Pedido pedido;


    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdOn;

    protected AbstractPedidoEvent() {
        // for ORM
        this.pedido = null;
        this.createdOn = Calendars.now();
    }

    public AbstractPedidoEvent(final Pedido what) {
        this.pedido = what;
        this.createdOn = Calendars.now();
    }

    public Pedido what() {
        return this.pedido;
    }

    public Calendar when() {
        return this.createdOn;
    }


}
