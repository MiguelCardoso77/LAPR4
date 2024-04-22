grammar interviewTemplateGeneration;

// Parser rules
template: candidateInfo interviewQuestions;

candidateInfo: 'Candidate Information' '{' 'Name:' NAME 'Email:' EMAIL '}' ;

interviewQuestions: 'Interview Questions' '{' question+ '}';

question: QUESTION_TEXT ANSWER_TYPE ';';

// Lexer rules
NAME: [A-Za-z]+;
EMAIL: [a-zA-Z0-9_.+-]+ '@' [a-zA-Z0-9-]+ '.' [a-zA-Z0-9-.]+;
QUESTION_TEXT: '"' ~["]* '"';
ANSWER_TYPE: ('Multiple Choice' | 'Short Answer');

WS: [ \t\r\n]+ -> skip;