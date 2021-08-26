package base.server.motorfluxoatividades.fluxoatividades.manuais;

import base.server.motorfluxoatividades.fluxoatividades.utils.NiveisPrioridade;
import base.server.motorfluxoatividades.fluxoatividades.utils.SortPedidoByNiveisCriticidade;
import eapli.base.colaboradormanagement.application.ListColaboradorService;
import eapli.base.colaboradormanagement.domain.Colaborador;
import eapli.base.pedidomanagement.application.AssignarPedidoController;
import eapli.base.pedidomanagement.application.ListPedidoController;
import eapli.base.pedidomanagement.domain.Pedido;
import eapli.base.teammanagement.application.ListEquipaService;
import eapli.base.teammanagement.domain.Equipa;

import java.util.*;

public class GestorPedidosManuais extends Thread{

    private static List<Pedido> listaPedidos;
    private static List<Colaborador> listaColaboradores;
    private static ListPedidoController listPedidoController;
    private static ListColaboradorService listColaboradorService;
    private static ListEquipaService listEquipaService;
    private static NiveisPrioridade niveisPrioridade;
    private static AssignarPedidoController assignarPedidoController;

    // Por default implementamos o fifo
    // True - Algoritmo de Assigment
    // False - Algoritmo FIFO
    private static boolean tipoAlgoritmo = true;

    public GestorPedidosManuais() {
    }

    @Override
    public void run() {
        this.listaPedidos = new ArrayList<>();
        this.listaColaboradores = new ArrayList<>();
        this.listPedidoController = new ListPedidoController();
        this.listColaboradorService = new ListColaboradorService();
        this.niveisPrioridade = new NiveisPrioridade();
        this.assignarPedidoController = new AssignarPedidoController();
        this.listEquipaService = new ListEquipaService();

        int ciclo = 0;
        ListPedidoController controller = new ListPedidoController();
        ListColaboradorService service = new ListColaboradorService();

        while (true) {
            try {
                Thread.sleep(5000);
                System.out.println("Ciclo: " + ciclo);

                // Get pedidos manuais com status APROVADO
                listaPedidos = controller.allPedidosPendentesManuais();
                Collections.sort(listaPedidos, new SortPedidoByNiveisCriticidade());

                // Get all Colaboradores
                listaColaboradores = service.allColaboradoresAutomatico();

                if (tipoAlgoritmo) {
                    algoritmoAssignment();
                } else {
                    algortimoFIFO();
                }
                ciclo++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void algortimoFIFO() {
        while (listaPedidos.listIterator().hasNext()) {

            if (listaColaboradores.iterator().hasNext()) {
                assignarPedidoController.assignarPedidoAprovadoAutomatico(listaPedidos.listIterator().next(), listaColaboradores.iterator().next());
            }

            listaPedidos.remove(0);
            listaColaboradores.remove(0);
        }
    }

    private void algoritmoAssignment() {
        // Iterar sobre todos os pedidos que estão pendentes e por assignar
        for (Pedido p : listaPedidos) {
            HashMap<Colaborador, Double> mapColaboradores = new HashMap<>();
            // Listar as equipas executoras de cada pedido
            List<Equipa> listEquipasExecutoras = listEquipaService.findAllEquipaByCatalogoAutomatico(p.servico().catalogo().identificador());
            // Obter a lista de colaboradores de cada equipa
            for (Equipa e : listEquipasExecutoras) {
                // Calcular o tempo médio de ocupacao de cada colaborador
                for (Colaborador c : e.colaboradores()) {
                    List<Pedido> pedidosColaborador = listPedidoController.allPedidosPendentesAssignedToColaboradorAutomatico(c.user().username());
                    double tempoMedioOcupacao = calcularOcupacaoColaborador(pedidosColaborador);
                    mapColaboradores.put(c, tempoMedioOcupacao);
                }
            }
            // Ordenar o HashMap por value para atribuir as tarefas aos colaboradores com menos tempo de ocupacao
            HashMap<Colaborador, Double> mapColaboradoresOrdenado = sortByValue(mapColaboradores);
            System.out.println("Assignar pedidos");
            this.assignarPedidoController.assignarPedidoAprovadoAutomatico(p, mapColaboradoresOrdenado.entrySet().iterator().next().getKey());
        }
    }

    // Funcao para calcular a ocupacao do colaborador
    private double calcularOcupacaoColaborador(List<Pedido> pedidos) {
        double totalTempo = 0;

        for (Pedido p : pedidos) {
            totalTempo = totalTempo + Integer.parseInt(p.servico().nivelCrtiticidade().tempoMedioResolucao().toString());
        }
        return totalTempo;
    }

    // Função para ordenar o hashmap por value
    private static HashMap<Colaborador, Double> sortByValue(HashMap<Colaborador, Double> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Colaborador, Double>> list = new LinkedList<Map.Entry<Colaborador, Double>>(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Colaborador, Double>>() {
            public int compare(Map.Entry<Colaborador, Double> o1,
                               Map.Entry<Colaborador, Double> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Colaborador, Double> temp = new LinkedHashMap<Colaborador, Double>();
        for (Map.Entry<Colaborador, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

}
