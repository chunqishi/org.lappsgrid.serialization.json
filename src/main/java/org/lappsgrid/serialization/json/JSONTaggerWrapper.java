package org.lappsgrid.serialization.json;

import org.json.JSONObject;
import org.lappsgrid.vocabulary.Annotations;

/**
 * Created by shi on 5/13/14.
 */
public class JsonTaggerWrapper extends JsonWrapper {

    public JsonTaggerWrapper(String textjson) {
        super(textjson);
        this.setAnnotationType(Annotations.TOKEN);
        this.setIdHeader("pos");
        this.setLastAnnotationType(Annotations.TOKEN);
    }

    public JsonTaggerWrapper() {
        super();
        this.setAnnotationType(Annotations.TOKEN);
        this.setIdHeader("pos");
        this.setLastAnnotationType(Annotations.TOKEN);
    }

}