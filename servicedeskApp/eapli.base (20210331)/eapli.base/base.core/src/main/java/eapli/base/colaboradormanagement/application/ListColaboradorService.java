/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.colaboradormanagement.application;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.base.colaboradormanagement.repositories.ColaboradorRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
public class ListColaboradorService {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ColaboradorRepository repo = PersistenceContext.repositories().colaboradores();

    /**
     * @return
     */
    public Iterable<ColaboradorDTO> allColaboradoresDTO() {

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.RECURSOS_HUMANO, BaseRoles.USER);
        final Iterable<Colaborador> colabs = this.repo.findAll();

        final List<ColaboradorDTO> ret = new ArrayList<>();
        colabs.forEach(e -> ret.add(e.toDTO()));

        return ret;
    }

    public List<Colaborador> allColaboradores() {

        //authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
        //BaseRoles.RECURSOS_HUMANO, BaseRoles.USER);
        final Iterable<Colaborador> colabs = this.repo.findAll();

        final List<Colaborador> ret = new ArrayList<>();
        colabs.forEach(e -> ret.add(e));

        return ret;
    }

    // Para motor de fluxo de atividades, sem autenticação de user
    public List<Colaborador> allColaboradoresAutomatico() {

        final Iterable<Colaborador> colabs = this.repo.findAll();

        final List<Colaborador> ret = new ArrayList<>();
        colabs.forEach(e -> ret.add(e));

        return ret;
    }

    public Optional<Colaborador> findColaboradorByMecNumber(
            final Long mecNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.RECURSOS_HUMANO);
        return repo.ofIdentity(mecNumber);
    }

    public Optional<Colaborador> findColaboradorByUsername(
            final Username user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER,
                BaseRoles.RECURSOS_HUMANO, BaseRoles.GESTOR_HELPDESK,
                BaseRoles.USER);
        return repo.findByUsername(user.toString());
    }

    //Para motor de fluxo de atividades, sem autenticacao
    public Optional<Colaborador> findColaboradorByUsernameAutomatico(final Username user) {
        return repo.findByUsername(user.toString());
    }

    public Optional<Colaborador> findColaboradorByUsername(
            final String user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_HELPDESK,
                BaseRoles.RECURSOS_HUMANO, BaseRoles.USER);
        return repo.findByUsername(user);
    }

    public ColaboradorDTO findColaboradorByUser(final String user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_HELPDESK,
                BaseRoles.RECURSOS_HUMANO, BaseRoles.USER);
        return repo.findByUsername(user).get().toDTO();
    }

    public List<Colaborador> findAllColaboradoresByEquipa(final Long equipaId) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.POWER_USER, BaseRoles.GESTOR_HELPDESK,
                BaseRoles.RECURSOS_HUMANO, BaseRoles.USER);
        return repo.findAllColaboradoresByEquipa(equipaId);
    }
}
