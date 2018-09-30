#!/bin/bash

# Initializes enviroment and generates kafka-topics that needed for Project execution
# If topic exists, script delete it and recreate that topic

. ./env.sh

for tp in $TOPICS;
do
	$KAFKA_HOME/bin/kafka-topics.sh --zookeeper localhost:2181 --list | grep $tp
	if [ "$?" -eq "0" ];
	then
		echo "TOPIC ALREADY EXISTS .... DELETING IT"
		$KAFKA_HOME/bin/kafka-topics.sh --zookeeper localhost:2181 --delete --topic $tp;
		sleep 5;
	fi

	echo "Creating a new one topic[$tp]"
	$KAFKA_HOME/bin/kafka-topics.sh --zookeeper localhost:2181 --create --topic $tp --replication-factor 1 --partitions 1;

done
