package org.lappsgrid.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shi on 5/15/14.
 */
public class JSONArray extends ArrayList {

    public JSONArray(List list){
        this.addAll(list);
    }

    public JSONArray(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.addAll((List) mapper.readValue(json, List.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public JSONArray(){}

    public JSONArray put(Object obj) {
        super.add(obj);
        return this;
    }


    public int length() {
        return this.size();
    }

    public JSONObject getJSONObject(int i) {
        Object val = get(i);
        if (val != null) {
            return new JSONObject((Map)val);
        }
        return null;
    }

    public JSONArray getJSONArray(int i) {
        Object val = get(i);
        if (val != null) {
            return new JSONArray((List)val);
        }
        return null;
    }

    public String getString(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (String)val;
        }
        return null;
    }

    public Long getLong(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Long)val;
        }
        return null;
    }

    public Double getDouble(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Double)val;
        }
        return null;
    }

    public Boolean getBoolean(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Boolean)val;
        }
        return null;
    }

    public Integer getInt(int i) {
        Object val = this.get(i);
        if (val != null) {
            return (Integer)val;
        }
        return null;
    }

}
