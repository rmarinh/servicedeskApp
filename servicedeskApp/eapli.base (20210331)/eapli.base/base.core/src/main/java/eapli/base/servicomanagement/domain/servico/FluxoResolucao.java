package eapli.base.servicomanagement.domain.servico;


import eapli.base.atividademanagement.domain.Atividade;
import eapli.base.atividademanagement.domain.AtividadeAutomatica;
import eapli.base.atividademanagement.domain.AtividadeManual;
import eapli.base.teammanagement.domain.EquipaType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class FluxoResolucao implements Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long identificador;

    @OneToOne
    private Atividade atividade;

    private String tipoAtividade;

    @OneToMany
    private Set<EquipaType> tiposEquipa;

    private boolean resolucao;

    protected FluxoResolucao(final Atividade atividade) {
        this.atividade = atividade;

        if(atividade instanceof AtividadeManual){
            this.tipoAtividade = AtividadeManual.class.getSimpleName();
        }else{
            this.tipoAtividade = AtividadeAutomatica.class.getSimpleName();
        }
    }

    protected FluxoResolucao() {
        // for ORM only
    }

    public boolean executar(){
        this.resolucao = true;
        return  this.resolucao;

    }

    public Atividade getAtividade() {
        return this.atividade;
    }

}
