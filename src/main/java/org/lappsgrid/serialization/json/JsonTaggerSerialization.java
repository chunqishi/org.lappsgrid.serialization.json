package org.lappsgrid.serialization.json;

import org.lappsgrid.vocabulary.Annotations;
import org.lappsgrid.vocabulary.Features;

/**
 * Created by shi on 5/13/14.
 */
public class JsonTaggerSerialization extends JsonSerialization {

    public JsonTaggerSerialization(String textjson) {
        super(textjson);
        this.setContainType(Features.PART_OF_SPEECH);
        this.setAnnotationType(Annotations.TOKEN);
        this.setIdHeader("pos");
        this.setLastAnnotationType(Annotations.TOKEN);
    }

    public JsonTaggerSerialization() {
        super();
        this.setContainType(Features.PART_OF_SPEECH);
        this.setAnnotationType(Annotations.TOKEN);
        this.setIdHeader("pos");
        this.setLastAnnotationType(Annotations.TOKEN);
    }

}