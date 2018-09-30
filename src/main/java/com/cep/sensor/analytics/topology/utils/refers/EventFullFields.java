package com.cep.sensor.analytics.topology.utils.refers;

public enum EventFullFields {

   UUID("uid"),
   ANTENNA("antenna"),
   STARTDATE("startDate"),
   ENDDATE("endDate");

   private String field_name;

   private EventFullFields(String s) {
      this.field_name = s;
   }

   public String getValue() {
      return this.field_name;
   }

}
