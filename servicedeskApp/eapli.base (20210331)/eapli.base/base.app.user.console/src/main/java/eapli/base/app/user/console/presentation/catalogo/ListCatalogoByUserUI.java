package eapli.base.app.user.console.presentation.catalogo;

import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.respositories.ServicoRepository;
import eapli.base.teammanagement.domain.Equipa;
import eapli.base.teammanagement.repositories.EquipaRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.presentation.console.AbstractUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class ListCatalogoByUserUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ColaboradorRepository colabRepo = PersistenceContext.repositories().colaboradores();
    private final ServicoRepository sevicoRepo = PersistenceContext.repositories().servicos();
    private final EquipaRepository equipaRepo = PersistenceContext.repositories().equipa();

    private final ListCatalogoController listCatalogoController = new ListCatalogoController();


    private static final Logger LOGGER = LoggerFactory.getLogger(ListCatalogoByUserUI.class);

    @Override
    protected boolean doShow() {
        System.out.println("\nCatálogos disponíveis:\n");

        Iterable<Equipa> equipas = allEquipasByColaborador();

        Set<Catalogo> catalogos = new HashSet<>();

        Set<Servico> servicos = new HashSet<>();

        for (Equipa e : equipas) {
            for (Catalogo c : listCatalogoController.catalogosByEquipa(e)){
                catalogos.add(c);
            }
        }

        for(Catalogo c : catalogos){
            for (Servico s : sevicoRepo.findServicoByCatalogo(c)){
                servicos.add(s);
            }
        }

        for(Servico s : servicos){
            System.out.println(s.toString());
        }
        return true;
    }

    @Override
    public String headline() {
        return "Catálogos";
    }

    public Iterable<Equipa> allEquipasByColaborador() {

        Username user = authz.session().get().authenticatedUser().identity();

        Colaborador colaborador = colabRepo.findByUsername(user.toString()).get();

        return equipaRepo.findAllEquipaByColaborador(colaborador);
    }
}
