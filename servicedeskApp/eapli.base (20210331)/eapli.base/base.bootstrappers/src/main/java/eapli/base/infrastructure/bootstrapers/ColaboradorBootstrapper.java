package eapli.base.infrastructure.bootstrapers;

import eapli.base.catalogoservicemanagement.application.RegisterCatalogController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.colaboradormanagement.application.AddColaboradorController;
import eapli.base.colaboradormanagement.domain.*;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Carlos Moutinho 1140858@isep.ipp.pt
 */
public class ColaboradorBootstrapper implements Action {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersBootstrapperBase.class);

    @Override
    public boolean execute() {
        Role role = BaseRoles.USER;
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        //Colaboradores de Pedidos
        registerColaborador("userPedidoA", "passA1", "firstnamePedidoA","Um",  "lapr4_1+2@protonmail.com",  "01/01/1990", "localum", "911111131", roles, null);
        registerColaborador("userPedidoB", "passA1", "firstnamePedidoB","Dois",  "lapr4_1+3@protonmail.com",  "01/01/1990", "localdois", "911111132", roles, null);
        registerColaborador("userPedidoC", "passA1", "firstnamePedidoC","Dois",  "lapr4_1+4@protonmail.com",  "01/01/1990", "localdois", "911111133", roles, null);

        //Colaboradores de Equipas
        registerColaborador("fabioSilva", "passA1", "Fabio","Silva",  "1181895@isep.ipp.pt",  "01/01/1990", "Porto", "931111111", roles, null);
        registerColaborador("rafaelSoares", "passA1", "Rafael","Soares",  "1181882@isep.ipp.pt",  "01/01/1990", "Porto", "931111111", roles, null);
        registerColaborador("ruiMarinho", "passA1", "Rui","Marinho",  "1171602@isep.ipp.pt",  "01/01/1990", "Porto", "931111111", roles, null);
        registerColaborador("sarahSilva", "passA1", "Sarah","Silva",  "1181892@isep.ipp.pt",  "01/01/1990", "Porto", "931111111", roles, null);
        registerColaborador("carlosMoutinho", "passA1", "Carlos","Moutinho",  "1140858@isep.ipp.pt",  "01/01/1990", "Porto", "931111111", roles, null);

        //Colaboradores Responsaveis de Equipas
        registerColaborador("quimBairro", "passA1", "Quim","Bairro",  "lapr4_1@protonmail.com",  "01/01/1990", "Porto", "931111111", roles, null);
        registerColaborador("antonioZambujeira", "passA1", "Antonio","Zambujeira",  "lapr4_2@protonmail.com",  "01/01/1990", "Lisboa", "931111111", roles, null);
        registerColaborador("zecaAlonso", "passA1", "Zaca","Alonso",  "lapr4_3@protonmail.com",  "01/01/1990", "Santa Maria da Feira", "931111111", roles, null);

        // System user - NAO APAGAR - este user sera usado pelo sistema na aprovacao
        registerColaborador("SystemUser1", "passA1", "System", "User",
                "lapr4_4@protonmail.com", "03/01/1990", "cyberspace", "990000001",
                roles, null);

        return false;
    }

    private void registerColaborador(final String username, final String password,
                                     final String firstName, final String lastName, final String email, final String date,
                                     final String localResidencia, final String numeroContacto, Set<Role> roles,
                                     final ColaboradorDTO responsavel) {
        final AddColaboradorController ctrl = new AddColaboradorController();

        try {
            Colaborador colaborador = ctrl.addColaborador(username, password, firstName,
                    lastName, email, date, localResidencia,
                    numeroContacto, roles, responsavel);

            System.out.println("Colaborador criado: " + colaborador.nomeCompleto());

        } catch (Exception e) {
            System.out.println("Colaborador NAO criado: " + firstName + " " + lastName + " " + e);
        }
    }
}
