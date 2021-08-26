us4071_algoritmos que distribuam a realização de tarefas automáticas pelas diversas instâncias do Executor de Tarefas Automática
=======================================

# 1. Requisitos

**US4071** Como Gestor de Projeto, eu pretendo que seja desenvolvido e integrado no Motor de Fluxos de Atividades  algoritmos que distribuam a realização de tarefas automáticas pelas diversas instâncias do Executor de Tarefas Automáticas existentes na infraestrutura instalada.

A interpretação feita deste caso de uso, foi no sentido da criação de dois algoritmos que permitam a distribuição das tarefas automáticas pelo Executor de Tarefas. O algoritmo é adotado pelo sistema e é definido por configuração.

Os dois algoritmos baseiam-se em:

- baseado em First Came First Served  (entre instâncias do Executor de Tarefas); e
- outro que tenha em consideração a disponibilidade das respetivas instâncias e a carga atual das mesmas.



# 3. Design

## 3.1. Realização da Funcionalidade


### FIFO ###

Localização: ExecutorTarefasAutomaticas>ExecutorTarefasAutomaticasDeamon


```
ExecutorService executor = Executors.newFixedThreadPool(5);

```

O ExecutorService é uma classe nativa de Java que permite uma gestão de threads dor forma dinâmica ou estática. De uma forma estática foi definida pela equipa 5 threads.
De forma a tornar a gestão de threads dinâmicas teríamos que instanciar o seguinte:


```
ExecutorService executor = Executors.newCachedThreadPool();

```

Esta classe permite implementar uma estratégia do tipo FIFO para executar as tarefas entre instancias do servidor.



### OrdenacaoPorPrioridade ###

Localização: MotorFluxosAtividades>GestorPedidosAutomaticos

Para desenvolver este algoritmo procedemos à criação de uma matriz cujas entradas estão numeradas de 1 a 30, sendo 30 representante da prioridade máxima.


/*              0       1       2       3      4     5
    Niveis	0-20%  20-40%  40-60%  60-80% 80-100% 100+%
4    5	       9	     19	     20 	 23	    25	   30
3    4	       8	     14      18	   21	    24	   29
2    3	       4	     10	     15	   17	    22	   28
1    2	       2	      5	     11	   12	    16	   27
0    1	       1	      3	      6	    7 	  13	   26
 */

Prosseguimos com a passagem para uma lista as tarefas automáticas presente na BD à qual aplicamos um <em>Comparator</em> que reflete o nosso algoritmo.
No algoritmo tem em conta o tempo máximo (referido no SLA) de cada pedido e o tempo decorrido. A percentagem do tempo restante permite-nos na matriz, identificar a coluna. Enquanto que o nível de criticidade associado ao serviço permite-nos identificar a linha. A posição da coluna e da linha dá-nos a prioridade do pedido.
Finalmente os pedidos e respetivo grau de prioridade são colocados numa lista e ordenados de forma decrescente.  


## 3.2. Pseudo-codigo


### FIFO ###

*N/A*


### OrdenacaoPorPrioridade ###


PROCEDIMENTO OrdenacaoPorPrioridade


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


FIMPROCEDIMENTO OrdenacaoPorPrioridade




## 3.3. Padrões Aplicados

*N/A*

## 3.4. Testes

*N/A*

# 4. Implementação

*N/A*

# 5. Integração/Demonstração

*N/A*

# 6. Observações

*N/A*
