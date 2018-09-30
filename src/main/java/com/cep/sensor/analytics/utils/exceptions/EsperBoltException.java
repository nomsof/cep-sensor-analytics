package com.cep.sensor.analytics.utils.exceptions;

/**
 * Custom Java Exception to for any issue found during execution of EsperBolt
 */
public class EsperBoltException extends Exception {

   private static final long serialVersionUID = -4896002585425711053L;

   public EsperBoltException(String msg, Throwable err) {
      super(msg, err);
   }
}