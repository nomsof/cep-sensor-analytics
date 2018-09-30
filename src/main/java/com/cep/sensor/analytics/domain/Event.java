package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable{
   /**
    * Main input event class
    */
   private static final long serialVersionUID = 2252439446886031519L;

   private Date startDate;
   private int antenna;
   private String uid;
   
   public Event() {
      super();
   }

   
   public Event(Date startDate, int antenna, String id) {
      super();
      this.startDate = startDate;
      this.antenna = antenna;
      this.uid = id;
   }

   /***************************************************/
   /************ GETTERS - SETTERS ********************/
   /***************************************************/

   /**
    * @return the startDate
    */
   public Date getStartDate() {
      return startDate;
   }


   /**
    * @param startDate the startDate to set
    */
   public void setStartDate(Date startDate) {
      this.startDate = startDate;
   }


   /**
    * @return the antenna
    */
   public int getAntenna() {
      return antenna;
   }


   /**
    * @param antenna the antenna to set
    */
   public void setAntenna(int antenna) {
      this.antenna = antenna;
   }


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
    * @return the serialversionuid
    */
   public static long getSerialversionuid() {
      return serialVersionUID;
   }


   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + antenna;
      result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
      result = prime * result + ((uid == null) ? 0 : uid.hashCode());
      return result;
   }


   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Event other = (Event) obj;
      if (antenna != other.antenna) return false;
      if (startDate == null) {
         if (other.startDate != null) return false;
      } else if (!startDate.equals(other.startDate)) return false;
      if (uid == null) {
         if (other.uid != null) return false;
      } else if (!uid.equals(other.uid)) return false;
      return true;
   }


   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("Event [");
      if (startDate != null) builder.append("startDate=").append(startDate).append(", ");
      builder.append("antenna=").append(antenna).append(", ");
      if (uid != null) builder.append("uid=").append(uid);
      builder.append("]");
      return builder.toString();
   }


   
   
}
