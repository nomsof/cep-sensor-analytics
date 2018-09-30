package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Main full event class
 */

public class EventFull implements Serializable {

   private static final long serialVersionUID = 7677708112724305777L;

   private String uid;
   private int antenna;
   private Date startDate;
   private Date endDate;

   public EventFull() {
      super();
   }

   public EventFull(String uid, int antenna, Date startDate, Date endDate) {
      super();
      this.uid = uid;
      this.antenna = antenna;
      this.startDate = startDate;
      this.endDate = endDate;
   }

   public EventFull(Event event) {
      super();
      this.uid = event.getUid();
      this.antenna = event.getAntenna();
      this.startDate = event.getStartDate();
      this.endDate = null;
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
    * @return the endDate
    */
   public Date getEndDate() {
      return endDate;
   }

   /**
    * @param endDate the endDate to set
    */
   public void setEndDate(Date endDate) {
      this.endDate = endDate;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + antenna;
      result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
      result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
      result = prime * result + ((uid == null) ? 0 : uid.hashCode());
      return result;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (!(obj instanceof EventFull)) {
         return false;
      }
      EventFull other = (EventFull) obj;
      if (antenna != other.antenna) {
         return false;
      }
      if (endDate == null) {
         if (other.endDate != null) {
            return false;
         }
      } else if (!endDate.equals(other.endDate)) {
         return false;
      }
      if (startDate == null) {
         if (other.startDate != null) {
            return false;
         }
      } else if (!startDate.equals(other.startDate)) {
         return false;
      }
      if (uid == null) {
         if (other.uid != null) {
            return false;
         }
      } else if (!uid.equals(other.uid)) {
         return false;
      }
      return true;
   }

   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EventFull [");
      if (uid != null) builder.append("uid=").append(uid).append(", ");
      builder.append("antenna=").append(antenna).append(", ");
      if (startDate != null) builder.append("startDate=").append(startDate).append(", ");
      if (endDate != null) builder.append("endDate=").append(endDate);
      builder.append("]");
      return builder.toString();
   }

}
