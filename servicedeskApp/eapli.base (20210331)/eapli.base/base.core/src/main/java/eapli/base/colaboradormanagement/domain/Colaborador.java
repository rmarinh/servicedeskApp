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

import eapli.base.colaboradormanagement.dto.ColaboradorDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.representations.dto.DTOable;

import javax.persistence.*;
import java.util.Date;

/**
 * A Client User.
 * <p>
 * This class represents client users. It follows a DDD approach where User
 * is the root entity of the Base User Aggregate and all of its properties
 * are instances of value objects. A Client User references a System User
 * <p>
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Rafael Soares 1181882@isep.ipp.pt
 */
@Entity
public class Colaborador implements AggregateRoot<Long>, DTOable<ColaboradorDTO> {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    @Column(nullable = true)
    private Long numeroMecanografico;
    private NomeCompleto nomeCompleto;
    private DataNascimento dataNascimento;
    private LocalResidencia localResidencia;
    private NumeroContacto numeroContacto;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;

    @OneToOne()
    private Colaborador responsavelHierarquico;

    public Colaborador(final SystemUser user,
                       final NomeCompleto nomeCompleto, final DataNascimento dataNascimento,
                       final LocalResidencia localResidencia, final NumeroContacto numeroContacto,
                       final Colaborador responsavelHierarquico) {
        if (user == null || nomeCompleto == null || dataNascimento == null
                || localResidencia == null || numeroContacto == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.localResidencia = localResidencia;
        this.numeroContacto = numeroContacto;
        this.responsavelHierarquico = responsavelHierarquico;
    }

    // Constructor for DTO
    public Colaborador(
                       final NomeCompleto nomeCompleto, final DataNascimento dataNascimento,
                       final LocalResidencia localResidencia, final NumeroContacto numeroContacto) {
        if (nomeCompleto == null || dataNascimento == null
                || localResidencia == null || numeroContacto == null) {
            throw new IllegalArgumentException();
        }

        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.localResidencia = localResidencia;
        this.numeroContacto = numeroContacto;
    }

    protected Colaborador() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public Long mecanographicNumber() {
        return identity();
    }

    @Override
    public Long identity() {
        return this.numeroMecanografico;
    }

    public Colaborador responsavelHierarquico() {
        return this.responsavelHierarquico;
    }

    public NomeCompleto nomeCompleto() {
        return this.nomeCompleto;
    }

    @Override
    public ColaboradorDTO toDTO() {
        return new ColaboradorDTO(this.systemUser.identity().toString(), this.identity(), this.nomeCompleto.toString(), this.dataNascimento.toString(), this.localResidencia.toString(), this.numeroContacto.toString());
    }
}
