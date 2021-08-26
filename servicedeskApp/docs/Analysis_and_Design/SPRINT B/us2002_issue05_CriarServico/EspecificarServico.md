# Análise #
 * Um serviço é caracterizador por:
        ** Informaçao Base:  codigo, titulo, descricaoBreve, descricaoCompleta, icone, palavrasChave, catalogo 
        **  Configuracao Formulario Solicitacao do Servico 
        **  Configuracao Fluxo Atividades

//#Configuracao Formulario Solicitacao do Servico || Formulario Atividade Manual:
//#    identificador, nome, lista de atributos (-> Solicitada ao colaborador), ScriptValidacao
//# 
//#Configuracao Atributo:
//#   nome da variavel , etiqueta de aprensentaçao (label), descriçao de ajuda, tipo de dados(numerico, texto), //ExpressaoRegular 

//#Especficar Atividades:
//#Atividade Aprovacao: Necessária ou não 
//# -> Atribuida ao responsavel do Colaborador que solicita o serviço

//#Atividade Resolucao 
//# tipo manual ou automatico ?
//# -> Caso tipo manual -> Configuracao Formulario Atividade Manual
//# -> Caso tipo Automatica -> Definir Linguagem de Script (Pela equipa)  e definir Script

//# Especificaçao Completa? ->  Serviço Disponivel 
//# Especificacao Incompleta -> Serviço Draft




# Design #

## WIP ##

Classes do dominio:  
 Servico, EstadoServico
 Servico um agregado independente de DishType
 (https://bitbucket.org/pag_isep/ecafeteria-spring/src/master/documentation/RegisterDish/UC%20-%20Register%20Dish%20-%20DESIGN.txt)
     - rela��o unidirecional many-to-one
     - cascade NONE
     - fetch EAGER
 nome do prato � do tipo Designation (framework)
 NutricionalInfo � um value object que faz parte do agregado Dish
     - rela��o unidireccional one-to-one
     - cascade ALL
     - fetch EAGER

Controlador: 
 RegistarServicoController
 ListCatalogoService - para evitar duplicaco de codigo

Repository: 
 ServicoRepository

###SD
```



title US 2002_Como RRH, pretendo especificar um novo Serviço


actor RRH
RRH->RegistarServicoUI: especificarServico
RegistarServicoUI->RegistarEquipaController: create
RegistarServicoController -> PersistenceContext: repositories()
PersistenceContext->RepositoryFactory: create
RepositoryFactory->ServicoRepository: create

#Catalogo -> Escolha do catalogo onde o serviço vai estar disponivel 
RegistarServicoUI->RegistarServicoController: catalogo()
RegistarServicoController -> ListCatalogoService: create()
RegistarServicoController -> ListCatalogoService: allCatalogo()
RegistarServicoUI->RegistarServicoUI: show()
RegistarServicoUI->RegistarServicoController: selectCatalogo()



#Info base:
#codigo, titulo, descricaoBreve, descricaoCompleta, icone, palavrasChave, catalogo 

RegistarServicoUI->RegistarServicoController: registarServico(Equipa, codigo, acronimo, designacao, listaColaboradoresResponsaveis)
RegistarServicoController -> Servico: newServico = create(codigo, titulo, descricaoBreve, descricaoCompleta, icone, palavrasChave, catalogo)




#Configuracao Formulario Solicitacao do Servico || Formulario Atividade Manual:
#    identificador, nome, lista de atributos (-> Solicitada ao colaborador), ScriptValidacao
# 
#Configuracao Atributo:
#   nome da variavel , etiqueta de aprensentaçao (label), descriçao de ajuda, tipo de dados(numerico, texto), ExpressaoRegular 

#Especficar Atividades:
#Atividade Aprovacao: Necessária ou não 
# -> Atribuida ao responsavel do Colaborador que solicita o serviço

#Atividade Resolucao 
# tipo manual ou automatico ?
# -> Caso tipo manual -> Configuracao Formulario Atividade Manual
# -> Caso tipo Automatica -> Definir Linguagem de Script (Pela equipa)  e definir Script

# Especificaçao Completa? ->  Serviço Disponivel 
# Especificacao Incompleta -> Serviço Draft





#RegistarEquipaUI->RegistarEquipaController: colaborador()
#RegistarEquipaController -> ListTiposEquipaService: create()
#RegistarEquipaController -> ListColaboradoresService: allColaboradorEquipa()
#RegistarEquipaUI->RegistarEquipaUI: show()
#loop:
#RegistarEquipaUI->RegistarEquipaController: registarEquipa(Equipa, codigo, acronimo, designacao, listaColaboradoresResponsaveis)
#RegistarEquipaController -> Equipa: newEquipa = create(tipoEquipa, codigo, acronimo, designacao, listaColaboradoresResponsaveis)
#RegistarEquipaController->EquipaRepository: save(newEquipa)
#end loop


```



# Tests # 



