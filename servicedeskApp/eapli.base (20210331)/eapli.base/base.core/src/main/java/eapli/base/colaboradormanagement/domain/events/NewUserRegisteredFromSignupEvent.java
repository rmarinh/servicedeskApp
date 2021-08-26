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
package eapli.base.colaboradormanagement.domain.events;

import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.framework.domain.events.DomainEvent;
import eapli.framework.domain.events.DomainEventBase;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
public class NewUserRegisteredFromSignupEvent extends DomainEventBase implements DomainEvent {

    private static final long serialVersionUID = 1L;

    private final NumeroMecanografico numeroMecanografico;
    private final Username newUser;

    public NewUserRegisteredFromSignupEvent(final NumeroMecanografico numeroMecanografico,
                                            final Username newUser) {
        this.numeroMecanografico = numeroMecanografico;
        this.newUser = newUser;
    }

    public NumeroMecanografico numeroMecanografico() {
        return numeroMecanografico;
    }

    public Username username() {
        return newUser;
    }

    @Override
    public String toString() {
        return "NewUserFromsignup(" + username() + ")";
    }
}
