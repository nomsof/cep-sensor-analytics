package com.cep.sensor.analytics.topology.utils.configs;

import java.io.Serializable;
import java.util.Arrays;

/**
 * EsperConfig class has all required elements that needed by EsperBolt to initialize.
 * It used as a pass-in object in ExtendedEsperBolt constructor
 */
public class EsperConfig implements Serializable {

   private static final long serialVersionUID = -2261806879726408921L;

   private String query;
   private String[] streams;

   public EsperConfig(String query) {
      super();
      this.query = query;
   }

   public String getQuery() {
      return query;
   }

   public void setQuery(String query) {
      this.query = query;
   }

   public String[] getStreams() {
      return streams;
   }

   public void setStreams(String[] streams) {
      this.streams = streams;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EsperConfig [");
      if (query != null) builder.append("query=").append(query).append(", ");
      if (streams != null) builder.append("streams=").append(Arrays.toString(streams));
      builder.append("]");
      return builder.toString();
   }

}
