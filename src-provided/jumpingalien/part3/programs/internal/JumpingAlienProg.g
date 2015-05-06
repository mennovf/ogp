grammar JumpingAlienProg;

program : 
	declaration*
	main=statement
;

declaration : variableType=type variableName=IDENTIFIER (':=' initialValue=literal)? ';';

statement : first=singleStatement (rest += singleStatement)*;

singleStatement:
    assignStatement
  | whileStatement
  | foreachStatement
  | breakStatement
  | ifStatement
  | printStatement
  | actionStatement
  ;

assignStatement: variableName=IDENTIFIER ':=' value=expression ';';

whileStatement : 'while' condition=expression 'do' body=statement 'done';

foreachStatement : 'foreach' '(' variableKind=kind ',' variableName=IDENTIFIER ')'
	('where' whereExpr=expression)?
	('sort' sortExpr=expression sortDir=sortDirection)?
	'do' body=statement 'done';

breakStatement: 'break' ';';

ifStatement : 'if' condition=expression 'then' ifbody=statement ('else' elsebody=statement)?  'fi';

printStatement : 'print' value=expression ';';

actionStatement :
    startRunStatement
  | stopRunStatement
  | startJumpStatement
  | stopJumpStatement
  | startDuckStatement
  | stopDuckStatement
  | waitStatement
  | skipStatement
  ;

startRunStatement: 'start_run' runDirection=expression ';';
stopRunStatement: 'stop_run' runDirection=expression ';';

startJumpStatement: 'start_jump' EMPTY_PAREN? ';';
stopJumpStatement: 'stop_jump' EMPTY_PAREN? ';';

startDuckStatement: 'start_duck' EMPTY_PAREN? ';';
stopDuckStatement: 'stop_duck' EMPTY_PAREN? ';';

waitStatement: 'wait' duration=expression ';';

skipStatement: 'skip' ';';
	
expression : 'sqrt' expr=expression #sqrtExpr
           | 'random' maxValue=expression #randomExpr
           | attr=(GETX|GETY|GETWIDTH|GETHEIGHT|GETHP) object=expression #getExpr
           | 'searchobj' searchDirection=expression #searchObjExpr
           | 'gettile' '(' xpos=expression ',' ypos=expression ')' #getTileExpr
           | test=(ISMAZUB|ISSHARK|ISSLIME|ISPLANT|ISDEAD|ISTERRAIN|ISPASSABLE|ISWATER|ISMAGMA|ISAIR|ISDUCKING|ISJUMPING) obj=expression #isExpr
           | ISMOVING '(' obj=expression ',' isMovingDirection=expression ')' #isMoving
		   | left=expression op=(TIMES|DIV) right=expression #multExpr
           | left=expression op=(PLUS|MINUS) right=expression #addExpr
		   | left=expression op=(LT|LTE|EQ|NEQ|GTE|GT) right=expression #cmpExpr
		   | '!' expr=expression #notExpr
		   | left=expression '&&' right=expression #andExpr
		   | left=expression '||' right=expression #orExpr
		   | variableName=IDENTIFIER #varExpr
		   | literal #literalExpr
		   | '(' expr=expression ')' #parenExpr
;

literal: (PLUS|MINUS)? value=NUMBER  #intLiteral
| (PLUS|MINUS)? NUMBER '.' NUMBER #doubleLiteral
| (BOOL_TRUE | BOOL_FALSE) #boolLiteral
| directionLiteral=direction #directionLiteral
| 'null' # nullLiteral
| 'self' # selfLiteral
;

type : double_type | bool_type | object_type | direction_type;
double_type : 'double';
bool_type: 'bool';
object_type: 'object';
direction_type: 'direction';

kind : KIND_MAZUB | KIND_BUZAM | KIND_SLIME | KIND_SHARK | KIND_PLANT | KIND_TERRAIN | KIND_ANY;
KIND_MAZUB: 'mazub';
KIND_BUZAM: 'buzam';
KIND_SLIME: 'slime';
KIND_SHARK: 'shark';
KIND_PLANT: 'plant';
KIND_TERRAIN: 'terrain';
KIND_ANY: 'any';

sortDirection: ASCENDING | DESCENDING;
ASCENDING: 'ascending';
DESCENDING: 'descending';

GETX: 'getx';
GETY: 'gety';
GETWIDTH: 'getwidth';
GETHEIGHT: 'getheight';
GETHP: 'gethp';

ISMAZUB: 'ismazub';
ISSHARK: 'isshark';
ISSLIME: 'isslime';
ISPLANT: 'isplant';
ISDEAD: 'isdead';
ISTERRAIN: 'isterrain';
ISPASSABLE: 'ispassable';
ISWATER: 'iswater';
ISMAGMA: 'ismagma';
ISAIR: 'isair';
ISMOVING: 'ismoving';
ISDUCKING: 'isducking';
ISJUMPING: 'isjumping';

BOOL_TRUE : 'true';
BOOL_FALSE : 'false';

direction: DIRECTION_LEFT | DIRECTION_RIGHT | DIRECTION_UP | DIRECTION_DOWN;
DIRECTION_LEFT: 'left';
DIRECTION_RIGHT: 'right';
DIRECTION_UP: 'up';
DIRECTION_DOWN: 'down';

IDENTIFIER: LETTER (LETTER|DIGIT)*;

LETTER: 'a'..'z'|'A'..'Z';
NUMBER: DIGIT+;
DIGIT: ('0'..'9');

LT : '<';
LTE : '<=';
EQ : '==';
NEQ: '!=';
GT : '>';
GTE : '>=';

PLUS: '+';
MINUS: '-';
TIMES: '*';
DIV: '/';

EMPTY_PAREN: '(' ')';
WS : (' ' | '\t' | '\n') -> skip;
