grammar ScriptingLanguageForm;

parse
 :  linha* EOF
 ;
/*
bloco
 : linha*
 |error #erroBloco
 ;
*/
linha
 : atribuicao ';' #tipoAtribuicao
 | comando ';'    #tipoComando
 | ifcondicional  #tipoCondicional
 | error          #erroLinha
 ;

atribuicao
 : Id '=' expressao
 ;
comando
 : Assert '(' expressao ')'    #assertComando
 ;
ifcondicional
 : ifStat  elseStat? End
 ;

ifStat
 : If expressao Do linha
 ;
elseStat
 : Else Do linha
 ;


exprList
 : expressao ( ',' expressao )*
 ;

expressao
 //calctype
 : expressao op=( '*' | '/' | '%' ) expressao            #multExpressao
 | expressao op=( '+' | '-' ) expressao                  #addExpressao
 //LLogicalType
 | expressao op=( '>=' | '<=' | '>' | '<' ) expressao    #compExpressao
 | expressao op=( '==' | '!=' ) expressao                #eqExpressao
 //For number or Comparation Assignement and Input
 | Number                                                  #numberExpressao
 | Bool                                                    #boolExpressao
 //script  type
 | Id expressao?                                  #idExpressao
 | String expressao?                                      #stringExpressao
 | '(' expressao ')' expressao?                          #expressaoExpressao
 | expressao Match expressao                             #matchExpressao
 ;
error: .;

//FROM ANTLR REPOSITORY

//Opercoes
Assert   : 'assert';
Match : 'match';

//Conditional
If       : 'if';
Else     : 'else';
Do       : 'do';
End      : 'end';
//LogicalOP
Equals   : '==';
NEquals  : '!=';
GTEquals : '>=';
LTEquals : '<=';
GT       : '>';
LT       : '<';
//Math Type
Pow      : '^';
Excl     : '!';
Add      : '+';
Subtract : '-';
Multiply : '*';
Divide   : '/';
Modulus  : '%';

//Special Chars Used
OParen   : '(';
CParen   : ')';
SColon   : ';';
Assign   : '=';
Comma    : ',';
Bool
 : 'true'
 | 'false'
 ;

Number
 : Int ( '.' Digit* )?
 ;

Id
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

String
 : ["] ( ~["\r\n\\] | '\\' ~[\r\n] )*  ["]
 | ['] ( ~['\r\n\\] | '\\' ~[\r\n] )* [']
 ;

Comment
 : ( '//' ~[\r\n]* | '/' .? '*/' ) -> skip
 ;
Space
 : [ \t\r\n\u000C] -> skip
 ;
fragment Int
 : [1-9] Digit*
 | '0'
 ;

fragment Digit
 : [0-9]
 ;