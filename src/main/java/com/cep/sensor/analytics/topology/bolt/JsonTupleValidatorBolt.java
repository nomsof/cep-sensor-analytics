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
import com.cep.sensor.analytics.topology.utils.refers.TopologyFields;
import com.cep.sensor.analytics.topology.utils.refers.TopologyStreams;
import com.cep.sensor.analytics.topology.utils.validators.IncomingEventValidator;

/**
 * JsonTupleValidatorBolt reads from a kafka Spout event per event.
 * Parse that incoming event as a JSON format to Event domain class and send it to another bolt
 */
public class JsonTupleValidatorBolt extends BaseRichBolt {

   private static final long serialVersionUID = 993526026038209277L;

   private static final Logger LOG = LoggerFactory.getLogger(JsonTupleValidatorBolt.class);

   private Map<String, Object> _stormConf;
   private TopologyContext _context;
   private OutputCollector _collector;
   private transient IncomingEventValidator validator;

   @Override
   public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
      this._stormConf = stormConf;
      this._context = context;
      this._collector = collector;
      this.validator = new IncomingEventValidator();
   }

   @Override
   public void execute(Tuple tuple) {
      Event eventObj = validator.validateJsonAndConvert(tuple.getString(0));
      if (eventObj == null) {
         LOG.error("Invalid input event [{}]", tuple.getString(0));
         this._collector.emit(TopologyStreams.INCOMING_TUPLE_ERROR.getValue(), new Values(eventObj));
      } else {
         this._collector.emit(TopologyStreams.INCOMING_TUPLE_VALID.getValue(), new Values(eventObj));
      }
   }

   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declareStream(TopologyStreams.INCOMING_TUPLE_VALID.getValue(),
            new Fields(TopologyFields.TUPLE_MSG.getValue()));

      declarer.declareStream(TopologyStreams.INCOMING_TUPLE_ERROR.getValue(),
            new Fields(TopologyFields.TUPLE_MSG.getValue()));
   }

   /***************************************************/
   /*********** GETTERS - SETTERS ********************/
   /***************************************************/

   /**
    * @return the _stormConf
    */
   public Map<String, Object> get_stormConf() {
      return _stormConf;
   }

   /**
    * @param _stormConf the _stormConf to set
    */
   public void set_stormConf(Map<String, Object> _stormConf) {
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
