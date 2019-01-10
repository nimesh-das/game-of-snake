JFLAGS = -g
JC = javac
JVM = java
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Board2.java \
        HowToPlay.java \
        MouseIn.java \
        SplashScreen.java \
	Snake.java \
	GameOver.java \
	AudioPlayer.java	

MAIN = Snake

default: classes

classes: $(CLASSES:.java=.class)

run:
	$(JVM) $(MAIN)

clean:
	$(RM) *.class
