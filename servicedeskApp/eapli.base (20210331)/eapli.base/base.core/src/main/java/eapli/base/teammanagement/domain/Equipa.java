package eapli.base.teammanagement.domain;

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

import javax.persistence.*;

import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.NumeroMecanografico;
import eapli.base.teammanagement.dto.EquipaDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Entity
public class Equipa implements Serializable, AggregateRoot<Long>, DTOable<EquipaDTO> {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long identificador;

    private Acronimo acronimo;

    private Designacao designacao;

    private Cor corEquipa;

    @OneToMany
    @JoinTable(name = "equipa_colaboradores")
    private Set<Colaborador> colaboradores = new HashSet<>();

    @OneToMany
    @JoinTable(name = "equipa_responsaveis")
    private Set<Colaborador> responsaveis = new HashSet<>();

    @ManyToOne(optional = false)
    private EquipaType tipoEquipa;

    public Equipa(final Acronimo acronimo, final Designacao designacao, final Cor cor, final EquipaType tipo, final Set<Colaborador> listaResponsaveis) {
        if (acronimo == null || designacao == null || cor == null) {
            throw new IllegalArgumentException();
        }

        this.acronimo = acronimo;
        this.designacao = designacao;
        this.corEquipa = cor;
        this.tipoEquipa = tipo;

        listaResponsaveis.forEach(resp -> {
            responsaveis.add(resp);
        });
    }

    //Constructor for DTO
    public Equipa(final Acronimo acronimo, final Designacao designacao, final Cor cor, final EquipaType tipo) {
        if (acronimo == null || designacao == null || cor == null) {
            throw new IllegalArgumentException();
        }

        this.acronimo = acronimo;
        this.designacao = designacao;
        this.corEquipa = cor;
        this.tipoEquipa = tipo;

    }

    protected Equipa() {
        // for ORM only
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

    @Override
    public Long identity() {
        return this.identificador;
    }

    public Designacao designacao() {
        return this.designacao;
    }

    public Acronimo acronimo() {
        return this.acronimo;
    }

    public Cor cor() {
        return this.corEquipa;
    }

    public EquipaType tipoEquipa() {
        return this.tipoEquipa;
    }

    public Set<Colaborador> colaboradores() {
        return this.colaboradores;
    }

    public void addColaborador(Colaborador colab) {

        try {
            this.colaboradores.add(colab);
        } catch (NullPointerException nullPointerException) {
            System.out.println("As informações do Colaborador estão por preencher.");
        }

    }

    public void removeColaborador(Colaborador colab) {

        try {
            this.colaboradores.remove(colab);
        } catch (NullPointerException nullPointerException) {
            System.out.println("As informações do Colaborador estão por preencher.");
        }
    }

    public void addResponsavel(Colaborador responsavel) {

        try {
            this.responsaveis.add(responsavel);
        } catch (NullPointerException nullPointerException) {
            System.out.println("As informações do Responsavel estão por preencher.");
        }
    }

    public void removerResponsavel(Colaborador responsavel) {

        try {
            this.responsaveis.remove(responsavel);
        } catch (NullPointerException nullPointerException) {
            System.out.println("As informações do Responsavel estão por preencher.");
        }

    }

    @Override
    public String toString() {
        return "Equipa{" +
                "version=" + version +
                ", identificador=" + identificador +
                ", acronimo=" + acronimo +
                ", designacao=" + designacao +
                ", corEquipa=" + corEquipa +
                ", colaboradores=" + colaboradores +
                ", responsaveis=" + responsaveis.toString() +
                ", tipoEquipa=" + tipoEquipa.toString() +
                '}';
    }

    @Override
    public EquipaDTO toDTO() {
        return new EquipaDTO(this.identity(), this.acronimo.toString(), this.designacao().toString(), this.corEquipa.toString(), this.tipoEquipa.identity());
    }
}

