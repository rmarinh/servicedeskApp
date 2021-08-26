# DEFINICAO #

	* Catálogo de Serviços: forma através da qual se organiza os serviços disponibilizados pelo sistema e a partir da qual se determina o público-alvo desses serviços, i.e., quem pode requer os mesmos.

# ANÁLISE # 

	* Neste contexto, um catálogo disponibiliza um ou mais serviços e possui pelo menos um colaborador que é responsável pela prestação desses Serviços. Por exemplo, o catálogo de “Reportar Erro / Falha” pode compreender os seguintes serviços:
	• “Reportar anomalia de comunicação”;
	• “Reportar anomalia em equipamento”;
	• “Reportar anomalia em aplicação”.
	Nota: o catalogo deve conseguir contem mais catalogos mas essa funcionalidade não é solicitada neste momento.

# REGRAS DE NEGÓCIO #

	* Um catálogo disponibiliza um ou mais serviços
	* Possui pelo menos um responsavel
	* Caracteriza-se por um identificador, por um título, por
uma descrição breve e outro mais completa, um ícone
	* Contem um criterio de acesso (lista de equipes que tem acesso ao catalogo)
		
# TESTES UNITÁRIOS #

	* Após associação de um colaborador a lista de responsaveis, a lista de responsaveis deve conter esse colaborador
	* Os colaboradores da lista de responsaveis tem de conseguir alterar os dados de um catalogo
	* Após associação de uma equipa a lista de criterios, a lista de criterios deve conter essa equipa
	* Os colaboradores que pertencem as equipas que estao na lista de criterios tem que conseguir ver este catalogo e conseguir solicitar os servicos contidos nele
