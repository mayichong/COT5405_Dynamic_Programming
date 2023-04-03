JAVAC = javac
JFLAGS = -g

.SUFFIXES: .java .class

.java.class:
	$(JAVAC) $(JFLAGS) $*.java

CLASSES = DynamicProgramming.class

all: $(CLASSES)

run1:
	java DynamicProgramming 1
run2:
	java DynamicProgramming 2
run3:
	java DynamicProgramming 3
run4:
	java DynamicProgramming 4
run5:
	java DynamicProgramming 5
run6:
	java DynamicProgramming 6
run7:
	java DynamicProgramming 7
clean:
	$(RM) *.class
