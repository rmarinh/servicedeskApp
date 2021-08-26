package eapli.base.servicomanagement.domain.servico;


import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.domain.AtividadeAutomatica;
import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.colaboradormanagement.domain.Colaborador;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FluxoAprovacao implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long identificador;


    @ManyToMany
    private Set<Colaborador> aprovadores = new HashSet<>();

    @OneToOne
    private Atividade atividade;

    private String tipoAtividade;

    private boolean aprovacao;

    protected FluxoAprovacao(String cargoResponsavel, Atividade atividade) {
        this.aprovadores = new HashSet<>();
        this.atividade = atividade;
        this.aprovacao = Boolean.FALSE;

        if(atividade instanceof AtividadeManual){
            this.tipoAtividade = AtividadeManual.class.getSimpleName();
        }else{
            this.tipoAtividade = AtividadeAutomatica.class.getSimpleName();
        }
    }

    protected FluxoAprovacao(Set<Colaborador> aprovadores, Atividade atividade) {
        this.aprovadores.addAll(aprovadores);
        this.atividade = atividade;
        this.aprovacao = Boolean.FALSE;

        if(atividade instanceof AtividadeManual){
            this.tipoAtividade = AtividadeManual.class.getSimpleName();
        }else{
            this.tipoAtividade = AtividadeAutomatica.class.getSimpleName();
        }
    }

    protected FluxoAprovacao() {
        // for ORM only
    }

    public boolean executar(){
        this.aprovacao =true;
        return this.aprovacao;
    }

    public Atividade getAtividade() {
        return this.atividade;
    }
}
