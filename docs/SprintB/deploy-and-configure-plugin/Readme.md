# Deploy and configure a plugin (i.e., Job Requirement Specification or Interview Model) to be used by the system

-----------

## User Story Description

As Language Engineer, I want to deploy and configure a plugin (i.e., Job Requirement Specification or Interview Model) to be used by the system.

-----------

# Grammars Created

## Requirements Grammar
```antlrv4
  grammar RequirementsGrammar;
  
  start: requirements;
  
  requirements: HASHTAG REQUIREMENTS PARAGRAPH requirementsList;
  requirementsList: ARROW requirementType;
  requirementType: academicDegree | programmingLanguages | yearsOfExperience;
  
  academicDegree: ACADEMIC_DEGREE academicDegreeType PARAGRAPH requirementsList | ACADEMIC_DEGREE academicDegreeType;
  academicDegreeType: NONE | BACHELOR | MASTER | DOCTORATE;
  
  programmingLanguages: PROGRAMMING_LANGUAGES programmingLanguagesType PARAGRAPH requirementsList | PROGRAMMING_LANGUAGES programmingLanguagesType;
  programmingLanguagesType: programmingTypes | programmingTypes COMMA SPACE programmingLanguagesType;
  programmingTypes: JAVA | JAVASCRIPT | PYTHON | TYPESCRIPT | PHP | CHASH;
  
  yearsOfExperience: YEARS_OF_EXPERIENCE yearsOfExperienceType PARAGRAPH requirementsList | YEARS_OF_EXPERIENCE yearsOfExperienceType;
  yearsOfExperienceType: INTEGER;
  
  // Lexical rules:
  ARROW: '-> ';
  COMMA: ',';
  INTEGER: [0-9]+;
  HASHTAG: '# ';
  PARAGRAPH: '\n' | '\r';
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
```

## Interview Model Grammar
```antlrv4
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
```

# Examples made to test the grammar

## Requirements Examples
[requirements](..%2F..%2F..%2Fjobs4u.core%2Fsrc%2Fmain%2Fresources%2Frequirements)

* We made three different examples to test different usages of the grammar, such as differente academic degrees, and different number of programming languages

### One of the test examples:
    # Requirements:
    -> Academic Degree: Master
    -> Programming Languages: Java, C#
    -> Years of Experience: 7

## Interview Model Examples
[interviewModels](..%2F..%2F..%2Fjobs4u.core%2Fsrc%2Fmain%2Fresources%2FinterviewModels)

* We made three different examples to test different usages of the grammar, such as all the possible types of questions.

### One of the test examples:
    # Questions:
    -> True/False: <Is Java a programming language?> True 30%
    -> Short Answer: <What is the name of the CEO of Microsoft?> <Satya Nadella> 20%
    -> Integer Number: <How many years of experience do you have?> 6 30%
    -> Decimal Number: <What is the value of 3/2?> 1.5 20%