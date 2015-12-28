LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := src/Little.g
TREE := src/LittleTreeParser.g

all: generate
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java generated/*.java

generate:
	rm -rf generated
	mkdir generated
	java -cp $(LIB_ANTLR) org.antlr.Tool -fo generated $(ANTLR_SCRIPT)
	java -cp $(LIB_ANTLR) org.antlr.Tool -fo generated $(TREE)

clean:
	rm -rf classes generated

grammar:
	java -cp $(LIB_ANTLR) org.antlr.Tool -fo generated $(ANTLR_SCRIPT)
	java -cp $(LIB_ANTLR) org.antlr.Tool -fo generated $(TREE)
T:
	java -cp $(LIB_ANTLR) org.antlr.Tool -fo generated src/T.g
group:
	echo "khaver aehumphr"

.PHONY: all generate clean
