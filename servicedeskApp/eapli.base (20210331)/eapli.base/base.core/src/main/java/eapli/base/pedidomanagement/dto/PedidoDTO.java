package eapli.base.pedidomanagement.dto;

public class PedidoDTO {

    public Long pk;
    public Long version;
    public String token;
    public Long idColaborador;
    public String idServico;
    public Long idSLA;
    public String status;

    public PedidoDTO(Long pk, Long version, String token, Long idColaborador, String idServico, Long idSLA, String status) {
        this.pk = pk;
        this.version = version;
        this.token = token;
        this.idColaborador = idColaborador;
        this.idServico = idServico;
        this.idSLA = idSLA;
        this.status = status;
    }
}