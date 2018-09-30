package com.cep.sensor.analytics.topology.utils.refers;

/**
 * Enumeration that contains required JSON keys in an JSON Object based on our Case 
 */
public enum EsperEventIDFields {

   ID("id"),
   TIMEID("timeid");

   private String field_name;

   private EsperEventIDFields(String s) {
      this.field_name = s;
   }

   public String getValue() {
      return this.field_name;
   }

}
