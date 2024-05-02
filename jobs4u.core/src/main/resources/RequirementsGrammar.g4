grammar RequirementsGrammar;

start: requirements;

requirements: HASHTAG REQUIREMENTS PARAGRAPH requirementsList;
requirementsList: ARROW requirementType;
requirementType: academicDegree | programmingLanguages | yearsOfExperience;

academicDegree: ACADEMIC_DEGREE academicDegreeType PARAGRAPH requirementsList | ACADEMIC_DEGREE academicDegreeType;
academicDegreeType: NONE | BACHELOR | MASTER | DOCTORATE;

programmingLanguages: PROGRAMMING_LANGUAGES programmingLanguagesType PARAGRAPH requirementsList | PROGRAMMING_LANGUAGES programmingLanguagesType;
programmingLanguagesType: programmingTypes | programmingTypes SPACE programmingLanguagesType;
programmingTypes: JAVA | JAVASCRIPT | PYTHON | TYPESCRIPT | PHP | CHASH;

yearsOfExperience: YEARS_OF_EXPERIENCE yearsOfExperienceType PARAGRAPH requirementsList | YEARS_OF_EXPERIENCE yearsOfExperienceType;
yearsOfExperienceType: INTEGER;

// Lexical rules:
ARROW: '-> ';
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