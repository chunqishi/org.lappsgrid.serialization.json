package org.lappsgrid.serialization.json;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shi on 5/15/14.
 */
public class JSONObject extends LinkedHashMap{
    public JSONObject(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.putAll((Map)mapper.readValue(json, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject(Map map){
        this.putAll(map);
    }

    public JSONObject(){}

    public String getString(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (String)val;
        }
        return null;
    }

    public Long getLong(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Long)val;
        }
        return null;
    }

    public Double getDouble(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Double)val;
        }
        return null;
    }

    public Boolean getBoolean(String key) {
        Object val = this.get(key);
        if (val != null) {
            return (Boolean)val;
        }
        return null;
    }

    public Integer getInt(String key) {
        Object val = this.get(key);
        if (val != null) {
            return ((Integer)val);
        }
        return null;
    }

    public JSONArray getJSONArray(String key){
        Object val = this.get(key);
        if (val != null) {
            return new JSONArray((List)val);
        }
        return null;
    }

    public boolean has(String key) {
        return this.containsKey(key);
    }

    public JSONObject getJSONObject(String key){
        Object val = this.get(key);
        if (val != null) {
            return new JSONObject((Map)val);
        }
        return null;
    }

    public JSONObject put(String key, Object obj) {
        super.put(key, obj);
        return this;
    }

}
