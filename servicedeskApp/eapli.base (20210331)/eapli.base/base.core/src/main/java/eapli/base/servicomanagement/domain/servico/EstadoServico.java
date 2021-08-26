package eapli.base.servicomanagement.domain.servico;

import eapli.framework.domain.model.ValueObject;


/**
 *  * O estado  de um Servico.
 *  *
 *  * Esta class representa o estado de um Servic:
 *      PUublicado , Incompleto
     * @author Rui Marinho
     *
     */


public enum EstadoServico implements ValueObject {

        COMPLETO, INCOMPLETO;
}
