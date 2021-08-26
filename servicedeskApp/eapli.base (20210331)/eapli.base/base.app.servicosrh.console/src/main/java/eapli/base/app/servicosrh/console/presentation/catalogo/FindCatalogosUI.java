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
package eapli.base.app.servicosrh.console.presentation.catalogo;

import eapli.base.catalogoservicemanagement.application.ListCatalogoController;
import eapli.base.catalogoservicemanagement.domain.Catalogo;
import eapli.base.servicomanagement.application.ListServicoService;
import eapli.base.servicomanagement.dto.ServicoDTO;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

import java.util.Optional;

/**
 *
 * @author Sara S. Silva 1181892@isep.ipp.pt
 */
@SuppressWarnings({ "squid:S106" })
public class FindCatalogosUI extends AbstractListUI<Catalogo> {


    private ListCatalogoController theController = new ListCatalogoController();
    private ListServicoService listServicoService = new ListServicoService();


    @Override
    protected Iterable<Catalogo> elements() { return theController.activeCatalogos();
    }

    @Override
    protected Visitor<Catalogo> elementPrinter() { return new CatalogoPrinter();
    }

    @Override
    protected String elementName() { return null;
    }

    @Override
    protected String listHeader() { return "Procurar catálogo";
    }

    @Override
    protected String emptyMessage() { return  "Nenhum catálogo disponível";
    }

    protected boolean doShow() {

        try {
            String titulo = Console.readLine("Insira o título do catálogo");

            Optional<Catalogo> resultado= theController.catalogoByTitulo(titulo);

            if(resultado.get() != null){
                System.out.println(resultado.toString());

                Iterable<ServicoDTO> listaServicos = listServicoService.servicosByCatalogo(resultado.get());

                listaServicos.forEach(e -> System.out.printf("- Servico: %s | Descrição: %s\n", e.codUnico, e.descBreve));

            }else{
                System.out.println("Titulo inexistente");
            }


        } catch (final IntegrityViolationException | ConcurrencyException e) {
            System.out.println("Titulo inexistente");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Procurar catálogo";
    }
}

