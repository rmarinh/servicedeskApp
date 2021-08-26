package eapli.base.catalogoservicemanagement.domain.dto;

import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.teammanagement.repositories.EquipaTypeRepository;
import eapli.framework.representations.dto.DTOParser;

public class CatalogoDTOParser implements DTOParser<CatalogoDTO, Catalogo> {

    @Override
    public Catalogo valueOf(CatalogoDTO dto) {
        return null;
    }
}
