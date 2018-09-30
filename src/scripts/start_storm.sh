#!/bin/bash

# Starts all Storm components
# Storm UI starts on localhost:8086

. env.sh

# Start Storm Nimbus
SN_LOG_FILE=`mktemp`
echo "[`date`] - Starting Nimbus"
$STORM_HOME/bin/storm nimbus > $SN_LOG_FILE $2>&1 &
SN_PID=$!

if [ "$?" -ne "0" ]; then
   echo "Something went wrong during Nimbus server start";
   exit;
fi


sleep 20;


# Start Storm Supervisor
SS_LOG_FILE=`mktemp`
echo "[`date`] - Starting Storm Supervisor"
$STORM_HOME/bin/storm supervisor > $SS_LOG_FILE $2>&1 &
SS_PID=$!

if [ "$?" -ne "0" ]; then
   echo "Something went wrong during Storm Supervisor server start";
   exit;
fi

sleep 20;


# Start Storm UI
SU_LOG_FILE=`mktemp`
echo "[`date`] - Starting Storm UI"
$STORM_HOME/bin/storm ui > $SU_LOG_FILE $2>&1 &
SU_PID=$!

if [ "$?" -ne "0" ]; then
   echo "Something went wrong during Storm UI start";
   exit;
fi


ctrl_c() { #if ctrl+C in on
	echo "exiting...."
	echo "Stoping Storm UI"
	kill -9 $SU_PID
	sleep 2

	echo "Stoping Storm Supervisor"
	kill -9 $SS_PID
	sleep 2

	echo "Stoping Storm Nimbus"
	kill -9 $SN_PID
	sleep 2

	echo "Deleting logs"
	rm -f $SN_LOG_FILE
	rm -f $SS_LOG_FILE
	rm -f $SU_LOG_FILE
	exit
}

trap ctrl_c SIGINT #trap the ctrl+c command

echo "Waiting.  Pid=$$"
while :
do
   sleep 10 &
   wait $!
done

