package eapli.base.colaboradormanagement.dto;

/**
 * A pure DTO for Colaborador. Members are public by design.
 */

public class ColaboradorDTO {

    public String username;
    public Long numeroMeconografico;
    public String nome;
    public String dataNascimento;
    public String localResidencia;
    public String numeroContacto;


    @SuppressWarnings("squid:ClassVariableVisibilityCheck")
    public ColaboradorDTO(final String username, final Long numeroMeconografico, final String nome, final String dataNascimento, final String localResidencia, final String numeroContacto) {

        this.username = username;
        this.numeroMeconografico = numeroMeconografico;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.localResidencia = localResidencia;
        this.numeroContacto = numeroContacto;

    }
}
