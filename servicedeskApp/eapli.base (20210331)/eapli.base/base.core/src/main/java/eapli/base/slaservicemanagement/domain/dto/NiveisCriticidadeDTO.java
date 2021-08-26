package eapli.base.slaservicemanagement.domain.dto;

public class NiveisCriticidadeDTO {

     public Long version;
     public Long identificador;

    public String cor;
    public String escalaNumerica;
    public String etiqueta;
    public String tempoMedioAprovacao;
    public String tempoMaximoAprovacao;
    public String tempoMedioResolucao;
    public String tempoMaximoResolucao;

    @SuppressWarnings("squid:ClassVariableVisibilityCheck")
    public NiveisCriticidadeDTO(final String cor, final String escalaNumerica,
                                final String etiqueta, final String tempoMedioAprovacao, final String tempoMaximoAprovacao,
                                final String tempoMedioResolucao, final String tempoMaximoResolucao) {
        this.version = -20L;
        this.identificador = -20L;
        this.cor = cor;
        this.escalaNumerica = escalaNumerica;
        this.etiqueta = etiqueta;
        this.tempoMedioAprovacao = tempoMedioAprovacao;
        this.tempoMaximoAprovacao = tempoMaximoAprovacao;
        this.tempoMedioResolucao = tempoMedioResolucao;
        this.tempoMaximoResolucao = tempoMaximoResolucao;
    }

    public NiveisCriticidadeDTO(Long version, Long identificador, String cor, String escalaNumerica, String etiqueta,
                                String tempoMedioAprovacao, String tempoMaximoAprovacao, String tempoMedioResolucao, String tempoMaximoResolucao) {
        this.version = version;
        this.identificador = identificador;
        this.cor = cor;
        this.escalaNumerica = escalaNumerica;
        this.etiqueta = etiqueta;
        this.tempoMedioAprovacao = tempoMedioAprovacao;
        this.tempoMaximoAprovacao = tempoMaximoAprovacao;
        this.tempoMedioResolucao = tempoMedioResolucao;
        this.tempoMaximoResolucao = tempoMaximoResolucao;
    }

    @Override
    public String toString() {
        return "NiveisCriticidadeDTO{" +
                "cor='" + cor + '\'' +
                ", escalaNumerica='" + escalaNumerica + '\'' +
                ", etiqueta='" + etiqueta + '\'' +
                ", tempoMedioAprovacao='" + tempoMedioAprovacao + '\'' +
                ", tempoMaximoAprovacao='" + tempoMaximoAprovacao + '\'' +
                ", tempoMedioResolucao='" + tempoMedioResolucao + '\'' +
                ", tempoMaximoResolucao='" + tempoMaximoResolucao + '\'' +
                '}';
    }
}
