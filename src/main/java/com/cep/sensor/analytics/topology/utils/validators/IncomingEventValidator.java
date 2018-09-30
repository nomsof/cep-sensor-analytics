package com.cep.sensor.analytics.topology.utils.validators;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cep.sensor.analytics.domain.Event;
import com.cep.sensor.analytics.topology.utils.refers.JsonFields;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * IncomingEventValidator is's a class to handle txt or json events and coverts them into Event
 * domain class
 *
 */
public class IncomingEventValidator implements Serializable {

   private static final long serialVersionUID = -9095336519696019192L;

   private static final Logger LOG = LoggerFactory.getLogger(IncomingEventValidator.class);

   private SimpleDateFormat _dateFormat;

   private ObjectMapper mapper;

   public IncomingEventValidator() {
      this._dateFormat = new SimpleDateFormat("hh:mm:ss.SSS");
      this.mapper = new ObjectMapper();
      this.mapper.setSerializationInclusion(Include.NON_NULL);
   }

   /*Validate Incoming Raw events and construct an Event Java Object*/
   public Event validateTxtAndConvert(String tuple) {
      String[] result;

      if (tuple == null) {
         return null;
      }

      if (tuple.length() < 10) {
         return null;
      }

      if (!tuple.startsWith("<") || !tuple.endsWith(">")) {
         return null;
      } else {
         tuple = tuple.replace("<", "").replace(">", "");
      }

      result = tuple.split(",");
      if (result.length != 3) {
         return null;
      }

      Event eventObj = new Event();

      eventObj.setUid(result[0]);
      eventObj.setAntenna(Integer.parseInt(result[1]));

      try {
         Date td = _dateFormat.parse(result[2]);
         eventObj.setStartDate(new Date());
         eventObj.getStartDate().setHours(td.getHours());
         eventObj.getStartDate().setMinutes(td.getMinutes());
         eventObj.getStartDate().setSeconds(td.getSeconds());
      } catch (ParseException e) {
         LOG.error("Cannot convert string to time [{}]", result[2]);
         return null;
      }

      return eventObj;
   }

   /*Validate Incoming JSON Format events and construct an Event Java Object*/
   public Event validateJsonAndConvert(String tuple) {

      if (tuple == null) {
         return null;
      }

      JsonNode jsonEvent = null;
      try {
         jsonEvent = mapper.readTree(tuple);
      } catch (IOException e1) {
         LOG.error("Cannot convert event to json [{}]", tuple);
         return null;
      }

      if (jsonEvent == null) {
         return null;
      }

      Event eventObj = new Event();

      eventObj.setUid(jsonEvent.get(JsonFields.TAG.getValue()).asText());
      eventObj.setAntenna(jsonEvent.get(JsonFields.CHECKPOINT.getValue()).asInt());

      try {
         eventObj.setStartDate(_dateFormat.parse(jsonEvent.get(JsonFields.TIMESTAMP.getValue()).asText()));
      } catch (ParseException e) {
         LOG.error("Cannot convert string to time [{}]", jsonEvent.get(JsonFields.TIMESTAMP.getValue()).asText());
         return null;
      }

      return eventObj;
   }

}
