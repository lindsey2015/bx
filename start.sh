#!/bin/sh

export JAVA_HOME="software/win64/jdk1.6.0_18"
export CATALINA_HOME="software/win64/apache-tomcat-6.0.43"
export CATALINA_BASE=$CATALINA_HOME
export JAVA_OPTS="-ms128m -mx768m -javaagent:$CATALINA_HOME/lib/aspectjweaver.jar -DpageDebuggingJarPath=$CATALINA_HOME/lib/page-debugging.jar"
export JPDA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9999"

rm $CATALINA_BASE/webapps/*.war
rm -rf $CATALINA_BASE/logs/*
rm -rf $CATALINA_BASE/temp/*
rm -rf $CATALINA_BASE/webapps/bx
rm -rf $CATALINA_BASE/work

# deploy new war to webapps
cp -rf target/bx-0.0.1-SNAPSHOT/WEB-INF/lib/* D:/source/bx/bx/src/main/webapp/WEB-INF/lib
cp target/bx-0.0.1-SNAPSHOT.war $CATALINA_BASE/webapps/bx.war

$CATALINA_HOME/bin/catalina.sh jpda run