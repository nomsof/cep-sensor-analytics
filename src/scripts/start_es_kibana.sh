#!/bin/bash

# Starts all ES/Kibana components

. env.sh

# Start ES
ES_LOG_FILE=`mktemp`
echo "[`date`] - Starting ES"
$ES_HOME/bin/elasticsearch > $ES_LOG_FILE $2>&1 &
ES_PID=$!

if [ "$?" -ne "0" ]; then
   echo "Something went wrong during ES server start";
   exit;
fi


sleep 20;


# Start Kibana
KIB_LOG_FILE=`mktemp`
echo "[`date`] - Starting Kibana"
$KIBANA_HOME/bin/kibana > $KIB_LOG_FILE $2>&1 &
KIB_PID=$!

if [ "$?" -ne "0" ]; then
   echo "Something went wrong during Kibana start";
   exit;
fi



ctrl_c() { #if ctrl+C in on
	echo "exiting...."
	echo "Stoping ES"
	kill -9 $ES_PID
	sleep 2

	echo "Stoping Kibana"
	kill -9 $KIB_PID
	sleep 2

	echo "Deleting logs"
	rm -f $ES_LOG_FILE
	rm -f $KIB_LOG_FILE
	exit
}

trap ctrl_c SIGINT #trap the ctrl+c command

echo "Waiting.  Pid=$$"
while :
do
   sleep 10 &
   wait $!
done

