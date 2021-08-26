package eapli.base.catalogoservicemanagement.repositories;

import eapli.base.catalogoservicemanagement.domain.Catalogo;

import eapli.base.catalogoservicemanagement.domain.dto.CatalogoDTO;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.teammanagement.domain.Equipa;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

/**
 *
 *
 * This
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
public interface CatalogoRepository
        extends DomainRepository<Long, Catalogo> {

    public Iterable<Catalogo> findAll();

    public Iterable<Catalogo> findAllByEquipa(Equipa equipa);

    public Optional<Catalogo> findById(Long identifier);

    Optional<Catalogo> findByTitulo(String titulo);

    public Iterable<Catalogo> findCatalogosGrantedToColaborador(Colaborador colaborador);

}
