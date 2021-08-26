package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.framework.visitor.Visitor;

public class ColaboradorPrinter implements Visitor<Colaborador> {

    @Override
    public void visit(final Colaborador visitee) {
        System.out.printf("%-30s", visitee.nomeCompleto());
    }

}