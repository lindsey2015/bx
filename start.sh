#!/bin/sh

export JAVA_HOME="software/win64/jdk1.6.0_18/"
export CATALINA_HOME="software/win64/apache-tomcat-6.0.43/"
export CATALINA_BASE=${CATALINA_HOME}
export JAVA_OPTS="-Xms128m -Xmx768m -javaagent:${CATALINA_HOME}\lib\aspectjweaver.jar -DpageDebuggingJarPath=${CATALINA_HOME}\lib\page-debugging.jar"
export JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9999"
$CATALINA_HOME/bin/catalina.sh jpda start