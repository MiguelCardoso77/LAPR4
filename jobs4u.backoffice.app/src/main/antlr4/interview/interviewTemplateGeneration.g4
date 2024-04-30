grammar interviewTemplateGeneration;

// Parser rules
template: candidateInfo interviewQuestions;

candidateInfo: 'Candidate Information' '{' 'Name:' NAME 'Email:' EMAIL '}' ;

interviewQuestions: 'Interview Questions' '{' question+ '}';

question: QUESTION_TEXT ANSWER_TYPE ';';


template2: jobRequirementSpecification ;

jobRequirementSpecification: 'Experience:' experienceRequirement  ';'  degree  programminglanguages ;

jobOpening: jobReference;

jobReference: 'Job' 'Reference' ':' JOB_REFERENCE ;

degree: 'Have a degree in computer science or similar program';

experienceRequirement: 'candidates' 'must' 'have' 'at' 'least' EXPERIENCE  'years' 'of' 'experience';

programminglanguages: 'have knowledge in, at least, one of the following programming languages:' PROGRAMMING_LANGUAGES;





// Lexer rules
NAME: [A-Za-z]+;
EMAIL: [a-zA-Z0-9_.+-]+ '@' [a-zA-Z0-9-]+ '.' [a-zA-Z0-9-.]+;
QUESTION_TEXT: '"' ~["]* '"';
ANSWER_TYPE: ('Multiple Choice' | 'Short Answer');
JOB_REFERENCE: [0-9]+ ;
EXPERIENCE: [0-9]+ ;
PROGRAMMING_LANGUAGES: ('java' | 'javascript' | 'typescript');


WS: [ \t\r\n]+ -> skip;