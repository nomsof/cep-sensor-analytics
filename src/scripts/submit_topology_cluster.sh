#!/bin/bash

. env.sh

$STORM_HOME/bin/storm jar $PROJECT_PATH/target/$JAR_NAME org.apache.storm.flux.Flux  --remote -c nimbus.host=localhost $PROJECT_PATH/src/flux-config/cluster-topology.yaml
