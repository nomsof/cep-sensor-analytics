package com.cep.sensor.analytics.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.tuple.ITuple;

public class DefaultEsTupleMapper implements EsTupleMapper {
    @Override
    public String getSource(ITuple tuple) {
        return tuple.getStringByField("source");
    }

    @Override
    public String getIndex(ITuple tuple) {
        return tuple.getStringByField("index");
    }

    @Override
    public String getType(ITuple tuple) {
        return tuple.getStringByField("type");
    }

    @Override
    public String getId(ITuple tuple) {
        return tuple.getStringByField("id");
    }

    @Override
    public Map<String, String> getParams(ITuple tuple, Map<String, String> defaultValue) {
        if (!tuple.contains("params")) {
            return defaultValue;
        }
        Object o = tuple.getValueByField("params");
        if (o instanceof Map) {
            Map<String, String> params = new HashMap<>();
            for (Map.Entry<?, ?> entry : ((Map<?, ?>) o).entrySet()) {
                params.put(entry.getKey().toString(), entry.getValue() == null ? null : entry.getValue().toString());
            }
            return params;
        }
        return defaultValue;
    }
}