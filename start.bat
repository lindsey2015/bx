@echo off
rem check 32 or 64 bit os
reg Query "HKLM\Hardware\Description\System\CentralProcessor\0" | find /i "x86" > NUL && set OS=32 || set OS=64

rem set env variables
set JAVA_HOME=software\win%OS%\jdk1.6.0_18
set CATALINA_HOME=software\win%OS%\apache-tomcat-6.0.43
set CATALINA_BASE=%CATALINA_HOME%

set LOG_HOME=%CATALINA_HOME%\logs
set JAVA_OPTS=-Dlog.path=%LOG_HOME% -server -Xms128m -Xmx512m 
set JAVA_OPTS=%JAVA_OPTS% -javaagent:%CATALINA_HOME%\lib\aspectjweaver.jar -DpageDebuggingJarPath=%CATALINA_HOME%\lib\page-debugging.jar
set CATALINA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9999"

rem remove old war, logs, work folder
del /s /q %CATALINA_BASE%\webapps\*.war
del /s /q %CATALINA_BASE%\logs\*
rmdir /s /q %CATALINA_BASE%\webapps\bx
rmdir /s /q %CATALINA_BASE%\work

rem deploy new war to webapps
copy /y target\bx-0.0.1-SNAPSHOT.war %CATALINA_BASE%\webapps\bx.war

rem start tomcat
%CATALINA_HOME%/bin/catalina.bat start