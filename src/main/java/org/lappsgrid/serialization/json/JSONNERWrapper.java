package org.lappsgrid.serialization.json;

import org.json.JSONObject;
import org.lappsgrid.vocabulary.Annotations;

/**
 * Created by shi on 5/13/14.
 */
public class JsonNERWrapper extends JsonWrapper {

    public JsonNERWrapper(String textjson) {
        super(textjson);
        this.setAnnotationType(Annotations.NE);
        this.setIdHeader("ner");
        this.setLastAnnotationType(Annotations.TOKEN);
    }

    public JsonNERWrapper() {
        super();
        this.setAnnotationType(Annotations.NE);
        this.setIdHeader("ner");
        this.setLastAnnotationType(Annotations.TOKEN);
    }
}