grammar InterviewModelGrammar;

start: questions;

questions: HASHTAG QUESTIONS PARAGRAPH questionsList;
questionsList: ARROW questionType;
questionBody: ANY_STRING;
questionType: questionDate | questionDecimalNumber | questionIntegerNumber | questionMultipleChoice | questionNumericRange | questionSingleChoice | questionShortAnswer | questionTime | questionTrueFalse;

questionDate: DATE questionBody SPACE answerDate SPACE score;
answerDate: DATE_ANSWER;

questionDecimalNumber: DECIMAL_NUMBER questionBody SPACE answerDecimal SPACE score;
answerDecimal: DECIMAL;

questionIntegerNumber: INTEGER_NUMBER questionBody SPACE answerInteger SPACE score;
answerInteger: INTEGER;

questionMultipleChoice: MULTIPLE_CHOICE questionBody SPACE answerMultipleChoice SPACE score;
answerMultipleChoice: ANY_STRING | ANY_STRING SPACE answerMultipleChoice;

questionNumericRange: NUMERIC_RANGE questionBody SPACE answerNumericRange SPACE score;
answerNumericRange: NUMERIC_RANGE_ANSWER;

questionSingleChoice: SINGLE_CHOICE questionBody SPACE answerSingleChoice SPACE score;
answerSingleChoice: ANY_STRING;

questionShortAnswer: SHORT_ANSWER questionBody SPACE answerShortText SPACE score;
answerShortText: ANY_STRING;

questionTime: TIME questionBody SPACE answerTime SPACE score;
answerTime: TIME_ANSWER;

questionTrueFalse: TRUE_FALSE questionBody SPACE answerTrueFalse score;
answerTrueFalse: TRUE_FALSE_ANWSER;

score: PERCENTAGE PARAGRAPH questionsList | PERCENTAGE;

// Lexical rules:
ANY_STRING : '<' ~('<' | '>')* '>';
ARROW: '-> ';
DECIMAL: [0-9]+ '.' [0-9]+ | [0.9]+ ',' [0-9]+;
INTEGER: [0-9]+;
HASHTAG: '# ';
PARAGRAPH: '\n' | '\r';
PERCENTAGE: [0-9]+ '%';
SPACE: ' ';

// Question Types:
QUESTIONS: 'Questions:';
DATE: 'Date: ';
DATE_ANSWER: ('0'[1-9]|'1'[0-9]|'2'[0-9]|'3'[0-1]) '/' ('0'[1-9]|'1'[0-2]) '/' [1-9][0-9][0-9][0-9];
DECIMAL_NUMBER: 'Decimal Number: ';
INTEGER_NUMBER: 'Integer Number: ';
MULTIPLE_CHOICE: 'Multiple Choice: ';
NUMERIC_RANGE: 'Numeric Range: ';
NUMERIC_RANGE_ANSWER: [0-9]+ '-' [0-9]+;
SINGLE_CHOICE: 'Single Choice: ';
SHORT_ANSWER: 'Short Answer: ';
TIME: 'Time: ';
TIME_ANSWER: ('0'[0-9]|'1'[0-9]|'2'[0-4]) ':' ('0'[0-9]|'1'[0-9]|'2'[0-9]|'3'[0-9]|'4'[0-9]|'5'[0-9]);
TRUE_FALSE: 'True/False: ';
TRUE_FALSE_ANWSER: [Tt]'rue ' | [Ff]'alse ';
