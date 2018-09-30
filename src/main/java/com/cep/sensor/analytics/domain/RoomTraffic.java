package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Esper Data Outcome Stream Class
 *
 */
public class RoomTraffic implements Serializable {

   private static final long serialVersionUID = 7426607990095649328L;

   private String id;
   private String antenna;
   private Date timeid;
   private Date minTime;
   private Integer counter;

   /**
    * @return the id
    */
   public String getId() {
      return id;
   }

   /**
    * @param id the id to set
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * @return the antenna
    */
   public String getAntenna() {
      return antenna;
   }

   /**
    * @param antenna the antenna to set
    */
   public void setAntenna(String antenna) {
      this.antenna = antenna;
   }

   /**
    * @return the timeid
    */
   public Date getTimeid() {
      return timeid;
   }

   /**
    * @param timeid the timeid to set
    */
   public void setTimeid(Date timeid) {
      this.timeid = timeid;
   }

   /**
    * @return the minTime
    */
   public Date getMinTime() {
      return minTime;
   }

   /**
    * @param minTime the minTime to set
    */
   public void setMinTime(Date minTime) {
      this.minTime = minTime;
   }

   /**
    * @return the counter
    */
   public Integer getCounter() {
      return counter;
   }

   /**
    * @param counter the counter to set
    */
   public void setCounter(Integer counter) {
      this.counter = counter;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("RoomTraffic [");
      if (id != null) builder.append("id=").append(id).append(", ");
      if (antenna != null) builder.append("antenna=").append(antenna).append(", ");
      if (timeid != null) builder.append("timeid=").append(timeid).append(", ");
      if (minTime != null) builder.append("minTime=").append(minTime).append(", ");
      if (counter != null) builder.append("counter=").append(counter);
      builder.append("]");
      return builder.toString();
   }

}
