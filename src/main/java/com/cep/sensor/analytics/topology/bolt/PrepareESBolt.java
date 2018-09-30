package com.cep.sensor.analytics.topology.bolt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

import com.cep.sensor.analytics.topology.utils.refers.EsperEventIDFields;
import com.cep.sensor.analytics.topology.utils.refers.TopologyFields;
import com.cep.sensor.analytics.topology.utils.refers.TopologyStreams;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Coordinator Bolt that handles incoming events by routing them into different
 * streams using the proper key
 */
public class PrepareESBolt extends BaseRichBolt {

   private static final long serialVersionUID = 281741764908822190L;

   private static final Logger LOG = LoggerFactory.getLogger(PrepareESBolt.class);

   private Map _stormConf;
   private TopologyContext _context;
   private OutputCollector _collector;
   private String index;
   private transient ObjectMapper mapper;
   private transient SimpleDateFormat dt;

   public PrepareESBolt() {
      super();
   }

   @Override
   public void prepare(Map stormConf, TopologyContext context, OutputCollector collector) {
      this._stormConf = stormConf;
      this._context = context;
      this._collector = collector;
      this.mapper = new ObjectMapper();
      this.dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
   }

   @Override
   public void execute(Tuple tuple) {
      if (tuple.getValues() != null && tuple.getValues().size() > 0) {
         LOG.info(tuple.getValues().toString());
         String id = getID((HashMap<Object, Object>) tuple.getValue(0));
         if (id != null) {
            _collector.emit(TopologyStreams.INCOMING_TUPLE_VALID.getValue(),
                  new Values(getSource((HashMap<Object, Object>) tuple.getValue(0)), tuple.getSourceStreamId(),
                        tuple.getSourceStreamId(), id));
         }
      }
      _collector.ack(tuple);
   }

   private String getID(HashMap<Object, Object> values) {
      Object part1 = (Object) (values.containsKey(EsperEventIDFields.ID.getValue())
            ? values.get(EsperEventIDFields.ID.getValue()) : "");
      Object part2 = (Object) (values.containsKey(EsperEventIDFields.TIMEID.getValue())
            ? values.get(EsperEventIDFields.TIMEID.getValue()) : "");
      if (part1 == null || part2 == null) {
         return null;
      } else {
         return (part1.hashCode() + "_" + ((Date) part2).getTime());
      }
   }

   private String getSource(HashMap<Object, Object> values) {
      ObjectNode node = mapper.createObjectNode();
      try {
         for (Object key : values.keySet()) {
            if (values.get(key) instanceof Date) {
               node.put(key.toString(), (long)((Date)values.get(key)).getTime());
            } else if (values.get(key) instanceof String) {
               node.put(key.toString(), (String) values.get(key));
            } else if (values.get(key) instanceof Integer) {
               node.put(key.toString(), (Integer) values.get(key));
            } else if (values.get(key) instanceof Long) {
               node.put(key.toString(), (Long) values.get(key));
            }
         }
         return mapper.writeValueAsString(node);
      } catch (Exception e) {
         LOG.error(values.toString(),e);
         return "{}";
      }
   }

   @Override
   public void declareOutputFields(OutputFieldsDeclarer declarer) {
      declarer.declareStream(TopologyStreams.INCOMING_TUPLE_VALID.getValue(),
            new Fields(TopologyFields.TUPLE_SOURCE.getValue(), TopologyFields.TUPLE_INDEX.getValue(),
                  TopologyFields.TUPLE_TYPE.getValue(), TopologyFields.TUPLE_ID.getValue()));
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
