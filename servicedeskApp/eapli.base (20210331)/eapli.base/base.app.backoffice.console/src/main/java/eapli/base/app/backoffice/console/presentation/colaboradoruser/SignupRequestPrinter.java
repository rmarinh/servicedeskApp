/**
 *
 */
package eapli.base.app.backoffice.console.presentation.colaboradoruser;

import eapli.base.colaboradormanagement.domain.SignupRequest;
import eapli.framework.visitor.Visitor;

/**
 * Created by Rafael Soares 1181882@isep.ipp.pt
 *
 */
@SuppressWarnings("squid:S106")
class SignupRequestPrinter implements Visitor<SignupRequest> {

    @Override
    public void visit(SignupRequest visitee) {
        System.out.printf("%-10s%-20s%-10s%n", visitee.identity(), visitee.name(),
                visitee.numeroMecanografico());
    }
}
