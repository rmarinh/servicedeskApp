package eapli.base.app.servicosrh.console.presentation.catalogo;

import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.framework.presentation.console.AbstractListUI;

import eapli.framework.visitor.Visitor;

/**
 *
 * @author losa
 */
@SuppressWarnings({ "squid:S106" })
public class ListCatalogosUI extends AbstractListUI<Catalogo> {

    private ListCatalogoController theController = new ListCatalogoController();

    @Override
    public String headline() {
        return "Lista de Catalogos existentes em sistema:";
    }

    @Override
    protected String emptyMessage() {
        return "Não exsitem nenhuma catalogo disponivel!";
    }

    @Override
    protected Iterable<Catalogo> elements() {
        return theController.activeCatalogos();
    }

    @Override
    protected Visitor<Catalogo> elementPrinter() {
        return new CatalogoPrinter();
    }

    @Override
    protected String elementName() {
        return "Catalogos";
    }

    @Override
    protected String listHeader() {
        return String.format("#  %-20s%-40s%-40s", "Identificador", "Titulo", "Descricao Breve");
    }
}