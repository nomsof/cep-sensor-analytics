package com.cep.sensor.analytics.topology.utils.refers;

/**
 * Enumeration that contains of different Storm Stream Names 
 */
public enum TopologyStreams {

   INCOMING_TUPLE_ERROR("INCOMING_TUPLE_ERROR"),
   INCOMING_TUPLE_VALID("INCOMING_TUPLE_VALID"),
   ESPER_ROOMTRAFFIC("ESPER_ROOMTRAFFIC"),
   ESPER_AVGWAITING("ESPER_AVGWAITING"),
   ESPER_AVGPERSONS("ESPER_AVGPERSONS"),
   ESPER_AVGVELOCITY("ESPER_AVGVELOCITY"),
   KEY_ANTENNA("KEY_ANTENNA"),
   KEY_UID("KEY_UID");
   
   private String stream_name;
   
   private TopologyStreams(String s) {
      this.stream_name = s;
  }

  public String getValue() {
      return this.stream_name;
  }
}
