package com.cep.sensor.analytics.topology.bolt.external;

import static java.util.Objects.requireNonNull;
import static org.apache.http.util.Args.notBlank;

import java.util.Map;

import com.cep.sensor.analytics.common.EsConfig;
import com.cep.sensor.analytics.common.StormElasticSearchClient;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import com.cep.sensor.analytics.topology.bolt.external.BaseTickTupleAwareRichBolt;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractEsBolt extends BaseTickTupleAwareRichBolt {

   private static final long serialVersionUID = -9162873745731463212L;

   private static final Logger LOG = LoggerFactory.getLogger(AbstractEsBolt.class);

   protected static RestClient client;
   protected final static ObjectMapper objectMapper = new ObjectMapper();

   protected OutputCollector collector;
   private EsConfig esConfig;

   public AbstractEsBolt(EsConfig esConfig) {
      this.esConfig = requireNonNull(esConfig);
   }

   @Override
   public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
      try {
         this.collector = outputCollector;
         synchronized (AbstractEsBolt.class) {
            if (client == null) {
               client = new StormElasticSearchClient(esConfig).construct();
            }
         }
      } catch (Exception e) {
         LOG.warn("unable to initialize EsBolt ", e);
      }
   }

   @Override
   public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {}

   /**
    * Construct an Elasticsearch endpoint from the provided index, type and
    * id.
    * 
    * @param index - required; name of Elasticsearch index
    * @param type - optional; name of Elasticsearch type
    * @param id - optional; Elasticsearch document ID
    * @return the index, type and id concatenated with '/'.
    */
   static String getEndpoint(String index, String type, String id) {
      requireNonNull(index);
      notBlank(index, "index");

      StringBuilder sb = new StringBuilder();
      sb.append("/").append(index);
      if (!(type == null || type.isEmpty())) {
         sb.append("/").append(type);
      }
      if (!(id == null || id.isEmpty())) {
         sb.append("/").append(id);
      }
      return sb.toString();
   }

   static RestClient getClient() {
      return AbstractEsBolt.client;
   }

   static void replaceClient(RestClient client) {
      AbstractEsBolt.client = client;
   }
}  