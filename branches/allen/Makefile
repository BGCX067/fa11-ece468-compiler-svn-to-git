LIB_ANTLR := lib/antlr.jar
ANTLR_SCRIPT := src/Little.g

all: generate
	rm -rf classes
	mkdir classes
	javac -cp $(LIB_ANTLR) -d classes src/*.java generated/*.java

generate:
	rm -rf generated
	mkdir generated
	java -cp $(LIB_ANTLR) org.antlr.Tool -fo generated $(ANTLR_SCRIPT)

clean:
	rm -rf classes build

.PHONY: all generate clean
