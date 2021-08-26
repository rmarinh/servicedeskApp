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
package eapli.base.app.servicosrh.console.presentation.pedidos;

import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.pedidomanagement.dto.PedidoDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author losa
 */
@SuppressWarnings({ "squid:S106" })
public class ListPedidosConcluidosCumprimentoUI extends AbstractUI {
    private ListPedidoController pedidoController = new ListPedidoController();

    @Override
    protected boolean doShow() {

        System.out.println("\nPor favor, preencha o intervalo que deseja:\n");

        final String dataFrom = Console.readLine("Data Desde: (Ex: AAAA-MM-DD)");
        final String dataTo = Console.readLine("Até: (Ex: AAAA-MM-DD)");

        List<PedidoDTO> listaPedidos = pedidoController.allPedidosConcluidoIntervalo(dataFrom, dataTo);
        List<PedidoDTO> listaPedidosCumprimento = new ArrayList<>();

        if(listaPedidos.isEmpty()){
            System.out.println("Não existem pedidos concluídos!");
            return false;
        }

        for(PedidoDTO p : listaPedidos){
            Pedido pedido = pedidoController.findPedidoById(p.pk).get();
            if(!pedido.cumprimentoSLA()){
                listaPedidosCumprimento.add(p);
            }
        }

        if(listaPedidosCumprimento.isEmpty()){
            System.out.println("Parabéns! Não existem pedidos concluídos em incumprimento do SLA!");
            return false;
        }

        System.out.format("#  %-20s%-40s%-40s", "Identificador", "Status", "Tempo Decorrido");

        for(PedidoDTO p : listaPedidosCumprimento){
            Pedido pedido = pedidoController.findPedidoById(p.pk).get();
            System.out.format("#  %-20s%-40s%-40s", p.pk, p.status, pedido.tempoDecorrido());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Lista Pedidos Concluídos em Incumprimento SLA";
    }
}
