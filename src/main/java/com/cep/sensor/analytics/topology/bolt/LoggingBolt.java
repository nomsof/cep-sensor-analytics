package com.cep.sensor.analytics.topology.bolt;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Tuple;

/**
 * Default logging bolt
 */
public class LoggingBolt extends BaseRichBolt {

   private static final long serialVersionUID = 1L;
   private static final Logger LOG = Logger.getLogger(LoggingBolt.class);

   private OutputCollector _collector;

   private String boltname = "";

   public LoggingBolt() {

   }

   public LoggingBolt(String name) {
      this.boltname = name;
   }

   public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
      _collector = collector;
   }

   public void execute(Tuple tuple) {
      LOG.info(tuple.getSourceComponent() + "[" + tuple.getSourceStreamId() + "]  => " + tuple.getValues());
      _collector.ack(tuple);
   }

   public void declareOutputFields(OutputFieldsDeclarer declarer) {}

   /***************************************************/
   /************ GETTERS - SETTERS ********************/
   /***************************************************/

   /**
    * @return the _collector
    */
   public OutputCollector get_collector() {
      return _collector;
   }

   /**
    * @param _collector the _collector to set
    */
   public void set_collector(OutputCollector _collector) {
      this._collector = _collector;
   }

   /**
    * @return the boltname
    */
   public String getBoltname() {
      return boltname;
   }

   /**
    * @param boltname the boltname to set
    */
   public void setBoltname(String boltname) {
      this.boltname = boltname;
   }

}