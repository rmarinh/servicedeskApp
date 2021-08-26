package base.server.motorfluxoatividades.fluxoatividades.utils;

import eapli.base.pedidomanagement.domain.Pedido;

import java.util.Comparator;

public class SortPedidoByNiveisCriticidade implements Comparator<Pedido> {

    private static NiveisPrioridade niveisPrioridade = new NiveisPrioridade();

    @Override
    public int compare(Pedido p1, Pedido p2) {
        Integer fatorPedido1 = calcularFatorPedido(p1);
        Integer fatorPedido2 = calcularFatorPedido(p2);

        return fatorPedido2.compareTo(fatorPedido1);
    }

    private int calcularFatorPedido(Pedido p){

        int tempoDecorrido = p.tempoDecorrido().intValue();
        int tempoMaximo =  Integer.parseInt(p.servico().nivelCrtiticidade().tempoMaximoAprovacao().toString());
        double tempoRestante = 0;
        double diferenca = 0;

        if (tempoDecorrido > tempoMaximo) {
            tempoRestante = (tempoDecorrido*100) / tempoMaximo;
        }else{
            diferenca = tempoMaximo-tempoDecorrido;
            tempoRestante = diferenca / tempoMaximo;
            tempoRestante = tempoRestante * 100;
        }

        tempoRestante= Math.round(tempoRestante);

        int nivelCriticidade = Integer.parseInt(p.servico().nivelCrtiticidade().escalaNumerica().toString());


        return niveisPrioridade.getPriority((int)tempoRestante, nivelCriticidade);
    }
}
