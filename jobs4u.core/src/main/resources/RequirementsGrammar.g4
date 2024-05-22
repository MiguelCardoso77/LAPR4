grammar RequirementsGrammar;

start: requirements;

requirements: HASHTAG REQUIREMENTS PARAGRAPH requirementsList;
requirementsList: ARROW requirementType;
requirementType: academicDegree | languages | programmingLanguages | yearsOfExperience;

academicDegree: ACADEMIC_DEGREE academicDegreeType PARAGRAPH requirementsList | ACADEMIC_DEGREE academicDegreeType;
academicDegreeType: NONE | BACHELOR | MASTER | DOCTORATE;

languages: LANGUAGES languagesType PARAGRAPH requirementsList | LANGUAGES languagesType;
languagesSelection: languagesType | languagesType COMMA SPACE languagesSelection;
languagesType: ENGLISH | GERMAN | SPANISH | FRENCH | ITALIAN | PORTUGUESE;

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
LANGUAGES: 'Languages: ';
PROGRAMMING_LANGUAGES: 'Programming Languages: ';
YEARS_OF_EXPERIENCE: 'Years of Experience: ';

// Academic Degree:
NONE: 'None';
BACHELOR: 'Bachelor';
MASTER: 'Master';
DOCTORATE: 'Doctorate';

// Languages:
ENGLISH: 'English';
FRENCH: 'French';
GERMAN: 'German';
ITALIAN: 'Italian';
PORTUGUESE: 'Portuguese';
SPANISH: 'Spanish';

// Programming Languages:
JAVA: 'Java';
JAVASCRIPT: 'JavaScript';
PYTHON: 'Python';
TYPESCRIPT: 'TypeScript';
PHP: 'PHP';
CHASH: 'C#';