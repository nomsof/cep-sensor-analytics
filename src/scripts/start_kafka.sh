#!/bin/bash

# Starts all Kafka components

. env.sh

# Start Zookeeper
ZK_LOG_FILE=`mktemp`
echo "[`date`] - Starting ZK"
$KAFKA_HOME/bin/zookeeper-server-start.sh $KAFKA_HOME/config/zookeeper.properties > $ZK_LOG_FILE $2>&1 &
ZK_PID=$!

if [ "$?" -ne "0" ]; then
   echo "Something went wrong during ZK server start";
   exit;
fi


sleep 10;


# Start Kafka Server
KS_LOG_FILE=`mktemp`
echo "[`date`] - Starting Kafka"
$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties > $KS_LOG_FILE $2>&1 &
KS_PID=$!

if [ "$?" -ne "0" ]; then
   echo "Something went wrong during Kakfa server start";
   exit;
fi



ctrl_c() { #if ctrl+C in on
	echo "exiting...."
	echo "Stoping ZK"
	kill -9 $ZK_PID
	sleep 2

	echo "Stoping Kafka"
	kill -9 $KS_PID
	sleep 2

	echo "Deleting logs"
	rm -f $ZK_LOG_FILE
	rm -f $KS_LOG_FILE
	exit
}

trap ctrl_c SIGINT #trap the ctrl+c command

echo "Waiting.  Pid=$$"
while :
do
   sleep 10 &
   wait $!
done

