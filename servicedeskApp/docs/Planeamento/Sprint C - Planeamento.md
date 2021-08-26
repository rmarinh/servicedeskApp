# Projeto Integrador da LEI-ISEP 2020-21 - 4º Semestre


# 1. Constituição do Grupo de Trabalho

O grupo de trabalho é constituído pelo estudantes identificados na tabela seguinte.

| **Aluno Nr.**	   | **Nome do Aluno**			    	|
|--------------|--------------------------------|
| 1181895  	   | Fábio Silva                	|
| 1181892      | Sarah Silva			        |
| 1181882      | Rafael Soares			        |
| 1171602      | Rui Marinho (Scrum Master)		|
| 1140858      | Carlos Moutinho		        |



# 2. Decisões técnicas e coordenação #

Nesta secção, serão mencionadas todas as decisões técnicas durante a reunião de planeamento. Principalmente todas as decisões técnicas que terão impacto nas tarefas dos elementos da equipa.

## Base de Dados ##

A base de dados a utilizar é o **H2**.

## Ambiente de Desenvolvimento ##

O IDE a utilizar para o desenvolvimento do projeto **INTELLiJ IDEA 2020.3.3**.

## Framework do Projeto ##

A equipa decidiu para este desenvolvimento a utilização da framework **Eapli Base**.




# 3. Calendário de Sprints #

| **Sprint** | **Início** | **Fim** |
|-----|-----------|-----------|
| A | 08/03/21 | 18/04/21 |
| B | 19/04/21 | 09/05/21 |
| C | 10/05/21 | 30/05/21 |
| D | 31/05/21 | 20/06/21 |




# 4. Distribuição de Funcionalidades #

A distribuição de requisitos/funcionalidades ao longo do período de desenvolvimento do projeto pelos elementos do grupo de trabalho realizou-se conforme descrito na tabela seguinte.

| **Aluno Nr.**	| **Sprint** | **Funcionalidade** |
|------------|----------|----------|
| 1181892 | **B**  |  us3001_issue9_PesquisarCatalogosDeServicos |
|  | **B**  |  us9001_issue10_Apresentacao |
|  | **C**  |  us2105_issue31_Bootstrap com níveis de criticidade e atribuição a catálogos |
|  | **C**  |  us5001_issue27_desenvolvido o Executor de Tarefas Automáticas |
| 1181882 | **B**  |  us2053_issue08_AssociarRemoverColaboradorEquipa |
|  | **B**  |  us2051_issue06_RegistarUmColaborador |
|  | **C**  |  us3021_issue23_consultar as tarefas pendentes assignadas |
|  | **C**  |  us1006_issue19_componente representativa de uma tarefa automática |
|  | **C**  |  us3022_issue24_consultar as tarefas pendentes por reivindicar|
| 1181895 | **B**  |  us2052_issue07_RegistarEquipa |
|  | **B**  |  us2054_issue14_RegistarTipoEquipa |
|  | **C**  |  us2011_issue29_estado atual do motor de fluxos de atividades |
|  | **C**  |  us3011_issue22_dashboard web |
|  | **C**  |  us4002_issue26_Motor de Fluxo de Atividades disponibilize os dados necessários às aplicações |
| 1171602 | **B**  |  us2002_issue05_CriarServico |
|  | **B**  |  us2103_issue12_BootstrapOrganizacaoEquipa |
|  | **B**  |  us1003_issue03_ConfiguraçãoEstrutura |
|  | **C**  |  us2003_issue20_continuar e completar a especificação em curso de um serviço |
|  | **C**  |  us3002_issue21_serviço do catálogo de serviços disponibilizados |
|  | **C**  |  us2104_issue28_Bootstrap com alguma informação relativa a serviços completos |
| 1140858 | **B**  |  us2001_issue04_CriarCatalogo |
|  | **B**  |  us2101_issue11_BootstrapCatalogoEServico |
|  | **C**  |  us2012_issue30_atribuição do nível de criticidade a um catálogo |
|  | **C**  |  us4001_issue25_Motor de Fluxo de Atividade |
|  | **C**  |  us1005_issue18_componente representativa de uma tarefa manual |
| 1170963 | **C**  |  us1004_issue17_Desenvolvimento de uma linguagem/gramatica de suporte geral ao sistema |



# 5. Dependências de User Stories #

| **Sprint** | **US** | **Dependências** | **Comentários** |
|-----|-----------|-----------|---------------------|
| B | 2052 | 2054 | Para criar uma equipa precisa de criar primeiro um tipo de equipa. |
| B | 2053 | 2051 | Para associar ou remover colaboradores numa equipa precisamos de ter colaboradores no sistema. |
| B | 3001 | 2002 | Para um utilizador pesquisar catálogos eles têm que existir no sistema. |
| B | 2101 | 2001 | A Factory da entidade Catalogo necessita de estar implementada para que estes sejam inicializados no sistema. |
| B | 2101 | 2051/2052/2053/2054 | A Factory das respetivas entidades necessitam de estar implementadas para que o sistema seja inicializado com a informação completa. |
| B | 9001 | Todas | É necessário desenvolvimento para realizar a apresentação. |
| B | Todas | 1003 | Todas dependem do repositórios. |
| C | 3021 | 2003 | Para poder implementar essa US a especificação de um serviço deve ser efetuado |
| C | 3022 | 2003 | Para poder implementar essa US a especificação de um serviço deve ser efetuado |
| C | 4001 | 2003 | Para poder implementar essa US a especificação de um serviço deve ser efetuado |
| C | 4002 | 4001 | A implementação de um Motor de fluxos é necessária à para a implementação dessa US |
| C | 5001 | 4001 | A implementação de um Motor de fluxos é necessária à para a implementação dessa US |
| C | 2011 | 4001 | A implementação de um Motor de fluxos é necessária à para a implementação dessa US |



# 6. Protocolos de Comunicação #

## 6.1. RCOMP Sprint's backlog ##

Neste sprint, cada elemento do grupo, ficará responsável pelo desenvolvimento de uma aplicação de software, do projeto integrador de LAPR garantindo que todas as ligações  são capazes de funcionar juntas, como um sistema único e integrado. O sistema é composto por diversas aplicações de redes, especificamente, Serviços e RH, Portal dos Utilizadores, Executor de Tarefas Automáticas e Motor de Fluxo de Atividades.

| Aplicação  |  Descrição |
|-------|:--|
| Serviços e RH | Opera como aplicação cliente SDP2021 para obter o estado atual do Motor de Fluxos de Atividades, que neste contexto exerce o papel de aplicação servidora SDP2021. |
| Portal dos Utilizadores | Opera como aplicação cliente SDP2021 para obter do Motor de Fluxos de Atividades os dados necessários para o dashboard do utilizador. Neste contexto a aplicação Motor de Fluxos de Atividades
exerce o papel de aplicação servidora SDP2021. |
| Executor de Tarefas Automáticas | Opera como aplicação servidora SDP2021 permitindo ao Motor de Fluxos de Atividades desencadear a execução de tarefas automáticas. Neste contexto a aplicação Motor de Fluxos de Atividades exerce o papel de aplicação cliente SDP2021. |
| Motor de Fluxos de Atividades | Opera como aplicação servidora SDP2021 permitindo às aplicações Aplicação de Serviços e RH e Aplicação Portal dos Utilizadores obterem os dados necessários. Opera como aplicação cliente SDP2021 para desencadear a execução de tarefas automáticas em servidores remotos que disponibilizam o serviço Executor de Tarefas Automáticas. |


O objetivo é a implementação de um  protocolo  de  comunicação de  aplicação denominado Service Desk  Protocol2021  (SDP 2021). Para isso é implementar mos seguintes pontos:
- Transação de Dados
- Aplicação de serviços e RH
- Aplicação Portal dos Utilizadores
- Executor de Tarefas Automáticas
- Motor de Fluxos de Atividades


## 6.2. Decisões técnicas e coordenação ##

No sprint que se segue, requer a utilização de um protocolo de comunicação de aplicação, denominado Service Desk Protocol 2021 (SDP 2021), para interação entre as diversas aplicações do sistema a desenvolver. O protocolo descrito tem por objetivo permitir genericamente a transação de dados entre as aplicações preconizadas no sistema.

## 6.3. Estrutura da aplicação ##

A aplicação divide-se em 4 packages distintos:

1. base.app.servicosrh.console (Aplicação de Serviços e RH)
2. base.app.user.console (Portal dos Utilizadores)
3. base.app.fluxoatividades (Motor Fluxo de Atividades)
4. base.app.tarefasautomaticas (Executor Tarefas Automáticas)

![Aplicacoes](Aplicacoes.jpeg)


## 6.4. Tarefas dos Elementos do Grupo ##

  * 1171602: Motor Fluxo de Atividades
  * 1140858: Motor Fluxo de Atividades
  * 1181892: Executor Tarefas Automáticas
  * 1181882: Portal dos Utilizadores
  * 1181895: Aplicação de Serviços e RH



## 6.5. Descrição do Protocolo de Comunicação ##

### 6.5.1. Transação de dados

A transação de dados são realizadas através de uma ligação Transmission Control Protocol (TCP).
De acordo com este modelo cabe:
- à aplicação cliente tomar a iniciativa de solicitar o estabelecimento da ligação TCP com a aplicação servidora;
- à aplicação servidora aceitar pedidos de ligação TCP no porto número 32507.

Depois de estabelecida a ligação TCP cabe:
- à aplicação cliente manter a iniciativa da comunicação, sendo a única que pode enviar mensagens de pedido;
- à aplicação servidora enviar mensagens de resposta aos pedidos recebidos

Cada pedido/resposta  consiste no envio de uma sequencia de butes segundo o seguinte formato:
[Versão, Código, Numero de bytes, Dados]

> Versão: Versão do protocolo SDP2021.

> Código: Código  que  identifica  o  tipo  de  pedido  ou  o  tipo  de  resposta. Este campo varia de 0 a 255.

> Numero de bytes: Número de bytes transportados no campo “Dados”.

> Dados: Dados   para   serem   manuseados   pelas   aplicações   finais.


| Code |  Significado/Utilização  |
| :--- | :--- |
| 0   | Teste-Pedido de teste sem qualquer efeito para além da devolução de uma resposta com código 2. Este pedido não transporta dados.  |
| 1   | Fim-Pedido de fim de ligação. O servidor deve devolver uma resposta com código 2, após o que ambas as aplicações devem fechara ligação TCP |
| 2   | Entendido-Resposta  vazia  (não  transporta  dados)  que  acusa  a  receção  de  um  pedido.  É enviada em resposta a pedidos com código 0 e código 1, mas poderá ser usada em outros contextos.  |
| 11  | Execução de aprovações automáticas |
| 21  | Execução de realizações automáticas |
| 255 | Segmento-identifica  os  dados  transportados como  sendo  uma  parte  de  um  conjunto  de dados mais extenso. Este código é usado para transferir volumes de dados superiores a 255 bytes. Nesse cenário um pedido ou uma resposta pode ser constituído por uma sequência de mensagens com código 255 finalizada por uma mensagem contendo um código diferente de 255. |




### 6.5.2. Aplicação de Serviços e RH

Opera como aplicação cliente SDP2021
Permite obter  o estado atual do Motor de Fluxos de Atividades

As  ligações  TCP  devem  persistir  e  ser  utilizadas  para  sucessivas  transações  enquanto  a  aplicação cliente estiver em execução.
Caso a ligação TCP entre o cliente e o servidor se tornar inoperante, cabe ao  cliente  estabelecer  de  forma  automática  e  sem  intervenção  humana  uma  nova  ligação  com  o mesmo servidor

### 6.5.3. Aplicação Portal dos Utilizadores

Opera  como aplicação  cliente  SDP2021
Permite obter  os  dados necessários para o dashboard do utilizador.

As  ligações  TCP  devem  persistir  e  ser  utilizadas  para  sucessivas transações  enquanto  a  aplicação cliente estiver em execução.
Caso a ligação TCP entre o cliente e o servidor se tornar inoperante, cabe ao  cliente  estabelecer  de  forma  automática  e  sem  intervenção  humana  uma  nova  ligação  com  o mesmo servidor.


### 6.5.4. Executor de Tarefas Automáticas

Opera como aplicação servidora SDP2021
Permite desencadear a execução de tarefas automáticas.

A ligação TCP deve persistir apenas durante a execução de cada tarefa automática.
Uma vez terminada a execução da tarefa, algo que deverá implicar a devolução de um feedback ao cliente, o cliente deve solicitar o fim da ligação


### 6.5.5. Motor de Fluxos de Atividades

Opera  como aplicação  servidora  SDP2021
Permite a obtenção  dos dados necessários às Aplicações de Serviços e RH.

Opera  como aplicação  cliente  SDP2021
Desencadeia  a  execução  de  tarefas  automáticas em servidores remotos que disponibilizam o serviço Executor de Tarefas Automáticas.
