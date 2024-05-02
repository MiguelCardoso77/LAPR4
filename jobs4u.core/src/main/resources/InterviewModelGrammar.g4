grammar InterviewModelGrammar;

start: title requirements;

title: HASHTAG TITLE titleDescription PARAGRAPH PARAGRAPH;
titleDescription: ANY_STRING;

requirements: HASHTAG REQUIREMENTS PARAGRAPH requirementsList;
requirementsList: ARROW requirementType;
requirementType: academicDegree | programmingLanguages | yearsOfExperience;

academicDegree: ACADEMIC_DEGREE academicDegreeType PARAGRAPH requirementsList | ACADEMIC_DEGREE academicDegreeType PARAGRAPH PARAGRAPH questions;
academicDegreeType: NONE | BACHELOR | MASTER | DOCTORATE;

programmingLanguages: PROGRAMMING_LANGUAGES programmingLanguagesType PARAGRAPH requirementsList | PROGRAMMING_LANGUAGES programmingLanguagesType PARAGRAPH PARAGRAPH questions;
programmingLanguagesType: JAVA | JAVASCRIPT | PYTHON | TYPESCRIPT | PHP | CHASH;

yearsOfExperience: YEARS_OF_EXPERIENCE yearsOfExperienceType PARAGRAPH requirementsList | YEARS_OF_EXPERIENCE yearsOfExperienceType PARAGRAPH PARAGRAPH questions;
yearsOfExperienceType: INTEGER;


questions: HASHTAG QUESTIONS PARAGRAPH questionsList;
questionsList: ARROW questionType;
questionBody: ANY_STRING;
questionType: questionDate | questionDecimalNumber | questionIntegerNumber | questionMultipleChoice | questionNumericRange | questionSingleChoice | questionShortAnswer | questionTime | questionTrueFalse;

questionDecimalNumber: DECIMAL_NUMBER questionBody SPACE answerDecimal SPACE score;
answerDecimal: DECIMAL;

questionIntegerNumber: INTEGER_NUMBER questionBody SPACE answerInteger SPACE score;
answerInteger: INTEGER;

questionShortAnswer: SHORT_ANSWER questionBody SPACE answerShortText SPACE score;
answerShortText: ANY_STRING;

questionTrueFalse: TRUE_FALSE questionBody SPACE answerTrueFalse score;
answerTrueFalse: TRUE_FALSE_ANWSER;

//////////
questionDate: DATE questionBody answerDate;
questionMultipleChoice: MULTIPLE_CHOICE questionBody answerMultipleChoice;
questionNumericRange: NUMERIC_RANGE questionBody answerNumericRange;
questionSingleChoice: SINGLE_CHOICE questionBody answerSingleChoice;
questionTime: TIME questionBody answerTime;

answerDate: ANY_STRING;
answerMultipleChoice: '(' ANY_STRING (',' ANY_STRING)* ')';
answerNumericRange: INTEGER;
answerSingleChoice: ANY_STRING;
answerTime: ANY_STRING;
//////////

score: PERCENTAGE PARAGRAPH questionsList | PERCENTAGE PARAGRAPH PARAGRAPH results;

results: HASHTAG RESULTS PARAGRAPH resultsBody;
resultsBody: ARROW ANY_STRING;

TITLE: 'Title: ';

// Lexical rules:
ANY_STRING : '"' ~('"')* '"';
ARROW: '-> ';
DECIMAL: [0-9]+ '.' [0-9]+ | [0.9]+ ',' [0-9]+;
INTEGER: [0-9]+;
HASHTAG: '# ';
PARAGRAPH: '\n' | '\r';
PERCENTAGE: [0-9]+ '%';
SPACE: ' ';

// Requirements:
REQUIREMENTS: 'Requirements:';
ACADEMIC_DEGREE: 'Academic Degree: ';
PROGRAMMING_LANGUAGES: 'Programming Languages: ';
YEARS_OF_EXPERIENCE: 'Years of Experience: ';

// Academic Degree:
NONE: 'None';
BACHELOR: 'Bachelor';
MASTER: 'Master';
DOCTORATE: 'Doctorate';

// Programming Languages:
JAVA: 'Java';
JAVASCRIPT: 'JavaScript';
PYTHON: 'Python';
TYPESCRIPT: 'TypeScript';
PHP: 'PHP';
CHASH: 'C#';

// Question Types:
QUESTIONS: 'Questions:';
DATE: 'Date: ';
DECIMAL_NUMBER: 'Decimal Number: ';
INTEGER_NUMBER: 'Integer Number: ';
MULTIPLE_CHOICE: 'Multiple Choice: ';
NUMERIC_RANGE: 'Numeric Range: ';
SINGLE_CHOICE: 'Single Choice: ';
SHORT_ANSWER: 'Short Answer: ';
TIME: 'Time: ';
TRUE_FALSE: 'True/False: ';
TRUE_FALSE_ANWSER: [Tt]'rue ' | [Ff]'alse ';

// Results:
RESULTS: 'Results:';
