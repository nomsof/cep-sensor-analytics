package com.cep.sensor.analytics.topology.bolt;

import java.util.Arrays;
import java.util.Collections;

import com.cep.sensor.analytics.topology.bolt.external.EsperBolt;
import com.cep.sensor.analytics.topology.utils.configs.EsperConfig;

/**
 * ExtendedEsperBolt prepares EsperBolt using EsperConfig in order
 * to be initialized through Flux config
 */
public class ExtendedEsperBolt extends EsperBolt {

   private static final long serialVersionUID = 4151409193345862377L;

   public ExtendedEsperBolt(EsperConfig conf) throws ClassNotFoundException{
      super();
      this.setStreamIDs(Arrays.asList(conf.getStreams()));
      this.addStatements(Collections.singleton(conf.getQuery()));
   }
}
