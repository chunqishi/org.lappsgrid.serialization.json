package org.lappsgrid.serialization.json;

import org.json.JSONObject;
import org.lappsgrid.vocabulary.Annotations;

/**
 * Created by shi on 5/13/14.
 */
public class JsonParserWrapper extends JsonWrapper {

    public JsonParserWrapper(String textjson) {
        super(textjson);
        this.setAnnotationType(Annotations.SENTENCE);
        this.setIdHeader("parser");
        this.setLastAnnotationType(Annotations.SENTENCE);
    }

    public JsonParserWrapper() {
        super();
        this.setAnnotationType(Annotations.SENTENCE);
        this.setIdHeader("parser");
        this.setLastAnnotationType(Annotations.SENTENCE);
    }
}