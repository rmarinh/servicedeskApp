package eapli.base.pedidomanagement.domain;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.time.util.Calendars;
import eapli.framework.validations.Invariants;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AvaliacaoPedido implements AggregateRoot<Long> {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne()
    private Pedido pedido;

    //1-Muito Mau, 2-Mau, 3-Razoavel, 4-Bom, 5-Muito Bom
    @Column(nullable = false)
    private int cotacao;

    @Column(nullable = true)
    private String comentario;

    protected AvaliacaoPedido() {
        // for ORM
    }

    public AvaliacaoPedido(final Pedido pedido, final int cotacao, final String comentario) {
        this.pedido = pedido;
        this.cotacao = cotacao;
        this.comentario = comentario;
    }

    @Override
    public boolean sameAs(Object other) {
        if (other instanceof AvaliacaoPedido) {
            other = (PedidoRascunho) other;
            if (((AvaliacaoPedido) other).id.equals(this.id)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public Long identity() {
        return id;
    }

    public Pedido pedido() {
        return pedido;
    }

    public int cotacao() {
        return this.cotacao;
    }

    public String comentario() {
        return this.comentario;
    }

    @Override
    public String toString() {
        return "AvaliacaoPedido{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", cotacao=" + cotacao +
                ", comentario='" + comentario + '\'' +
                '}';
    }
}
