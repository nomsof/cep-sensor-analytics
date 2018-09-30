package com.cep.sensor.analytics.topology.utils.refers;

/**
 * Enumeration that contains required JSON keys in an JSON Object based on our Case 
 */
public enum JsonFields {

   TAG("tag"),
   CHECKPOINT("checkpoint"),
   TIMESTAMP("timestamp");

   private String field_name;

   private JsonFields(String s) {
      this.field_name = s;
   }

   public String getValue() {
      return this.field_name;
   }

}
