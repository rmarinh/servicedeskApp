package eapli.base.teammanagement.dto;

import eapli.framework.representations.dto.DTO;

/**
 * A pure DTO for Equipa. Members are public by design.
 */

public class EquipaDTO {

    public Long id;
    public String acronimo;
    public String designacao;
    public String cor;
    public Long tipo;

    @SuppressWarnings("squid:ClassVariableVisibilityCheck")
    public EquipaDTO(final Long id, final String acronimoEquipa, final String designacaoEquipa, final String corEquipa, final Long tipoEquipa) {
        this.id = id;
        this.acronimo = acronimoEquipa;
        this.designacao = designacaoEquipa;
        this.cor = corEquipa;
        this.tipo = tipoEquipa;

    }
}
