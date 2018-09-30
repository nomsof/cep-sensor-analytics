package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Esper Data Outcome Stream Class
 *
 */
public class PersonVelocity implements Serializable {

   private static final long serialVersionUID = 6745988539156359026L;

   private String uid;
   private String id;
   private Date timeid;
   private Long avgTime;

   /**
    * @return the uid
    */
   public String getUid() {
      return uid;
   }

   /**
    * @param uid the uid to set
    */
   public void setUid(String uid) {
      this.uid = uid;
   }

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
    * @return the avgTime
    */
   public Long getAvgTime() {
      return avgTime;
   }

   /**
    * @param avgTime the avgTime to set
    */
   public void setAvgTime(Long avgTime) {
      this.avgTime = avgTime;
   }

   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("PersonVelocity [");
      if (uid != null) builder.append("uid=").append(uid).append(", ");
      if (id != null) builder.append("id=").append(id).append(", ");
      builder.append("avgTime=").append(avgTime).append("]");
      return builder.toString();
   }

}
