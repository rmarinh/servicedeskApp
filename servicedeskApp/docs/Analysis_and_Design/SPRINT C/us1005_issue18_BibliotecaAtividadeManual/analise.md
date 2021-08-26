# DEFINICAO #

	* Biblioteca de tarefa representativas de atividades manuais: Estas tarefas manuais representam uma realização de uma tarefa manual e tem o propósito de recolher informação de um utilizador no âmbito de um pedido, estas tarefas serão disponibilizadas em sistema, para quando for criado um serviço, poder ser associado a um pedido. Com esta atividade estar disponível podemos reutilizar atividades e salvar tempo ao gestor dos serviços no acto de criação de serviços.

# ANÁLISE # 

	* Neste contexto, uma atividade manual representa uma acão desempenhada por um humano no ambito de um servico e aparecem no seguinte contexto: 
		• A realização, opcional e prévia, de uma atividade/tarefa que visa determinar a aprovação/rejeição do pedido com vista à sua posterior satisfação; e
		• A realização de uma atividade/tarefa que visa satisfazer o pedido em causa. Esta apenas deve ser executada (i) quando o pedido em causa foi aprovado (cf. atividade de aprovação); ou (ii) quando a referida atividade de aprovação não está contemplada no serviço subjacente ao pedido.

# REGRAS DE NEGÓCIO #

	* Uma atividade contém um formulário que terá de ser preenchido.
	* Uma atividade tem um script associado que terá de ser executado.

# TESTES UNITÁRIOS #

	* O preenchimento do formulario tera de ser de acordo com as regras e com o tipo de dados
	* Apos esta atividade ser associada a um fluxo de atividades, esta atividade devera continuar disponivel para ser novamente associada no futuro