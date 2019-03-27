#!/bin/bash 
port=8080
appName=bigdata-event-tracking-receiver-$port
classpath=com.realMoney.StartApplication

current_path=`pwd`
case "`uname`" in
    Linux)
		bin_abs_path=$(readlink -f $(dirname $0))
		;;
	*)
		bin_abs_path=`cd $(dirname $0); pwd`
		;;
esac
base=${bin_abs_path}/..
log_configurationFile=$base/conf/log4j.properties
export LANG=en_US.UTF-8
export BASE=$base

if [ -f $base/bin/$appName.pid ] ; then
	echo "found $appName.pid , Please run stop.sh first ,then startup.sh" 2>&2
    exit 1
fi

## set java path
if [ -z "$JAVA" ] ; then
  JAVA=$(which java)
fi


if [ -z "$JAVA" ]; then
  	echo "Cannot find a Java JDK. Please set either set JAVA or put java (>=1.5) in your PATH." 2>&2
    exit 1
fi


str=`file $JAVA_HOME/bin/java | grep 64-bit`
if [ -n "$str" ]; then
	JAVA_OPTS="-server -Xms2048m -Xmx2048m -Xmn1024m -XX:SurvivorRatio=2 -XX:PermSize=96m -XX:MaxPermSize=256m -Xss256k -XX:-UseAdaptiveSizePolicy -XX:MaxTenuringThreshold=15 -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:+HeapDumpOnOutOfMemoryError"
else
	JAVA_OPTS="-server -Xms1024m -Xmx1024m -XX:NewSize=256m -XX:MaxNewSize=256m -XX:MaxPermSize=128m "
fi

JAVA_OPTS=" $JAVA_OPTS -Djava.awt.headless=true -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8"
METRICS_COLLECTOR_OPTS="-DappName=$appName -Dlog.configuration=file:$log_configurationFile"


if [ -e $log_configurationFile ]
then 
	
	for i in $base/lib/*;
		do CLASSPATH=$i:"$CLASSPATH";
	done
 	CLASSPATH="$base/conf:$CLASSPATH";
 	
 	echo "cd to $bin_abs_path for workaround relative path"
  	cd $bin_abs_path
 	
	echo LOG CONFIGURATION : $log_configurationFile
	echo CLASSPATH :$CLASSPATH

	$JAVA $JAVA_OPTS $JAVA_DEBUG_OPT $METRICS_COLLECTOR_OPTS -classpath .:$CLASSPATH $classpath  --server.port=$port 1>>$base/bin/nohup.out 2>&1 &

	echo $! > $base/bin/$appName.pid
	echo "cd to $current_path for continue"
  	cd $current_path
else 
	echo "log configration file($log_configurationFile) is not exist,please create then first!"
fi
