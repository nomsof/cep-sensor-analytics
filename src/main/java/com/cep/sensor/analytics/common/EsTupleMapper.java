package com.cep.sensor.analytics.common;

import org.apache.storm.tuple.ITuple;

import java.io.Serializable;
import java.util.Map;

/**
 * TupleMapper defines how to extract source, index, type, and id from tuple for ElasticSearch.
 */
public interface EsTupleMapper extends Serializable {
    /**
     * Extracts source from tuple.
     * @param tuple source tuple
     * @return source
     */
    String getSource(ITuple tuple);

    /**
     * Extracts index from tuple.
     * @param tuple source tuple
     * @return index
     */
    String getIndex(ITuple tuple);

    /**
     * Extracts type from tuple.
     * @param tuple source tuple
     * @return type
     */
    String getType(ITuple tuple);

    /**
     * Extracts id from tuple.
     * @param tuple source tuple
     * @return id
     */
    String getId(ITuple tuple);

    /**
     * Extracts params from tuple if available.
     * @param tuple source tuple
     * @param defaultValue value to return if params are missing
     * @return params
     */
    Map<String, String> getParams(ITuple tuple, Map<String, String> defaultValue);
}