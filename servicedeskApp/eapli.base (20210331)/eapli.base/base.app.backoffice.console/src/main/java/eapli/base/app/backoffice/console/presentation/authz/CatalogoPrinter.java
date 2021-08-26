package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 *
 */
@SuppressWarnings({ "squid:S106" })
public class CatalogoPrinter implements Visitor<Catalogo> {

    @Override
    public void visit(final Catalogo visitee) {
        System.out.printf("%-20s%-40s%-40s", visitee.identity(), visitee.descricaoBreve(), visitee.descricaoCompleta());
    }
}
