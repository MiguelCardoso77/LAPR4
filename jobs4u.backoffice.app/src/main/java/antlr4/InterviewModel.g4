grammar InterviewModel;

start: header requirements? interview footer;

footer: BULLET_POINT_T FOOTER score_definition TERMINAL_T interview_conduct TERMINAL_T;

interview_conduct: INTERVIEW SHOULD_BE CONDUCTED IN order ORDER;

order: order_atomic COMA_T order | order_atomic;

order_atomic: ORDER_TYPE ORDER_METHOD?;

score_definition: PASSING RESULTS MUST_SCORE passingScore=Decimal outstandingClause?;

outstandingClause: COMA_T OUTSTANDING RESULTS MUST_SCORE outstandingResults=Decimal;

requirements: BULLET_POINT_T REQUIREMENTS requirements_body;

requirements_body: requirements_atomic requirements_body | requirements_atomic;

//TODO Define
requirements_atomic: ENTRY_T requirementName=String AS requirementsType TERMINAL_T ;

requirementsType: 'text' (MAY_BE String)?
                | 'number' MAY_BE AT_LEAST? Decimal
                | 'number' MAY_BE Decimal AND_MORE
                | 'group' MAY_BE 'one of' '[' arr ']'
                | 'yes/no' MAY_BE ('yes'|'no')
                ;

arr: arr COMA_T String | String;

interview: BULLET_POINT_T INTERVIEW interview_body;

interview_body: interview_atomic interview_body | interview_atomic;

//TODO Define
interview_atomic: ENTRY_T (true_false | short | single_choice | multiple_choice | int_q | real_q | date_q | time_q | scale_q) TERMINAL_T;

true_false: TRUE_FALSE QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
ITS_VALIDATION IS_DEFINED AS (TRUE|FALSE) AWARDING questionPoints=Decimal;

short: SHORT_TEXT QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
ITS_VALIDATION IS_DEFINED AS questionCorrection=String OF TYPE ('literal' | 'regex') AWARDING questionPoints=Decimal;

single_choice: SINGLE_CHOICE QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
ITS_VALIDATION IS_DEFINED AS correctChoice=String FROM choices=String+ AWARDING questionPoints=Decimal;

multiple_choice: MULTIPLE_CHOICE QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
ITS_VALIDATION IS_DEFINED AS correctChoices=c_choices FROM choices=String+ AWARDING questionPoints=Decimal;

c_choices: choice choice+;

choice: '(' String COMA_T Decimal ')';

int_q: INTEGER_NUMBER QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
       ITS_VALIDATION IS_DEFINED AS questionCorrection=Decimal AWARDING questionPoints=Decimal;

real_q: REAL_NUMBER QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
        ITS_VALIDATION IS_DEFINED AS questionCorrection=Real AWARDING questionPoints=Decimal;

date_q: DATE QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
             ITS_VALIDATION IS_DEFINED AS questionCorrection=String OF TYPE format=String AWARDING questionPoints=Decimal;

time_q: TIME QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
             ITS_VALIDATION IS_DEFINED AS questionCorrection=String OF TYPE format=String AWARDING questionPoints=Decimal;

scale_q: SCALE QUESTION WITH ID questionId=String BEING questionPrompt=String WITH DIFFICULTY OF questionDifficulty=Decimal TERMINAL_T
                     ITS_VALIDATION IS_DEFINED AS questionCorrection=Decimal BETWEEN lowerBound=Decimal AND higherBound=Decimal AWARDING questionPoints=Decimal;

header: CREATE INTERVIEW_MODEL interviewName=String FOR_JOB jobOpeningReference=String AS FOLLOWS SPECIFICATION_T;

// Categories

fragment CreateCapitalized: 'Create';
fragment CreateUncapitalized: 'create';
fragment RequirementsCapitalized: 'Requirements';
fragment RequirementsUncapitalized: 'requirements';
fragment InterviewCapitalized : 'Interview';
fragment InterviewUncapitalized : 'interview';
fragment FooterCapitalized : 'Footer';
fragment FooterUncapitalized : 'footer';
fragment PassingCapitalized : 'Passing';
fragment PassingUncapitalized : 'passing';
fragment CommentBody: ~[#\n]*;
fragment QuestionCapitalized: 'Question';
fragment QuestionUncapitalized : 'question';

FOOTER: FooterCapitalized | FooterUncapitalized;
INTERVIEW: InterviewCapitalized | InterviewUncapitalized;
REQUIREMENTS: RequirementsCapitalized | RequirementsUncapitalized;
CREATE: CreateCapitalized | CreateUncapitalized;
PASSING: PassingCapitalized | PassingUncapitalized;
QUESTION: QuestionCapitalized| QuestionUncapitalized;

// Semantic tokens, Readable for the language

AND: 'and';
AS: 'as';
AWARDING: 'awarding';
BEING: 'being';
BETWEEN: 'between';
CONDUCTED: 'conducted';
DIFFICULTY: 'difficulty';
FALSE: 'false';
FOLLOWS: 'follows';
FOR_JOB: 'for job opening';
FROM: 'from';
IN: 'in';
INTERVIEW_MODEL: 'interview model';
IS_DEFINED: 'is defined';
ITS_VALIDATION: [Ii]'ts validation';
MAY_BE: 'may be';
MUST_SCORE: 'must score';
OF:'of';
OUTSTANDING: 'outstanding';
ORDER: 'order';
RESULTS: 'results';
SHOULD_BE:'should be';
TRUE: 'true';
TYPE: 'type';
WITH: 'with';

// Syntax tokens

BULLET_POINT_T: '*';
COMA_T: ',';
ENTRY_T: '->';
SPECIFICATION_T: ':';
TERMINAL_T: '.';
String: '"' ~'"'+ '"';
Decimal: '0'|[1-9][0-9]*;
Real: Decimal (TERMINAL_T|COMA_T) ('0' | [0-9]*[1-9]);
AND_MORE: '+';
AT_LEAST: '^';
ORDER_TYPE: ([rR]'andom'|[pP]'riority'|[dD]'ifficulty');
ORDER_METHOD: ([aA]'scending'|[dD]'escending');

// Skip comments or structural tokens.

COMMENT: '#' CommentBody '\r'?'\n' -> skip;
WS: [ \t\r\n]->skip;

TRUE_FALSE: [tT]'rue/false';
SHORT_TEXT: [sS]'hort text';
SINGLE_CHOICE: [sS]'ingle choice';
MULTIPLE_CHOICE: [mM]'ultiple choice';
INTEGER_NUMBER: [iI]'nteger number';
REAL_NUMBER: [rR]'eal number';
DATE: [dD]'ate';
TIME: [tT]'ime';
SCALE: [sS]'cale';
ID: [iI]'d';