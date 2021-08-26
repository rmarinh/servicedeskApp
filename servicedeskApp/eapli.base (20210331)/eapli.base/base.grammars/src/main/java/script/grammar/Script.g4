grammar Script;

parse
 : bloco EOF        // Um bloco ou fim ENDOFFILE
 ;

bloco               //Um bloco: Tem 0 ou mais linhas
 : (linha)*
 ;

linha               // Uma linha pode ser de 3 tipos
 : declaracao ';'   // declaracao -> Com terminal ";"
 | comando ';'      // Comando (Execucao) ";"
 | ifBloco          // Condicional
 ;

declaracao          //Declaracao Identificador(String)
                    //: Identifier indexes? '=' expression
 : Identificador  '=' expressao
 ;

comando             // Comando
                    // Identifier '(' exprList? ')'
 : Println '(' expressao? ')'                                                                   #printlnComando     //Comando Println
 | Print '(' expressao ')'                                                                      #printComando       //Comando Print
 | Assert '(' expressao ')'                                                                     #assertComando      //Comando assert
 | SendEmail '(' expressao ',' expressao ',' expressao')'                                       #sendEmailComando   //Comando sendMail
 | Find '(' expressao',' expressao',' expressao',' expressao',' (expressao',')* expressao ')'   #findComando        //Comando find in xml
 | Replace '(' expressao','expressao ',' expressao')'                                           #replaceComando     //Comando replace in variable
 | Match (expressao )                                                                           #matchComando       //Comando match regex
 ;

ifBloco                                     //Condicional linha
 : ifCondicional elseCondicional? End       // if( expressao) do  bloco [pode ter ou nao ]else bloco   end
 ;

ifCondicional                               //Se Condicao
 : If expressao Do bloco
 ;

elseCondicional                             // else condicao
 : Else Do bloco
 ;


idList
 : Identificador ( ',' Identificador )*
 ;
/*
exprList
 : expression ( ',' expression )*
 ;
*/

expressao
   //Expressoes calculo
 : expressao op=( '*' | '/' | '%' ) expressao           #multExpressao
 | expressao op=( '+' | '-' ) expressao                 #addExpressao
 //Expressoes Comparacao Booleana
 | expressao op=( '>=' | '<=' | '>' | '<' ) expressao   #compExpressao
 | expressao op=( '==' | '!=' ) expressao               #eqExpressao
 //Expressao Match
 | expressao op='$' expressao                           #matchExpressao

//Tipos de dados
 | Number                                               #numberExpressao
 | Bool                                                 #boolExpressao
 | Null                                                 #nullExpressao
 | Date                                                 #dateExpressao

//Chamada de comandos
 | comando                                              #comandoExpressao
 | comando indexes?                                     #comandoExpressao
//Identificacoo
 | Identificador                                        #identificadorExpressao
 | Identificador indexes?                               #identificadorExpressao
 //Tipos de dados
 | String indexes?                                      #stringExpressao
 //| String
   | '(' expressao ')' indexes?                         #expressaoExpressao

 ;

//Indices
indexes
 : ( '[' expressao ']' )+
 ;



//Comandos
Println  : 'println';
Print    : 'print';
Assert   : 'assert';
SendEmail : 'sendemail';
CreateEmail : 'createemail';
Find : 'find';
Replace : 'replace';
Match: 'match';
Matcher: '$';

//Condicional
If       : 'if';
Else     : 'else';
Do       : 'do';
End      : 'end';
//Dados
Null     : 'null';
//Logica Bool
Or       : '||';
And      : '&&';
Equals   : '==';
NEquals  : '!=';
GTEquals : '>=';
LTEquals : '<=';

//OUtors Chars
Excl     : '!';
GT       : '>';
LT       : '<';
Add      : '+';
Subtract : '-';
Multiply : '*';
Divide   : '/';
Modulus  : '%';
OParen   : '(';
CParen   : ')';
SColon   : ';';
Assign   : '=';
Comma    : ',';

Bool
 : 'true'
 | 'false'
 ;

 Date
   : '"'Digit Digit Digit Digit'-'Digit Digit'-'Digit Digit'"'
   ;

Number
 : Int ( '.' Digit* )?
 ;

Identificador
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

String
 : ["] ( ~["\r\n\\] | '\\' ~[\r\n] )* ["]
 | ['] ( ~['\r\n\\] | '\\' ~[\r\n] )* [']
 ;

Comment
 : ( '//' ~[\r\n]* | '/*' .*? '*/' ) -> skip
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