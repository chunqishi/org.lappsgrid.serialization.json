package org.lappsgrid.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shi on 5/15/14.
 */
public class JSONArray {

    List<Object> list = null;

    public JSONArray(List list){
        this.list = list;
    }

    public JSONArray(String json){
        ObjectMapper mapper = new ObjectMapper();
        try {
            this.list  = (List) mapper.readValue(json, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this.list);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public JSONArray(){
        list = new ArrayList<Object>();
    }

    public Object get(int i) {
        return this.list.get(i);
    }

    public JSONArray put(Object obj) {
        if(obj instanceof JSONArray) {
            obj = ((JSONArray)obj).list;
        } else if (obj instanceof JSONObject) {
            obj = ((JSONObject)obj).map;
        }
        this.list.add(obj);
        return this;
    }


    public int length() {
        return this.list.size();
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
