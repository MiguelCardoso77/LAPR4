@ECHO OFF

ECHO Running Candidate App...
SET CANDIDATE=jobs4u.candidate.app\target\jobs4u.candidate.app-0.1.0.jar;jobs4u.candidate.app\target\dependency\*

java -cp %CANDIDATE% user.console.CandidateApp
