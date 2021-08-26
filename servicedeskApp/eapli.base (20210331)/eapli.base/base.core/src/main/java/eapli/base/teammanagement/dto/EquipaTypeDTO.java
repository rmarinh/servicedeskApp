package eapli.base.teammanagement.dto;

/**
 * A pure DTO for Equipa. Members are public by design.
 */

public class EquipaTypeDTO {

    public Long id;
    public String designacao;
    public String cor;

    public EquipaTypeDTO(final String designacao, final String cor) {
        this.id = -1L;
        this.designacao = designacao;
        this.cor = cor;
    }

    public EquipaTypeDTO(final Long id, final String designacao, final String cor) {
        this.id = id;
        this.designacao = designacao;
        this.cor = cor;
    }
}
