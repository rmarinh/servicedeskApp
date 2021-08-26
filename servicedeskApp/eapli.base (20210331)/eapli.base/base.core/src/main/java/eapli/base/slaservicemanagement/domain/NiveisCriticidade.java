package eapli.base.slaservicemanagement.domain;

import eapli.base.slaservicemanagement.domain.dto.NiveisCriticidadeDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * O SLA.
 *
 * Esta class representa os niveis de criticidade.
 *
 * @author Sara Silva 1181892@isep.ipp.pt
 *
 */
@Entity
public class NiveisCriticidade implements Serializable, AggregateRoot<Long>, DTOable<NiveisCriticidadeDTO> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long identificador;

    @Column(nullable = false)
    private Etiqueta etiqueta;

    @Column(nullable = false)
    private EscalaNumerica escalaNumerica;

    @Enumerated(EnumType.STRING)     @Column(nullable = false)
    private Cor cor;

    @Column(nullable = false)
    private TempoMedioAprovacao tempoMedioAprovacao;

    @Column(nullable = false)
    private TempoMaximoAprovacao tempoMaximoAprovacao;

    @Column(nullable = false)
    private TempoMedioResolucao tempoMedioResolucao;

    @Column(nullable = false)
    private TempoMaximoResolucao tempoMaximoResolucao;

    public NiveisCriticidade(final Cor cor, final EscalaNumerica escalaNumerica,
                             final Etiqueta etiqueta, final TempoMaximoAprovacao tempoMaximoAprovacao, final TempoMedioAprovacao tempoMedioAprovacao,
                            final TempoMaximoResolucao tempoMaximoResolucao, final TempoMedioResolucao tempoMedioResolucao) {

        if (cor== null || escalaNumerica== null || etiqueta== null || tempoMaximoAprovacao == null || tempoMedioAprovacao == null) {
            throw new IllegalArgumentException();
        }
        this.cor = cor;
        this.escalaNumerica=escalaNumerica;
        this.etiqueta=etiqueta;
        this.tempoMaximoAprovacao = tempoMaximoAprovacao;
        this.tempoMedioAprovacao = tempoMedioAprovacao;
        this.tempoMaximoResolucao = tempoMaximoResolucao;
        this.tempoMedioResolucao = tempoMedioResolucao;
    }

    public NiveisCriticidade(Long version, Long identificador, Etiqueta etiqueta, EscalaNumerica escalaNumerica, Cor cor,
                             TempoMedioAprovacao tempoMedioAprovacao, TempoMaximoAprovacao tempoMaximoAprovacao,
                             TempoMaximoResolucao tempoMaximoResolucao, TempoMedioResolucao tempoMedioResolucao) {
        this.version = version;
        this.identificador = identificador;
        this.etiqueta = etiqueta;
        this.escalaNumerica = escalaNumerica;
        this.cor = cor;
        this.tempoMedioAprovacao = tempoMedioAprovacao;
        this.tempoMaximoAprovacao = tempoMaximoAprovacao;
        this.tempoMaximoResolucao = tempoMaximoResolucao;
        this.tempoMedioResolucao = tempoMedioResolucao;
    }

    protected NiveisCriticidade() {
        // for ORM only
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public Long identificador() {
        return identity();
    }

    public Cor cor() {
        return this.cor;
    }


    public EscalaNumerica escalaNumerica(){ return this.escalaNumerica;
    }

    public Etiqueta etiqueta(){
        return this.etiqueta;
    }

    public TempoMaximoAprovacao tempoMaximoAprovacao(){
        return this.tempoMaximoAprovacao;
    }

    public TempoMedioAprovacao tempoMedioAprovacao(){
        return this.tempoMedioAprovacao;
    }

    public TempoMaximoResolucao tempoMaximoResolucao(){
        return this.tempoMaximoResolucao;
    }

    public TempoMedioResolucao tempoMedioResolucao(){
        return this.tempoMedioResolucao;
    }

    @Override
    public Long identity() {
        return this.identificador;
    }

    @Override
    public String toString() {
        return "Niveis de Criticidade{" +
                "version=" + version +
                ", identificador=" + identificador +
                ", cor=" + cor +
                ", escala numérica=" + escalaNumerica +
                ", etiqueta=" + etiqueta +
                ", tempo máximo=" + tempoMaximoAprovacao +
                ", tempo médio=" + tempoMedioAprovacao +
                '}';
    }



    @Override
    public NiveisCriticidadeDTO toDTO() {
        return new NiveisCriticidadeDTO(this.version, this.identificador ,this.cor.toString(), this.escalaNumerica.toString(),
                                        this.etiqueta.toString(), this.tempoMaximoAprovacao.toString(),
                                        this.tempoMedioAprovacao.toString(), this.tempoMaximoResolucao.toString(), this.tempoMedioResolucao.toString());
    }
}
