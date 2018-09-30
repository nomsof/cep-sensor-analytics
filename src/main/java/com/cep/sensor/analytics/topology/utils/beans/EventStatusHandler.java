package com.cep.sensor.analytics.topology.utils.beans;

import java.util.HashMap;

import com.cep.sensor.analytics.domain.Event;
import com.cep.sensor.analytics.domain.EventFull;

/**
 * EventStatusHandler Class is an internal tool to locate the changes of
 * uids during room changes. It can be used in cases that Esper has high Query Complexity
 */
public class EventStatusHandler {

   private static EventStatusHandler instance = null;
   private HashMap<String, EventFull> state = null;

   private EventStatusHandler() {
      state = new HashMap<String, EventFull>();
   }

   public static synchronized EventStatusHandler getInstance() {
      if (instance == null) {
         instance = new EventStatusHandler();
      }
      return instance;
   }

   /*
    * Main logic that a person comes or leaves a room.
    * When exits a room a full event is generated
    * Otherwise null
    * */
   public EventFull process(Event event) {

      //It's the first occurrence of the user in our world
      if (!state.containsKey(event.getUid())) {
         state.put(event.getUid(), new EventFull(event));
         return null;
      } else {
         //User already exist but now it's in a different room
         if (state.get(event.getUid()).getAntenna() != event.getAntenna()) {
            EventFull result = state.get(event.getUid());
            result.setEndDate(event.getStartDate());
            state.put(event.getUid(), new EventFull(event));
            return result;
         } else {
            //User already exist and it's in the same room
            //Ignore that event
            return null;
         }
      }
   }

   /***************************************************/
   /************ GETTERS - SETTERS ********************/
   /***************************************************/

   /**
    * @return the state
    */
   public HashMap<String, EventFull> getState() {
      return state;
   }

   /**
    * @param state the state to set
    */
   public void setState(HashMap<String, EventFull> state) {
      this.state = state;
   }

}
