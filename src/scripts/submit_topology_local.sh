#!/bin/bash

. env.sh

$STORM_HOME/bin/storm jar $PROJECT_PATH/target/$JAR_NAME org.apache.storm.flux.Flux --local --sleep 1800000  $PROJECT_PATH/src/flux-config/local-topology.yaml
