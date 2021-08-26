package eapli.base.atividademanagement.domain;

import eapli.base.formulario.Formulario;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Manual")
public class AtividadeManual extends Atividade implements Serializable {
    /*
    Os dados/informação a recolher no formulário (i) de solicitação do serviço e/ou (ii) no
formulário de uma atividade manual. Cada formulário tem um identificador único no
âmbito do serviço, um nome e uma lista de atributos a solicitar ao colaborador. Um
atributo caracteriza-se por um nome de variável, uma etiqueta (label) de apresentação,
uma descrição de ajuda, um tipo de dados base (e.g., numérico, texto, data, booleano,
seleção de valores pré-definidos) e uma expressão regular que permita a sua validação
local. O formulário deve ainda ter associado um script que permita proceder à sua
validação;
     */

    @Lob
    private String formulario;

    public AtividadeManual(DescricaoBreve descricaoBreve, Formulario formulario) {
        super(descricaoBreve);
        this.formulario = Formulario.formularioToXML(formulario);
    }

    public AtividadeManual() {

    }

    public Formulario formulario() {
       return Formulario.xmlToFormulario(formulario);
    }

}
