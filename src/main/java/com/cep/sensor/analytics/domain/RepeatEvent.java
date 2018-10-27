package com.cep.sensor.analytics.domain;

import java.io.Serializable;
import java.util.Date;

public class RepeatEvent implements Serializable {
   /**
    * Main input event class
    */
   private static final long serialVersionUID = 2252439446886031519L;

   private int new_antenna;
   private int old_antenna;
   private Date start_time;
   private Date end_time;
   private String uid;
   private Date timeid;
   private String id;
   private int cnt;

   public RepeatEvent() {
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
    * @return the cnt
    */
   public int getCnt() {
      return cnt;
   }

   /**
    * @param cnt the cnt to set
    */
   public void setCnt(int cnt) {
      this.cnt = cnt;
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
    * @return the end_time
    */
   public Date getEnd_time() {
      return end_time;
   }

   /**
    * @param end_time the end_time to set
    */
   public void setEnd_time(Date end_time) {
      this.end_time = end_time;
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

   /* (non-Javadoc)
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + cnt;
      result = prime * result + ((end_time == null) ? 0 : end_time.hashCode());
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      result = prime * result + new_antenna;
      result = prime * result + old_antenna;
      result = prime * result + ((start_time == null) ? 0 : start_time.hashCode());
      result = prime * result + ((timeid == null) ? 0 : timeid.hashCode());
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
      RepeatEvent other = (RepeatEvent) obj;
      if (cnt != other.cnt) return false;
      if (end_time == null) {
         if (other.end_time != null) return false;
      } else if (!end_time.equals(other.end_time)) return false;
      if (id == null) {
         if (other.id != null) return false;
      } else if (!id.equals(other.id)) return false;
      if (new_antenna != other.new_antenna) return false;
      if (old_antenna != other.old_antenna) return false;
      if (start_time == null) {
         if (other.start_time != null) return false;
      } else if (!start_time.equals(other.start_time)) return false;
      if (timeid == null) {
         if (other.timeid != null) return false;
      } else if (!timeid.equals(other.timeid)) return false;
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
      builder.append("RepeatEvent [new_antenna=").append(new_antenna).append(", old_antenna=").append(old_antenna)
            .append(", start_time=").append(start_time).append(", end_time=").append(end_time).append(", uid=")
            .append(uid).append(", timeid=").append(timeid).append(", id=").append(id).append(", cnt=").append(cnt)
            .append("]");
      return builder.toString();
   }



}
