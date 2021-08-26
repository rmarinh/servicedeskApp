grammar ScriptingLanguageForm;


parse
 : 'Type:=' TypeScript block EOF
 ;

block
 : statement* ( Return expression ';' )?
 ;

statement
 : assignment ';'
 | functionCall ';'
 | ifStatement
 ;

assignment
 : Identifier Assign expression
 ;
functionCall
 : Identifier '(' exprList? ')' #identifierFunctionCall
 | Println '(' expression? ')'  #printlnFunctionCall
 | Print '(' expression ')'     #printFunctionCall
 | Assert '(' expression ')'    #assertFunctionCall
 | Search '(' expression ')'    #searchFunctionCall
 | Replace '(' expression')'  #replaceFunctionCall
 | CreateEmail '(' Identifier ','Identifier',' Identifier  ')'     #createEmailFunctionCall
 | SendEmail '(' Identifier ')'     #sendEmailFunctionCall
 ;
ifStatement
 : ifStat  elseStat? End
 ;

ifStat
 : If expression Do block
 ;
elseStat
 : Else Do block
 ;


exprList
 : expression ( ',' expression )*
 ;

expression
 : '-' expression                                       #unaryMinusExpression
 | '!' expression                                       #notExpression

 //calctype
 | expression op=( '*' | '/' | '%' ) expression         #multExpression
 | expression op=( '+' | '-' ) expression               #addExpression
 | <assoc=right> expression '^' expression              #powerExpression
 //LLogicalType
 | expression op=( '>=' | '<=' | '>' | '<' ) expression #compExpression
 | expression op=( '==' | '!=' ) expression             #eqExpression
 | expression '&&' expression                           #andExpression
 | expression '||' expression                           #orExpression
 | expression In expression                             #inExpression

 | Number                                               #numberExpression
 | Bool                                                 #boolExpression
 | Null                                                 #nullExpression
 //script  type
 | functionCall expression?                                #functionCallExpression
 | Identifier expression?                                  #identifierExpression
 | String expression?                                      #stringExpression
 | '(' expression ')' expression?                          #expressionExpression
 | Input '(' String? ')'                                #inputExpression
 | expression Match expression                          #matchExpression
 ;


//Opercoes
Println  : 'println';
Print    : 'print';
Input    : 'input';
Assert   : 'assert';
Match : 'match';
Search : 'search';
Replace: 'replace';
CreateEmail :'createEmail';
SendEmail :'sendEmail';

//Conditional
Def      : 'def';
If       : 'if';
Else     : 'else';
Return   : 'return';
To       : 'to';
Do       : 'do';
End      : 'end';
In       : 'in';
Null     : 'null';
//LogicalOP
Or       : '||';
And      : '&&';
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
OBrace   : '{';
CBrace   : '}';
OBracket : '[';
CBracket : ']';
OParen   : '(';
CParen   : ')';
SColon   : ';';
Assign   : '=';
Comma    : ',';
Colon    : ':';
TypeScript : ('FORMULARIOTYPE'|'SCRIPTTYPE');
Bool
 : 'true'
 | 'false'
 ;

Number
 : Int ( '.' Digit* )?
 ;

Identifier
 : [a-zA-Z_] [a-zA-Z_0-9]*
 ;

String
 : ["] ( ~["\r\n\\] | '\\' ~[\r\n] )*  ["]
 | ['] ( ~['\r\n\\] | '\\' ~[\r\n] )* [']
 ;

StringWithReplace
: '-''–''{''{'[A-Z]'}''}''-''-'
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
