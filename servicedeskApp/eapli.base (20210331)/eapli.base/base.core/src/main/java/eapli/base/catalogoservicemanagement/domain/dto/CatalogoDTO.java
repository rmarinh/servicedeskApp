package eapli.base.catalogoservicemanagement.domain.dto;

import eapli.framework.representations.dto.DTO;

/**
 * Um DTO para Catalogo.
 * Os membros são públicos por design
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
@DTO
public class CatalogoDTO {

    public Long identificador;
    public String titulo;
    public String descricaoBreve;
    public String descricaoCompleta;

    @SuppressWarnings("squid:ClassVariableVisibilityCheck")
    public CatalogoDTO(final Long identificador, final String titulo, final String descricaoBreve,
                       final String descricaoCompleta) {
        this.identificador = identificador;
        this.titulo = titulo;
        this.descricaoBreve = descricaoBreve;
        this.descricaoCompleta = descricaoCompleta;
    }
}
