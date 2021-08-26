package eapli.base.catalogoservicemanagement.domain.builder;

import eapli.base.catalogoservicemanagement.domain.*;
import eapli.base.slaservicemanagement.domain.NiveisCriticidade;
import eapli.framework.domain.model.DomainFactory;

/**
 * Um builder para a class Catalogo.
 * <p>
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class CatalogoBuilder implements DomainFactory<Catalogo> {
    // TODO: Acabar o builder do Catalogo

    /*private Titulo titulo;
    private DescricaoBreve descricaoBreve;
    private DescricaoCompleta descricaoCompleta;
    private NiveisCriticidade nivelCriticidade;

    public CatalogoBuilder withTitulo(Titulo titulo) {
        this.titulo = titulo;
        return this;
    }

    public CatalogoBuilder withDescricaoBreve(DescricaoBreve descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
        return this;
    }

    public CatalogoBuilder withDescricaoCompleta(DescricaoCompleta descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
        return this;
    }


    public CatalogoBuilder withNivelCriticidade(NiveisCriticidade nivelCriticidade) {
        this.nivelCriticidade = nivelCriticidade;
        return this;
    }*/

    @Override
    public Catalogo build() {
        /*// since the factory knows that all the parts are needed it could throw
        // an exception. however, we will leave that to the constructor
        return new Catalogo(titulo, descricaoBreve,
                descricaoCompleta,
                nivelCriticidade);*/
        return null;
    }
}
