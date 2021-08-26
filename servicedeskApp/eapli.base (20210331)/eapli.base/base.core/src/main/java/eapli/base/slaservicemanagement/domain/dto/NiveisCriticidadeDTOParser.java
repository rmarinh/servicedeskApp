package eapli.base.slaservicemanagement.domain.dto;

import eapli.base.slaservicemanagement.domain.*;
import eapli.framework.representations.dto.DTOParser;

public class NiveisCriticidadeDTOParser implements DTOParser<NiveisCriticidadeDTO, NiveisCriticidade> {

    @Override
    public NiveisCriticidade valueOf(NiveisCriticidadeDTO dto) {
        final Cor cor = Cor.valueOf(dto.cor);
        final EscalaNumerica escalaNumerica = new EscalaNumerica(dto.escalaNumerica);
        final Etiqueta etiqueta = new Etiqueta(dto.etiqueta);
        final TempoMaximoAprovacao tempoMaximoAprovacao = new TempoMaximoAprovacao(dto.tempoMaximoAprovacao);
        final TempoMedioAprovacao tempoMedioAprovacao = new TempoMedioAprovacao(dto.tempoMedioAprovacao);
        final TempoMaximoResolucao tempoMaximoResolucao = new TempoMaximoResolucao(dto.tempoMaximoResolucao);
        final TempoMedioResolucao tempoMedioResolucao = new TempoMedioResolucao(dto.tempoMedioResolucao);

        if (dto.version == -20L) {
            return new NiveisCriticidade(cor, escalaNumerica,
                    etiqueta, tempoMaximoAprovacao,
                    tempoMedioAprovacao, tempoMaximoResolucao, tempoMedioResolucao);
        } else {
            return new NiveisCriticidade(dto.version, dto.identificador,
                    etiqueta, escalaNumerica, cor,
                    tempoMedioAprovacao, tempoMaximoAprovacao, tempoMaximoResolucao, tempoMedioResolucao);
        }

    }

}
