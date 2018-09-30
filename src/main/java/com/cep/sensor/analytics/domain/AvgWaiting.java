package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Esper Data Outcome Stream Class
 *
 */
public class AvgWaiting implements Serializable {

   private static final Long serialVersionUID = -7660201974545206196L;

   private String id;
   private String antenna;
   private Date timeid;
   private Long minTime;
   private Long maxTime;

   public AvgWaiting() {
      super();
   }

   /***************************************************/
   /************ GETTERS - SETTERS ********************/
   /***************************************************/

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
   public Long getMinTime() {
      return minTime;
   }

   /**
    * @param minTime the minTime to set
    */
   public void setMinTime(Long minTime) {
      this.minTime = minTime;
   }

   /**
    * @return the maxTime
    */
   public Long getMaxTime() {
      return maxTime;
   }

   /**
    * @param maxTime the maxTime to set
    */
   public void setMaxTime(Long maxTime) {
      this.maxTime = maxTime;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("AvgWaiting [");
      if (id != null) builder.append("id=").append(id).append(", ");
      if (antenna != null) builder.append("antenna=").append(antenna).append(", ");
      builder.append("timeid=").append(timeid).append(", minTime=").append(minTime).append(", maxTime=").append(maxTime)
            .append("]");
      return builder.toString();
   }

}
