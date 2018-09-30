package com.cep.sensor.analytics.topology.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cep.sensor.analytics.domain.Event;
import com.cep.sensor.analytics.topology.utils.beans.EventStatusHandler;
import com.cep.sensor.analytics.topology.utils.refers.TopologyFields;
import com.cep.sensor.analytics.topology.utils.refers.TopologyStreams;

/**
 *
 * Coordinator Bolt that handles incoming events by routing them into
 * different streams using the proper key
 */
public class CoordinatorBolt extends BaseRichBolt {

   private static final long serialVersionUID = 2939293192643403049L;

   private static final Logger LOG = LoggerFactory.getLogger(CoordinatorBolt.class);

   private Map _stormConf;
   private TopologyContext _context;
   private OutputCollector _collector;
   private EventStatusHandler handler;

   @Override
   public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
      this._stormConf = stormConf;
      this._context = context;
      this._collector = collector;
   }

   @Override
   public void execute(Tuple tuple) {
      Event eventobj = (Event) tuple.getValue(0);

      if (eventobj != null) {
         _collector.emit(TopologyStreams.KEY_ANTENNA.getValue(), new Values(eventobj.getAntenna(), eventobj));
         _collector.emit(TopologyStreams.KEY_UID.getValue(), new Values(eventobj.getUid(), eventobj));
      } else {
         _collector.ack(tuple);
      }
   }

   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declareStream(TopologyStreams.KEY_ANTENNA.getValue(),
            new Fields(TopologyFields.TUPLE_KEY.getValue(), TopologyFields.TUPLE_MSG.getValue()));
      declarer.declareStream(TopologyStreams.KEY_UID.getValue(),
            new Fields(TopologyFields.TUPLE_KEY.getValue(), TopologyFields.TUPLE_MSG.getValue()));
   }

   /**
    * @return the _stormConf
    */
   public Map get_stormConf() {
      return _stormConf;
   }

   /**
    * @param _stormConf the _stormConf to set
    */
   public void set_stormConf(Map _stormConf) {
      this._stormConf = _stormConf;
   }

   /**
    * @return the _context
    */
   public TopologyContext get_context() {
      return _context;
   }

   /**
    * @param _context the _context to set
    */
   public void set_context(TopologyContext _context) {
      this._context = _context;
   }

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
}
