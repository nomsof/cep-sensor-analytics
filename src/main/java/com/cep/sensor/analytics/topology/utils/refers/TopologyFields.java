package com.cep.sensor.analytics.topology.utils.refers;

/**
 * Enumeration that contains of different Storm Stream Field Names 
 */
public enum TopologyFields {

   TUPLE_KEY("TUPLE_KEY"),
   TUPLE_MSG("TULPE_MSG"),
   TUPLE_SOURCE("source"),
   TUPLE_INDEX("index"),
   TUPLE_TYPE("type"),
   TUPLE_ID("id");

   private String field_name;

   private TopologyFields(String s) {
      this.field_name = s;
   }

   public String getValue() {
      return this.field_name;
   }

}
