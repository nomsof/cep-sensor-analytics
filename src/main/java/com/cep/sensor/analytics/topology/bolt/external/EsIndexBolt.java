package com.cep.sensor.analytics.topology.bolt.external;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import com.cep.sensor.analytics.common.DefaultEsTupleMapper;
import com.cep.sensor.analytics.common.EsConfig;
import com.cep.sensor.analytics.common.EsTupleMapper;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

/**
 * Basic bolt for storing tuple to ES document.
 */
public class EsIndexBolt extends AbstractEsBolt {
   private final EsTupleMapper tupleMapper;

   /**
    * EsIndexBolt constructor
    * 
    * @param esConfig Elasticsearch configuration containing node addresses {@link EsConfig}
    */
   public EsIndexBolt(EsConfig esConfig) {
      this(esConfig, new DefaultEsTupleMapper());
   }
   
   public EsIndexBolt(String esip) {
      this(new EsConfig(esip), new DefaultEsTupleMapper());
   }


   /**
    * EsIndexBolt constructor
    * 
    * @param esConfig Elasticsearch configuration containing node addresses {@link EsConfig}
    * @param tupleMapper Tuple to ES document mapper {@link EsTupleMapper}
    */
   public EsIndexBolt(EsConfig esConfig, EsTupleMapper tupleMapper) {
      super(esConfig);
      this.tupleMapper = requireNonNull(tupleMapper);
   }

   @Override
   public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
      super.prepare(map, topologyContext, outputCollector);
   }

   /**
    * {@inheritDoc}
    * Tuple should have relevant fields (source, index, type, id) for tupleMapper to extract ES
    * document.
    */
   @Override
   public void process(Tuple tuple) {
      try {
         String source = tupleMapper.getSource(tuple);
         String index = tupleMapper.getIndex(tuple);
         String type = tupleMapper.getType(tuple);
         String id = tupleMapper.getId(tuple);
         Map<String, String> params = tupleMapper.getParams(tuple, new HashMap<>());

         client.performRequest("put", getEndpoint(index, type, id), params, new StringEntity(source));
         collector.ack(tuple);
      } catch (Exception e) {
         collector.reportError(e);
         collector.fail(tuple);
      }
   }

   @Override
   public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {}
}