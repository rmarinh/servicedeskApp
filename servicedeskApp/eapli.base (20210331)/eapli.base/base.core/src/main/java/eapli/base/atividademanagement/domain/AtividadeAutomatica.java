package eapli.base.atividademanagement.domain;

import eapli.base.formulario.Formulario;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Automatica")
public class AtividadeAutomatica extends Atividade implements Serializable {

    private Script script;

    public AtividadeAutomatica(DescricaoBreve descricaoBreve, Script script) {
        super(descricaoBreve);
        this.script = script;
    }

    public AtividadeAutomatica() {

    }
    public  String Script(){
        return this.script.getScript();
    }


}
