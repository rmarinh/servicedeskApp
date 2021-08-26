us4072_ algoritmos que assignem automáticamente tarefas a colaboradores
=======================================

# 1. Requisitos

**US4072** Como Gestor de Projeto, eu pretendo que seja desenvolvido e integrado no Motor de Fluxos de Atividades algoritmos que assignem automaticamente tarefas a colaboradores de forma a evitar que essas tarefas tenham que ser reivindicadas pelos mesmos.


A interpretação feita deste caso de uso, foi no sentido da criação de dois algoritmos que permitam a distribuição das tarefas manuais aos colaboradores creditados para realizar as tarefas. O algoritmo é adotado pelo sistema e é definido por configuração.

Os dois algoritmos baseiam-se em:

- baseado em First Came First Served (entre colaboradores de cada equipa); e
- outro que, no âmbito de cada equipa, considere a quantidade de tarefas pendentes dos colaboradores e o tempo médio de execução de cada uma das tarefas (pré-definido em cada tarefa).



# 3. Design

## 3.1. Realização da Funcionalidade


### FIFO ###

Localização:  motorDeFluxosAtividades > fluxoatividades > GestorPedidosManuais

O algoritmo FIFO (First in first out) é um processo cujo primeiro elemento a ser inserido, será o primeiro a ser retirado, ou seja, adiciona-se itens no fim da fila e remove-se do início.

Desenvolvemos o algoritmo tendo por base 2 listas, uma de pedidos manuais e outra de colaboradores tendo como finalidade a atribuição da primeira tarefa disponível ao primeiro colaborador disponível nas respetivas listas.




### OrdenacaoPorOcupacao ###

Localização:  motorDeFluxosAtividades > fluxoatividades > GestorPedidosManuais


O primeiro passo no desenvolvimento do algoritmo foi obter a lista de pedidos manuais pendentes. A cada um dos pedidos presente na lista identificamos as equipas executoras do pedido. De seguida, iteramos cada um dos colaboradores dessa(s) equipas e calculamos o seu tempo de ocupação, isto é, somamos os tempos médios de resolução de cada uma das tarefas que tem assignado.
A informação sobre o colaborador e o seu tempo médio de ocupação são registados e colocados num mapa que é, posteriormente ordenado de forma crescente.
Uma vez a análise sobre os colaboradores efetuada, é assignada a tarefa ao colaborador com menor taxa de ocupação.



## 3.2. Pseudo-codigo


### FIFO ###

PROCEDIMENTO algoritmo FIFO

      listaColaboradores <- listaDeColaboradores;
      listaPedidos <- listaPedidosManuaisPorAssignar;

      WHILE
        listaPedidos.hasNext() DO

        SE
            listaPedidos.hasNext()
        ENTAO
            assignarPedido
        FIMSE

        listaColaboradores.remove(index 0)
        listaPedidos.remove(index 0)
      END WHILE

FIMPROCEDIMENTO algoritmo FIFO



### OrdenacaoPorOcupacao ###


PROCEDIMENTO algoritmo OrdenacaoPorOcupacao


      tempoDecorrido <- dataAtual-dataSubmissão;
      tempoMaximo <- tempoMaximoAprovacaoPedido;


      SE
         tempoDecorrido > tempoMaximo ENTAO
         tempoRestante <- (tempoDecorrido*100) / tempoMaximo
      SENAO
        diferenca <- tempoMaximo-tempoDecorrido;
        tempoRestante <- diferenca / tempoMaximo;
        tempoRestante <- tempoRestante * 100;  

      FIMSE


      nivelCriticidade <- nivelCriticidadeDoPedido;

      RETORNO prioridadeDoPedido

FIMPROCEDIMENTO OrdenacaoPorOcupacao




## 3.3. Padrões Aplicados

*N/A*

## 3.4. Testes

*N/A*

# 4. Implementação

## FIFO ##

```
private void algortimoFIFO() {

    while (listaPedidos.listIterator().hasNext()) {

        if (listaColaboradores.iterator().hasNext()) {
            assignarPedidoController.assignarPedidoAprovadoAutomatico(listaPedidos.listIterator().next(), listaColaboradores.iterator().next());
        }

        listaPedidos.remove(0);
        listaColaboradores.remove(0);
    }
}
```

## OrdenacaoPorOcupacao ##

```
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
```

# 5. Integração/Demonstração

*N/A*

# 6. Observações

*N/A*
