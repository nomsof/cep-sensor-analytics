package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Esper Data Outcome Stream Class
 *
 */
public class AvgPersons implements Serializable {

   private static final long serialVersionUID = -9018112363846952709L;

   private String id;
   private String antenna;
   private Date timeid;
   private Integer cnt;

   public AvgPersons() {
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
    * @return the cnt
    */
   public Integer getCnt() {
      return cnt;
   }

   /**
    * @param cnt the cnt to set
    */
   public void setCnt(Integer cnt) {
      this.cnt = cnt;
   }

   /**
    * @return the serialversionuid
    */
   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("AvgPersons [");
      if (id != null) builder.append("id=").append(id).append(", ");
      if (antenna != null) builder.append("antenna=").append(antenna).append(", ");
      builder.append("timeid=").append(timeid).append(", cnt=").append(cnt).append("]");
      return builder.toString();
   }

}
