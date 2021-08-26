# Projeto Integrador da LEI-ISEP 2020-21 - 4º Semestre

### Sprint master: 1181895 ###

## 1. Sprint's backlog ##

O objetivo é a implementação de um  protocolo  de  comunicação de  aplicação denominado Service Desk Protocol2021 (SDP 2021). Para isso é implementar mos seguintes pontos:

- Transação de Dados
- Aplicação de serviços e RH
- Aplicação Portal dos Utilizadores
- Executor de Tarefas Automáticas
- Motor de Fluxos de Atividades


# 2. Subtasks assessment #

- 1171602 e 1140858: Motor Fluxo de Atividades
  * Aplicação: base.app.fluxoatividades
  * Totally implemented with issues 
  	- The server cannot get the information about the user session; 
  	- Split the HTTP in threads; 
  	- Comunication throuthout packages;

- 1181892: Executor Tarefas Automáticas
  * Aplicação: base.app.tarefasautomaticas
  * Totally implemented with issues 
  	- Currently we are simulating the execution of each request and not using scripts;

- 1181882: Portal dos Utilizadores
  * Aplicação: base.app.user.console
  * Totally implemented with issues 
  	- Currenty we cannot have access to the information of the user session. On the dashboard we are displaying all the tickets available on the database;

- 1181895: Aplicação de Serviços e RH
  * Aplicação: base.app.servicosrh.console
  * Totally implemented with issues 
  	- Currenty we cannot have access to the information of the user session. On the dashboard we are displaying all the tickets available on the database;


# 3. Data transmission #
	
| Code |  Significado/Utilização  |
| :--- | :--- |
| 0   | Teste-Pedido de teste sem qualquer efeito para além da devolução de uma resposta com código 2. Este pedido não transporta dados.  |
| 1   | Fim-Pedido de fim de ligação. O servidor deve devolver uma resposta com código 2, após o que ambas as aplicações devem fechara ligação TCP |
| 2   | Entendido-Resposta  vazia  (não  transporta  dados)  que  acusa  a  receção  de  um  pedido.  É enviada em resposta a pedidos com código 0 e código 1, mas poderá ser usada em outros contextos.  |
| 11  | Execução de aprovações automáticas |
| 21  | Execução de realizações automáticas |
| 255 | Segmento-identifica  os  dados  transportados como  sendo  uma  parte  de  um  conjunto  de dados mais extenso. Este código é usado para transferir volumes de dados superiores a 255 bytes. Nesse cenário um pedido ou uma resposta pode ser constituído por uma sequência de mensagens com código 255 finalizada por uma mensagem contendo um código diferente de 255. |



