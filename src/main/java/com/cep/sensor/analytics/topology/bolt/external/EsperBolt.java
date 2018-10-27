package com.cep.sensor.analytics.topology.bolt.external;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.FailedException;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cep.sensor.analytics.domain.Event;
import com.cep.sensor.analytics.domain.EventExt;
import com.cep.sensor.analytics.topology.utils.refers.TopologyStreams;
import com.cep.sensor.analytics.utils.exceptions.EsperBoltException;
import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatementException;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.client.soda.EPStatementObjectModel;

public class EsperBolt extends BaseRichBolt implements UpdateListener {

   private static final long serialVersionUID = -581693166287235015L;
   private static final Logger LOG = LoggerFactory.getLogger(EsperBolt.class);

   private OutputCollector collector;
   private transient EPServiceProvider epService;
   private Set<String> statements;
   private List<String> streamIDs;
   private Set<EPStatementObjectModel> objectStatements;

   public static void checkEPLSyntax(String statement) throws EsperBoltException {
      Configuration cepConfig = new Configuration();
      EPServiceProvider service = EPServiceProviderManager.getDefaultProvider(cepConfig);
      service.initialize();
      try {
         service.getEPAdministrator().prepareEPL(statement);
      } catch (EPStatementException e) {
         throw new EsperBoltException(e.getMessage(), e);
      }
      service.destroy();
   }

   /**
    *
    * @param statements
    * @return
    */
   public void addStatements(Set<String> statements) {
      this.statements = statements;
   }

   /**
    *
    * @param sIDs
    */
   public void setStreamIDs(List<String> sIDs) {
      streamIDs = sIDs;
   }

   /**
    *
    * @param objectStatements
    * @return
    */
   public void addObjectStatemens(Set<EPStatementObjectModel> objectStatements) {
      this.objectStatements = objectStatements;
   }

   /**
    * {@inheritDoc}
    *
    * @param ofd
    */
   @Override
   public void declareOutputFields(OutputFieldsDeclarer ofd) {
      if (this.streamIDs == null) {
         throw new FailedException("outputTypes cannot be null");
      }
      for (String sID : this.streamIDs) {
         ofd.declareStream(sID, new Fields(TopologyStreams.INCOMING_TUPLE_VALID.getValue()));
      }
   }

   /**
    * {@inheritDoc}
    *
    * @param map
    * @param tc
    * @param oc
    */
   @Override
   public void prepare(@SuppressWarnings("rawtypes") Map map, TopologyContext tc, OutputCollector oc) {
      this.collector = oc;
      Configuration cepConfig = new Configuration();
      cepConfig.addEventTypeAutoName("com.cep.sensor.analytics.domain");
      this.epService = EPServiceProviderManager.getDefaultProvider(cepConfig);
      this.epService.initialize();
      if (!processStatemens()) {
         throw new FailedException("At least one type of statement has to be not empty");
      }
   }

   private boolean processStatemens() {
      boolean hasStatemens = false;
      if (this.statements != null) {
         for (String s : this.statements) {
            this.epService.getEPAdministrator().createEPL(s).addListener(this);
            hasStatemens = true;
         }
      }
      if (this.objectStatements != null) {
         for (EPStatementObjectModel s : this.objectStatements) {
            this.epService.getEPAdministrator().create(s).addListener(this);
            hasStatemens = true;
         }
      }
      return hasStatemens;
   }

   /**
    * {@inheritDoc}
    *
    * @param tuple
    */
   @Override
   public void execute(Tuple tuple) {
      if(tuple != null) {
         if(tuple.getSourceGlobalStreamId().get_streamId().equals("recurring")){
            this.epService.getEPRuntime().sendEvent(convertMapToObject((HashMap<Object,Object>)tuple.getValue(0)));
         }else {
            this.epService.getEPRuntime().sendEvent((Event) tuple.getValue(tuple.size()-1));
         }
      }
      collector.ack(tuple);
   }

   private EventExt convertMapToObject(HashMap<Object,Object> input) {
      EventExt event = new EventExt();
      event.setNew_antenna((int)input.get("new_antenna"));
      event.setOld_antenna((int)input.get("old_antenna"));
      event.setStart_time((Date)input.get("start_time"));
      event.setChange_time((Date)input.get("change_time"));
      event.setUid((String)input.get("uid"));
      return event;
   }


   /**
    * {@inheritDoc}
    */
   @Override
   public void cleanup() {
      if (this.epService != null) {
         this.epService.destroy();
      }
   }

   /**
    * {@inheritDoc}
    *
    * @param newEvents
    * @param oldEvents
    */
   @Override
   public void update(EventBean[] newEvents, EventBean[] oldEvents) {
      if (newEvents == null) {
         return;
      }
      for (EventBean newEvent : newEvents) {
         for (String sID : this.streamIDs) {
            collector.emit(sID, new Values(newEvent.getUnderlying()));
         }
      }
   }

}