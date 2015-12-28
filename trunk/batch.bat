@ECHO OFF
REM - LABEL INDICATING THE BEGINNING OF THE DOCUMENT.
:BEGIN

SETLOCAL

SET LIB_ANTLR=lib/antlr.jar
SET ANTLR_SCRIPT=src/Little.g
SET TREE=src/LittleTreeParser.g

IF [%1]==[] GOTO ask
IF [%1]==[all] GOTO generate
IF [%1]==[generate] GOTO generate
IF [%1]==[clean] GOTO clean
IF [%1]==[grammar] GOTO grammar



:generate
	rd /S /Q generated
	mkdir generated
	java -cp %LIB_ANTLR% org.antlr.Tool -fo generated %ANTLR_SCRIPT%
	java -cp %LIB_ANTLR% org.antlr.Tool -fo generated %TREE%
	IF [%1]==[generate] GOTO end

:all
	rd /Q /S classes
	mkdir classes

	javac -cp %LIB_ANTLR% -d classes src/*.java generated/*.java
GOTO end


:clean
	rd /S /Q classes 
	rd /S /Q generated
GOTO end

:grammar
	java -cp %LIB_ANTLR% org.antlr.Tool -fo generated %ANTLR_SCRIPT%
	java -cp %LIB_ANTLR% org.antlr.Tool -fo generated %TREE%
GOTO end

:ask
	echo Enter one choice as an argument "batch all"

:end