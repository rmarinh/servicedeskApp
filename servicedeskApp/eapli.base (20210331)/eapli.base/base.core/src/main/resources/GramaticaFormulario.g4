grammar GramaticaFormulario;


prog: init;

init: Prologo formularioID atributos Epilogo                     # printAtributo
;
formularioID: '<formulario><nome>' TEXTO '</nome><atributos>'
;
atributos:   atributo
            |atributo (atributo)*
;
atributo: abrirAtributo campos fecharAtributo       # atributoSingular
;
abrirAtributo: '<atributo>'
;
fecharAtributo: '</atributo>'
;
campos: nome titulo descricao validadores
;
nome:'<nome>' TEXTO '</nome>'
;
titulo: '<titulo>' TEXTO '</titulo>'
;
descricao: '<descricao>' TEXTO '</descricao>'
;
validadores:       TIPOSTRING validador? '</validador><preenchimento>' preenchimentoString? '</preenchimento>'
                 | TIPOBOOLEANO validador? '</validador><preenchimento>' preenchimentoBooleano? '</preenchimento>'
                 | TIPONUMERICO validador? '</validador><preenchimento>' preenchimentoNumerico? '</preenchimento>'
                 | TIPODATA validador? '</validador><preenchimento>' preenchimentoData? '</preenchimento>'
;

validador: TEXTO | REGEX            #regex;
preenchimentoString: TEXTO          #userInputString;
preenchimentoBooleano: BOOLEANO     #userInputBoolean;
preenchimentoNumerico: NUMERICO     #userInputNumerico;
preenchimentoData: DATA             #userInputData;




WS : [ \t\n\r] + -> skip;
NEWLINE : [2\r\n]+ -> skip;

TIPOSTRING:'<tipoDados>String</tipoDados><validador>';
TIPOBOOLEANO:'<tipoDados>Boolean</tipoDados><validador>';
TIPONUMERICO: '<tipoDados>Numerico</tipoDados><validador>';
TIPODATA:'<tipoDados>Data</tipoDados><validador>';

Prologo:'<?xml version="1.0" encoding="UTF-8" standalone="yes"?>';
Epilogo: '</atributos><scriptvalidacao/></formulario>' ;
DATA: (YEAR'-'MONTH'-'DAY);
NUMERICO: [0-9]+;
YEAR: ('20'([0-9][0-9]|[3-9][0-9]));
MONTH: ('0'[1-9]|'1'[1-2]);
DAY: ('0'[1-9]|[1-2][0-9]|'3'[1-2]);
BOOLEANO: TRUE | FALSE;
TRUE: 'true' | 'TRUE' | 'True';
FALSE: 'false' | 'FALSE' | 'False';
TEXTO: [a-zA-Z0-9 -]+;
REGEX: ('['* TEXTO ']'* ('*'|'+'|'?')* ('{'[0-9]*'}')*)+;