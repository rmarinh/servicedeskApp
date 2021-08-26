# 2051 - Registar colaborador
=======================================

# 1. Requisitos

- US 2051 - Como Responsável de Recursos Humanos (RRH), eu pretendo proceder à especificação de um novo colaborador de modo a que este possa, posteriormente, aceder e usar o sistema.

Apenas o responsável de recursos humanos pode criar um novo colaborador. 
Ao criar o novo colaborador também é criada a conta de utilizador, para que este possa aceder e usar o sistema.

## 1.1 Regras de Negócio

* Um colaborador pode ter um responsável.
* Uma equipa pode ter um ou mais colaboradores
* Uma equipa pode ser constituída por um colaborador onde este é o responsável.

# 2. Análise 

Um colaborador caracteriza-se pelo seu número mecanográfico, nome curto, 
nome completo, data de nascimento, local de residência, endereço de email institucional,
número de contacto, pela função que desempenha e, caso exista, pela indicação de quem é o seu responsável 
hierárquico atual na organização (i.e., outro colaborador).

## 2.1 Testes

De forma a aferir uma correta satisfação dos requisitos da US foram concebidos os seguintes testes:

**Teste 1:** Após associação de um responsável a um colaborador, o colaborador deverá conter referencia para esse responsável.

**Teste 2:** Testar os values objects

# 3. Design 

## 3.1 Realização da Funcionalidade

![US 2051 - Diagrama de Sequência](US2051_RegistarColaborador.jpg)

## 3.3 Padrões Aplicados

| **Padrão**	   | **Observações**			|
|--------------|--------------------------------|
| Factory      | Padrão utilizado para atribuir a responsabilidade de criar objetos a uma classe particular. |
| Repository   | Padrão utilizado para persistir os objetos criados. |
| MVC   	   | Padrão utilizado para a UI e Controllers. |
| DTO   	   | Padrão utilizado para apresentação dos objectos. |
| Builder	   | Padrão utilizado para separar a construção de um objeto complexo de sua representação. |

# 4. Implementação

A implementação foi seguida de acordo com o diagrama de sequência apresentado. De observar que, todos os serviços de listagem utilizados no desenvolvimento desta US, mais concretamente listar os colaboradores, são utilizados DTOs de forma a diminuir o acoplamento entre a UI e as classes de Dominio.

Também houve a necessidade de utilização de um Builder para o registo de uma colaborador.

# 5. Observações
