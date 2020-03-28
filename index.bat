echo off
echo NUL>_.class&&del /s /f /q *.class
cls
javac -encoding utf8 com/krzem/logic_gates/Main.java&&java -Dfile.encoding=UTF8 com/krzem/logic_gates/Main
start /min cmd /c "echo NUL>_.class&&del /s /f /q *.class"