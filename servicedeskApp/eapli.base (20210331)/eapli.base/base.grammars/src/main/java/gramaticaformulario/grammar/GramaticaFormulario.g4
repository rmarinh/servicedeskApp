grammar GramaticaFormulario;


prog: init;
// Prologo de XML Ignorado
init:   Prologo formularioID atributos epilogo          # printAtributo
;
// Nome do Formulario
formularioID: '<formulario><nome>' TEXTO '</nome><atributos>'
;
// Atributos
atributos:   atributo
            |atributo (atributo)*
;

//Estrutura do atributo
atributo: abrirAtributo campos fecharAtributo           # atributoSingular
;
//Tag abertura
abrirAtributo: '<atributo>'
;
//Tag  fecho
fecharAtributo: '</atributo>'
;

//Campos de atributo
campos: nome titulo descricao validadores
;

nome:'<nome>' TEXTO '</nome>'
;
titulo: '<titulo>' TEXTO '</titulo>'
;
descricao: '<descricao>' TEXTO '</descricao>'
;
//Validacao: Valida se o preenhcimento vai de acordo com o o TIPODADOS definido pelo colaborador
validadores:       TIPOSTRING validador? '</validador><preenchimento>' preenchimentoString? (error)? '</preenchimento>'
                 | TIPOBOOLEANO validador? '</validador><preenchimento>' preenchimentoBooleano? (error)?  '</preenchimento>'
                 | TIPONUMERICO validador? '</validador><preenchimento>' preenchimentoNumerico?  (error)? '</preenchimento>'
                 | TIPODATA validador? '</validador><preenchimento>' preenchimentoData? (error)?  '</preenchimento>'
;

//Validacao de Preenchimento consoante o tipo de dados
validador: TEXTO | REGEX                                #regex;
preenchimentoString: TEXTO                              #userInputString;
preenchimentoBooleano: BOOLEANO                         #userInputBoolean;
preenchimentoNumerico: NUMERICO                         #userInputNumerico;
preenchimentoData: DATA                                 #userInputData;
error: .;
epilogo: Epilogo #scriptValidacao; // get script validacao


WS : [ \t\n\r] + -> skip;
NEWLINE : [2\r\n]+ -> skip;

TIPOSTRING:'<tipoDados>String</tipoDados><validador>';
TIPOBOOLEANO:'<tipoDados>Boolean</tipoDados><validador>';
TIPONUMERICO: '<tipoDados>Numerico</tipoDados><validador>';
TIPODATA:'<tipoDados>Data</tipoDados><validador>';

Prologo:'<?xml version="1.0" encoding="UTF-8" standalone="yes"?>';
Epilogo: '</atributos><scriptvalidacao/></formulario>'
        |'</atributos><scriptvalidacao>'(.)*?'</scriptvalidacao></formulario>' //ignorar o script de validacao dentro do formuulario
        ;
DATA: (YEAR'-'MONTH'-'DAY);
NUMERICO: [0-9]+;
YEAR: ('20'([0-9][0-9]|[3-9][0-9]));
MONTH: ('0'[1-9]|'1'[1-2]);
DAY: ('0'[1-9]|[1-2][0-9]|'3'[1-2]);
BOOLEANO: TRUE | FALSE;
TRUE: 'true' | 'TRUE' | 'True';
FALSE: 'false' | 'FALSE' | 'False';
TEXTO: [a-zA-Z0-9 -]+;
REGEX: ('['* TEXTO ']'* '|'* ('*'|'+'|'?')* ('{'[0-9]*'}')*)+;