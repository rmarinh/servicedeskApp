package eapli.base.teammanagement.dto;

import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.representations.dto.DTOParser;

public class EquipaDTOParser implements DTOParser<EquipaDTO, Equipa> {

    private final EquipaTypeRepository equipaTypeRepository;

    /**
     * Configure the parser by injecting the necessary dependency.
     *
     * @param equipaTypeRepository the Equipa type repository
     */
    public EquipaDTOParser(final EquipaTypeRepository equipaTypeRepository) {
        this.equipaTypeRepository = equipaTypeRepository;
    }

    @Override
    public Equipa valueOf(final EquipaDTO dto) {
// retrieve the Equipa type
        final EquipaType type = equipaTypeRepository.ofIdentity(dto.tipo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unknown Equipa type: " + dto.designacao));

        return new Equipa(new Acronimo(dto.acronimo), new Designacao(dto.designacao), new Cor(dto.cor), type);
    }
}
