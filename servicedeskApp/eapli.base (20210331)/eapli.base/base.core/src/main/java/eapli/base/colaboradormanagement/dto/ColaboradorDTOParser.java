package eapli.base.colaboradormanagement.dto;

import eapli.base.colaboradormanagement.domain.*;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.representations.dto.DTOParser;

public class ColaboradorDTOParser implements DTOParser<ColaboradorDTO, Colaborador> {

    @Override
    public Colaborador valueOf(final ColaboradorDTO dto) {

        return new Colaborador(new NomeCompleto(dto.nome), new DataNascimento(dto.dataNascimento), new LocalResidencia(dto.localResidencia),
                new NumeroContacto(dto.numeroContacto));
    }
}
