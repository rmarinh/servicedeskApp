package eapli.base.pedidomanagement.dto;

public class PedidoRascunhoDTO {

    public Long pk;
    public Long version;
    public Long idColaborador;
    public String idServico;
    public String urgencia;
    public String dataLimiteResolucao;

    public PedidoRascunhoDTO(Long pk, Long version, Long idColaborador, String idServico, String urgencia, String dataLimiteResolucao) {
        this.pk = pk;
        this.version = version;
        this.idColaborador = idColaborador;
        this.idServico = idServico;
        this.urgencia = urgencia;
        this.dataLimiteResolucao = dataLimiteResolucao;
    }
}
