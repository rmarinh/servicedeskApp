package eapli.base.app.servicosrh.console.presentation.atividades;

import eapli.base.atividademanagement.domain.Atividade;
import eapli.framework.visitor.Visitor;

public class AtividadePrinter implements Visitor<Atividade> {

    @Override
    public void visit(final Atividade visitee) {
        System.out.printf("%s -- %s", visitee.identity(), visitee.descricaoBreve());
    }

}
