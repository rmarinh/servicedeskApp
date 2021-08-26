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
package eapli.base.servicomanagement;

import eapli.base.catalogoservicemanagement.domain.*;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.colaboradormanagement.domain.ColaboradorBuilder;
import eapli.base.servicomanagement.domain.servico.PalavraChave;
import eapli.base.servicomanagement.domain.servico.Servico;
import eapli.base.servicomanagement.domain.servico.ServicoBuilder;
import eapli.base.slaservicemanagement.domain.*;
import eapli.base.teammanagement.domain.*;
import eapli.base.teammanagement.domain.Cor;
import eapli.framework.infrastructure.authz.domain.model.NilPasswordPolicy;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.time.util.Calendars;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */
public class ServicoTest {


    public static Servico dummyServicoIncompleto(){

        SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        userBuilder.with("userName", "password", "firstName", "lastName", "email@isep.ipp.pt");
        SystemUser newUser = userBuilder.build();

        ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();
        colaboradorBuilder.withUsername("username")
                .withName("firstName", "lastName")
                .withEmail("email@isep.pt")
                .withDataNascimento("10/10/1990")
                .withLocalResidencia("localResidencia")
                .withNumeroContacto("numeroContacto")
                .createdOn(Calendars.now())
                .withPassword("PassA1")
                .withSystemUser(newUser)
                .withResponsavelHierarquico(null);
        Colaborador colaborador = colaboradorBuilder.build();

        // Criação de Equipa

        Acronimo ACRONIMO_EQUIPA = Acronimo.valueOf("Equipa 1");
        Designacao DESIGNACAO_EQUIPA = Designacao.valueOf("Equipa Teste 1");
        Designacao DESIGNACAO_TIPO_EQUIPA = Designacao.valueOf("Tipo de Equipa 1");

        Cor AZUL = Cor.valueOf("Azul");

        EquipaType TIPO_EQUIPA_UM = new EquipaType(DESIGNACAO_TIPO_EQUIPA, AZUL);

        Equipa EQUIPA_UM = new Equipa(ACRONIMO_EQUIPA, DESIGNACAO_EQUIPA, AZUL, TIPO_EQUIPA_UM);
        Equipa EQUIPA_DOIS = new Equipa(ACRONIMO_EQUIPA, DESIGNACAO_EQUIPA, AZUL, TIPO_EQUIPA_UM);


        // Niveis de Criticidade

        NiveisCriticidade sla = new NiveisCriticidade(1L, 1L, new Etiqueta("Etiqueta"), new EscalaNumerica("10"),
                eapli.base.slaservicemanagement.domain.Cor.valueOf("AZUL"),
                new TempoMedioAprovacao("10"), new TempoMaximoAprovacao("10"), new TempoMaximoResolucao("10"), new TempoMedioResolucao("10"));

        // Criação de Catalogo

        Titulo TITULO = Titulo.valueOf("Titulo Catalogo");
        eapli.base.catalogoservicemanagement.domain.DescricaoBreve DESC_BREVE_EQUIPA = DescricaoBreve.valueOf("Descricao Breve");
        DescricaoCompleta DESC_COMP_EQUIPA = DescricaoCompleta.valueOf("Descricao Completa");
        Icone ICONE = Icone.valueOf("Icone");

        Set<Equipa> criterios = new HashSet<>();
        criterios.add(EQUIPA_UM);
        criterios.add(EQUIPA_DOIS);

        Set<Equipa> equipasExecutoras = new HashSet<>();
        criterios.add(EQUIPA_UM);
        criterios.add(EQUIPA_DOIS);

        Catalogo catalogo = new Catalogo(TITULO,
                DESC_BREVE_EQUIPA,
                DESC_COMP_EQUIPA,
                ICONE,
                colaborador,
                sla,
                criterios,
                equipasExecutoras);

        // Criação dos Serviço

        CodigoUnico COD_SERV_UM = CodigoUnico.valueOf("SRV01");

        Set<PalavraChave> keywords = new HashSet<>();

        keywords.add(new PalavraChave("keyword"));

        ServicoBuilder servicoBuilder = new ServicoBuilder();
        servicoBuilder.withCodigoUnico(COD_SERV_UM.toString());
        servicoBuilder.withCatalogo(catalogo);
        servicoBuilder.withDescricaoBreve(eapli.base.servicomanagement.domain.servico.DescricaoBreve.valueOf("Servico Um"));
        servicoBuilder.withDescricaoCompleta(eapli.base.servicomanagement.domain.servico.DescricaoCompleta.valueOf("Descrição Completa do Serviço Um"));
        servicoBuilder.withStatusIncompleted();
        servicoBuilder.withPalavrasChaves(keywords);

        Servico servico = servicoBuilder.build();

        return servico;
    }

    public static Servico dummyServicoCompleto(){

        SystemUserBuilder userBuilder = new SystemUserBuilder(new NilPasswordPolicy(), new PlainTextEncoder());
        userBuilder.with("userName", "password", "firstName", "lastName", "email@isep.ipp.pt");
        SystemUser newUser = userBuilder.build();

        ColaboradorBuilder colaboradorBuilder = new ColaboradorBuilder();
        colaboradorBuilder.withUsername("username")
                .withName("firstName", "lastName")
                .withEmail("email@isep.pt")
                .withDataNascimento("10/10/1990")
                .withLocalResidencia("localResidencia")
                .withNumeroContacto("numeroContacto")
                .createdOn(Calendars.now())
                .withPassword("PassA1")
                .withSystemUser(newUser)
                .withResponsavelHierarquico(null);
        Colaborador colaborador = colaboradorBuilder.build();

        // Criação de Equipa

        Acronimo ACRONIMO_EQUIPA = Acronimo.valueOf("Equipa 1");
        Designacao DESIGNACAO_EQUIPA = Designacao.valueOf("Equipa Teste 1");
        Designacao DESIGNACAO_TIPO_EQUIPA = Designacao.valueOf("Tipo de Equipa 1");

        Cor AZUL = Cor.valueOf("Azul");

        EquipaType TIPO_EQUIPA_UM = new EquipaType(DESIGNACAO_TIPO_EQUIPA, AZUL);

        Equipa EQUIPA_UM = new Equipa(ACRONIMO_EQUIPA, DESIGNACAO_EQUIPA, AZUL, TIPO_EQUIPA_UM);
        Equipa EQUIPA_DOIS = new Equipa(ACRONIMO_EQUIPA, DESIGNACAO_EQUIPA, AZUL, TIPO_EQUIPA_UM);


        // Niveis de Criticidade

        NiveisCriticidade sla = new NiveisCriticidade(1L, 1L, new Etiqueta("Etiqueta"), new EscalaNumerica("10"),
                eapli.base.slaservicemanagement.domain.Cor.valueOf("AZUL"),
                new TempoMedioAprovacao("10"), new TempoMaximoAprovacao("10"), new TempoMaximoResolucao("10"), new TempoMedioResolucao("10"));

        // Criação de Catalogo

        Titulo TITULO = Titulo.valueOf("Titulo Catalogo");
        eapli.base.catalogoservicemanagement.domain.DescricaoBreve DESC_BREVE_EQUIPA = DescricaoBreve.valueOf("Descricao Breve");
        DescricaoCompleta DESC_COMP_EQUIPA = DescricaoCompleta.valueOf("Descricao Completa");
        Icone ICONE = Icone.valueOf("Icone");

        Set<Equipa> criterios = new HashSet<>();
        criterios.add(EQUIPA_UM);
        criterios.add(EQUIPA_DOIS);

        Set<Equipa> equipasExecutoras = new HashSet<>();
        criterios.add(EQUIPA_UM);
        criterios.add(EQUIPA_DOIS);

        Catalogo catalogo = new Catalogo(TITULO,
                DESC_BREVE_EQUIPA,
                DESC_COMP_EQUIPA,
                ICONE,
                colaborador,
                sla,
                criterios,
                equipasExecutoras);

        // Criação dos Serviço

        CodigoUnico COD_SERV_UM = CodigoUnico.valueOf("SRV01");

        Set<PalavraChave> keywords = new HashSet<>();

        keywords.add(new PalavraChave("keyword"));

        ServicoBuilder servicoBuilder = new ServicoBuilder();
        servicoBuilder.withCodigoUnico(COD_SERV_UM.toString());
        servicoBuilder.withCatalogo(catalogo);
        servicoBuilder.withDescricaoBreve(eapli.base.servicomanagement.domain.servico.DescricaoBreve.valueOf("Servico Um"));
        servicoBuilder.withDescricaoCompleta(eapli.base.servicomanagement.domain.servico.DescricaoCompleta.valueOf("Descrição Completa do Serviço Um"));
        servicoBuilder.withStatusIncompleted();
        servicoBuilder.withPalavrasChaves(keywords);

        Servico servico = servicoBuilder.build();

        return servico;
    }

    private static final Servico servicoIncompleto = dummyServicoIncompleto();
    private static final Servico servicoCompleta = dummyServicoCompleto();

    /**
     * Test of Status, of class Servico.
     */
    @Test
    public void ensureIncompleteStatus() {
        assertTrue(servicoIncompleto.isIncompleto());
    }

    /**
     * Test of Codigo Unico, of class Servico.
     */
    @Test
    public void ensureServicoHasCodigoUnico() {
        assertTrue(servicoIncompleto.codigoUnico() != null);
    }

    /**
     * Test of Catalogo, of class Servico.
     */
    @Test
    public void ensureServicoHasCatalogo() {
        assertTrue(servicoIncompleto.catalogo() != null);
    }

    /**
     * Test of Palavras Chaves, of class Servico.
     */
    @Test
    public void ensureServicoHasPalavrasChave() {
        assertTrue(servicoIncompleto.palavraChaves() != null);
    }

    /**
     * Test of Descrição Breve, of class Servico.
     */
    @Test
    public void ensureServicoHasDescricaoBreve() {
        assertTrue(servicoIncompleto.descricaoBreve() != null);
    }

    /**
     * Test of Descrição Completo, of class Servico.
     */
    @Test
    public void ensureServicoHasDescricaoCompleto() {
        assertTrue(servicoIncompleto.descricaoCompleta() != null);
    }
}
