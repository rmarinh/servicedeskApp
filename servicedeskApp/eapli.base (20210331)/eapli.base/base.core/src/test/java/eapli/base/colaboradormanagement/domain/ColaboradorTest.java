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
package eapli.base.colaboradormanagement.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import eapli.base.colaboradormanagement.domain.*;
import eapli.framework.infrastructure.authz.domain.model.*;
import org.junit.Test;

import eapli.base.usermanagement.domain.BaseRoles;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class ColaboradorTest {

    Colaborador colaborador1 = new Colaborador(NomeCompleto.valueOf("nome completo"),
            DataNascimento.valueOf("01/01/1990"), LocalResidencia.valueOf("local residencia"),
            NumeroContacto.valueOf("912223344"));

    Colaborador colaborador2 = new Colaborador(NomeCompleto.valueOf("nome completo"),
            DataNascimento.valueOf("01/01/1990"), LocalResidencia.valueOf("local residencia"),
            NumeroContacto.valueOf("912223344"));

    Colaborador colaborador3 = new Colaborador(NomeCompleto.valueOf("nome"),
            DataNascimento.valueOf("01/01/1992"), LocalResidencia.valueOf("teste"),
            NumeroContacto.valueOf("9111111"));

    public static SystemUser dummyUser(final String username, final Role... roles) {
        // should we load from spring context?
        final SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        return userBuilder.with(username, "duMMy1", "dummy", "dummy", "a@b.ro").withRoles(roles).build();
    }

    private SystemUser getNewDummyUser() {
        return dummyUser("dummy", BaseRoles.POWER_USER);
    }

    private SystemUser getNewDummyUserTwo() {
        return dummyUser("dummy-two", BaseRoles.POWER_USER);
    }

    @Test
    public void ensureColaboradoresEqualsPassesForTheSameName() throws Exception {

        final boolean expected = colaborador1.nomeCompleto().equals(colaborador2.nomeCompleto());

        assertTrue(expected);
    }

    @Test
    public void ensureColaboradoresEqualsFailsForDifferenteName() throws Exception {

        final boolean expected = colaborador1.nomeCompleto().equals(colaborador3.nomeCompleto());

        assertFalse(expected);
    }

    @Test
    public void ensureColaboradoresEqualsAreTheSameForTheSameInstance() throws Exception {

        final boolean expected = colaborador1.equals(colaborador1);

        assertTrue(expected);
    }

}
