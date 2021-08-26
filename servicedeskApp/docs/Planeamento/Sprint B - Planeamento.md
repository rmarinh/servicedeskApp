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

## Comunicações Clientes - Servidor ##



# 3. Calendário de Sprints #

| **Sprint** | **Início** | **Fim** |
|-----|-----------|-----------|
| A | 08/03/21 | 18/04/21 |
| B | 19/04/21 | 09/05/21 |
| C | 10/05/21 | 30/05/21 |
| D | 31/05/21 | 20/06/21 |


# 4. Distribuição de Funcionalidades #

A distribuição de requisitos/funcionalidades ao longo do período de desenvolvimento do projeto pelos elementos do grupo de trabalho realizou-se conforme descrito na tabela seguinte.

| **Aluno Nr.**	| **Sprint B** | **Sprint C** | **Sprint D** |
|------------|----------|----------|----------|
| 1171602 | [2002-Criar Serviço](/docs/Analysis_and_Design/us2002_issue05_CriarServico)|  |  |
|  		  | [2103-BootStrap Organizacao Equipa](/docs/Analysis_and_Design/us2103_issue12_BootstrapOrganizacaoEquipa) |  |  |
| 		  | | 2003-Completar Serviço |  |
| 		  | | 2104-BootStrap Serviços |  |
| 1181895 | [2052](/docs/Analysis_and_Design/us2052_issue07_RegistarEquipa)|  |  |
|         | [2054](/docs/Analysis_and_Design/us2054_issue14_RegistarTipoEquipa)|  |  |
|         | | 2011-Atualização / Estado atual do Motor de Fluxo de Atividades |  |
|         | | 3011-Dashboard Web do Utilizador |  |
|         | | 4002-Disponibilizar infos do Motor de Fluxo de Atividades |  |
| 1181892 | [3001-Consultar ou pesquisar catálogos / serviços](/docs/Analysis_and_Design/us3001_issue9_PesquisarCatalogosDeServicos)|  |  |
|         | [2010-Definir níveis de criticidade](/docs/Analysis_and_Design/us2010_issue13_DefinirNivelCriticidadeSLA)|  |  |
|         | 9001-Elaborar apresentação |  |  |
|         | | 5001-Executor de Tarefas Automáticas |  |
|         | | 2105-Bootstrap de níveis de criticidade |  |
| 1181882 | [2053-Associar / Remover colaborador de equipa](/docs/Analysis_and_Design/us2053_issue08_AssociarRemoverColaboradorEquipa)|  |  |
|         | [2051-Registar colaborador](/docs/Analysis_and_Design/us2051_issue06_RegistarUmColaborador)|  |  |
|         | | 3021-Consultar tarefas pendentes |  |
|         | | 1006-Componente representativa de uma tarefa automática |  |
|         | | 3022-Consultar e reivindicar tarefas pendentes |  |
| 1140858 | [2001-Criar catálogo](/docs/Analysis_and_Design/us2001_issue04_CriarCatalogo)|  |  |
|         | [2101-Bootstrap de catalogo e serviço](/docs/Analysis_and_Design/us2101_issue11_BootstrapCatalogoEServico) |  |  |
|         | | 4001-Desenvolvimento do motor de fluxo de atividade |  |
|         | | 1005-Desenvolvimento da componente representativa de uma tarefa manual |  |
|         | | 2012-Atribuir nivel de criticidade |  |

# 5. Dependências de User Stories #

| **Sprint** | **US** | **Dependências** | **Comentários** |
|-----|-----------|-----------|---------------------|
| B | 2052 | 2054 | Para criar uma equipa precisa de criar primeiro um tipo de equipa. |
| B | 2053 | 2051 | Para associar ou remover colaboradores numa equipa precisamos de ter colaboradores no sistema. |
| B | 3001 | 2002 | Para um utilizador pesquisar catálogos eles têm que existir no sistema. |
| B | 2101 | 2001 | A Factory da entidade Catalogo necessita de estar implementada para que estes sejam inicializados no sistema. |
| B | 2101 | 2051/2052/2053/2054 | A Factory das respectivas entidades necessitam de estar implementadas para que o sistema seja inicializado com a informação completa. |
| B | 9001 | Todas | É necessário desenvolvimento para realizar a apresentação. |
| B | Todas | 1003 | Todas dependem de repositórios. |