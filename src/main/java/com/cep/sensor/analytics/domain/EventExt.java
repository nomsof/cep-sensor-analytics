package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

public class EventExt implements Serializable {
   /**
    * Main input event class
    */
   private static final long serialVersionUID = 2252439446886031519L;

   private int new_antenna;
   private int old_antenna;
   private Date start_time;
   private Date change_time;
   private String uid;

   public EventExt() {
      super();
   }


   /**
    * @return the new_antenna
    */
   public int getNew_antenna() {
      return new_antenna;
   }

   /**
    * @param new_antenna the new_antenna to set
    */
   public void setNew_antenna(int new_antenna) {
      this.new_antenna = new_antenna;
   }

   /**
    * @return the old_antenna
    */
   public int getOld_antenna() {
      return old_antenna;
   }

   /**
    * @param old_antenna the old_antenna to set
    */
   public void setOld_antenna(int old_antenna) {
      this.old_antenna = old_antenna;
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

   /**
    * @return the start_time
    */
   public Date getStart_time() {
      return start_time;
   }


   /**
    * @param start_time the start_time to set
    */
   public void setStart_time(Date start_time) {
      this.start_time = start_time;
   }


   /**
    * @return the change_time
    */
   public Date getChange_time() {
      return change_time;
   }


   /**
    * @param change_time the change_time to set
    */
   public void setChange_time(Date change_time) {
      this.change_time = change_time;
   }


   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((change_time == null) ? 0 : change_time.hashCode());
      result = prime * result + new_antenna;
      result = prime * result + old_antenna;
      result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
      result = prime * result + ((uid == null) ? 0 : uid.hashCode());
      return result;
   }


   /* (non-Javadoc)
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      EventExt other = (EventExt) obj;
      if (change_time == null) {
         if (other.change_time != null) return false;
      } else if (!change_time.equals(other.change_time)) return false;
      if (new_antenna != other.new_antenna) return false;
      if (old_antenna != other.old_antenna) return false;
      if (start_time == null) {
         if (other.start_time != null) return false;
      } else if (!start_time.equals(other.start_time)) return false;
      if (uid == null) {
         if (other.uid != null) return false;
      } else if (!uid.equals(other.uid)) return false;
      return true;
   }


   /* (non-Javadoc)
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString() {
      StringBuilder builder = new StringBuilder();
      builder.append("EventExt [new_antenna=").append(new_antenna).append(", old_antenna=").append(old_antenna)
            .append(", start_time=").append(start_time).append(", change_time=").append(change_time).append(", uid=")
            .append(uid).append("]");
      return builder.toString();
   }
}
