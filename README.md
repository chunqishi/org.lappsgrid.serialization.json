org.lappsgrid.JsonSerialization
===============================

Provide Json Wrapper / Serialization Package.

### Maven Usage

```xml
     <dependency>
            <groupId>org.lappsgrid</groupId>
            <artifactId>JsonSerialization</artifactId>
            <version>0.0.1</version>
     </dependency>
```

### JsonSerialization Model

```java
            // JSON Serialization Model.
        if (discriminator == Types.JSON) {                                  // 1st json.
            String jsonstr = data.getPayload();
            JsonNERSerialization json = new JsonNERSerialization(jsonstr);  // read json
            json.setProducer(this.getClass().getName() + ":" + VERSION);    // set producer
            json.setType("ner:opennlp");                                    // set type
            List<JSONObject> tokenObjs = json.findLastAnnotations();        // find required last step annotations.
            if (tokenObjs == null) {
                String message = "Invalid JSON input. Expected annotation type: " + json.getLastAnnotationType();
                logger.warn(message);
                return DataFactory.error(message);
            }

            String[] tokens = new String[tokenObjs.size()];
            for(int i = 0; i < tokens.length; i++ ) {
                tokens[i] = json.getAnnotationTextValue(tokenObjs.get(i));  // read annotation text = text.sub(start, end)
            }

            for (TokenNameFinder nameFinder : nameFinders) {
                Span [] partSpans = nameFinder.find(tokens);
                for (Span span:partSpans){                                 // json create newAnnotationWithType
                    JSONObject annotation = json.newAnnotationWithType(span.getType(), tokenObjs.get(span.getStart()));
                }
            }
            return DataFactory.json(json.toString());                     // json toString
        } else if (discriminator == Types.TEXT)
        {
            String text = data.getPayload();
            JsonNERSerialization json = new JsonNERSerialization();      // default constructor
            json.setTextValue(text);                                     // set text value
            json.setProducer(this.getClass().getName() + ":" + VERSION); // set producer
            json.setType("ner:opennlp");                                 // set type

            Span[] spans = find(new String[]{text});
            for (Span span : spans) {                                    // json create new annotation
                JSONObject annotation = json.newAnnotationWithType(span.getType());
                json.setWord(annotation, text);                          // set annotation feature word
                json.setStart(annotation, 0);                            // set annotation text start
                json.setEnd(annotation, text.length());                  // set annotation text end
            }
            return DataFactory.json(json.toString());                    // json toString

        }
```

### JsonSplitterSerialization

```java
            // splitter example.
        if (discriminator == Types.JSON) {
            String jsonstr = data.getPayload();
            JsonSplitterSerialization json = new JsonSplitterSerialization(jsonstr);
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("splitter:opennlp");
            Span[] spans = sentPosDetect(json.getTextValue());
            for (Span span : spans) {
                JSONObject annotation = json.newAnnotation();
                json.setStart(annotation, span.getStart());
                json.setEnd(annotation, span.getEnd());
            }
            return DataFactory.json(json.toString());

        } else if (discriminator == Types.TEXT)
        {
            String text = data.getPayload();
            JsonSplitterSerialization json = new JsonSplitterSerialization();
            json.setTextValue(text);
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("splitter:opennlp");

            Span[] spans = sentPosDetect(text);
            for (Span span : spans) {
                JSONObject ann = json.newAnnotation();
                json.setStart(ann, span.getStart());
                json.setEnd(ann, span.getEnd());
            }
            return DataFactory.json(json.toString());
        }
```

### JsonTokenizerSerialization
```java
            // tokenizer example.
        if (discriminator == Types.JSON) {
            String jsonstr = data.getPayload();
            JsonTokenizerSerialization json = new JsonTokenizerSerialization(jsonstr);
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("tokenizer:opennlp");
            Span[] spans = tokenizePos(json.getTextValue());
            for (Span span : spans) {
                JSONObject ann = json.newAnnotation();
                json.setStart(ann, span.getStart());
                json.setEnd(ann, span.getEnd());
                json.setWord(ann, json.getTextValue().substring(span.getStart(), span.getEnd()));
            }
            return DataFactory.json(json.toString());

        } else if (discriminator == Types.TEXT) {
            String text = data.getPayload();
            JsonTokenizerSerialization json = new JsonTokenizerSerialization();
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("tokenizer:opennlp");
            json.setTextValue(text);
            Span[] spans = tokenizePos(text);
            for (Span span : spans) {
                JSONObject ann = json.newAnnotation();
                json.setStart(ann, span.getStart());
                json.setEnd(ann, span.getEnd());
                json.setWord(ann, json.getTextValue().substring(span.getStart(), span.getEnd()));
            }
            return DataFactory.json(json.toString());
        }
```
### JsonTaggerSerialization
```java
            // postagger example.
        if (discriminator == Types.JSON) {
            String jsonstr = data.getPayload();
            JsonTaggerSerialization json = new JsonTaggerSerialization(jsonstr);
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("tagger:opennlp");
            List<JSONObject> tokenObjs = json.findLastAnnotations();
            if (tokenObjs == null) {
                String message = "Invalid JSON input. Expected annotation type: " + json.getLastAnnotationType();
                logger.warn(message);
                return DataFactory.error(message);
            }

            String[] tokens = new String[tokenObjs.size()];
            for(int i = 0; i < tokens.length; i++ ) {
                tokens[i] = json.getAnnotationTextValue(tokenObjs.get(i));
            }

            String[] tags = postagger.tag(tokens);

            for(int i = 0; i < tokenObjs.size(); i++) {
                JSONObject annotation = json.newAnnotation(tokenObjs.get(i));
                json.setCategory(annotation, tags[i]);
            }
            return DataFactory.json(json.toString());
        } else  if (discriminator == Types.TEXT) {
            String textvalue = data.getPayload();
            JsonTaggerSerialization json = new JsonTaggerSerialization();
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("tagger:opennlp");
            json.setTextValue(textvalue);

            String [] tags = tag(new String[]{textvalue});
            for(int i = 0; i < tags.length; i++) {
                JSONObject annotation =  json.newAnnotation();
                json.setStart(annotation, 0);
                json.setEnd(annotation, textvalue.length());
                json.setCategory(annotation, tags[i]);
            }
            return DataFactory.json(json.toString());

        }
```

### JsonNERSerialization
```java
            // ner example.
        if (discriminator == Types.JSON) {
            String jsonstr = data.getPayload();
            JsonNERSerialization json = new JsonNERSerialization(jsonstr);
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("ner:opennlp");
            List<JSONObject> tokenObjs = json.findLastAnnotations();
            if (tokenObjs == null) {
                String message = "Invalid JSON input. Expected annotation type: " + json.getLastAnnotationType();
                logger.warn(message);
                return DataFactory.error(message);
            }

            String[] tokens = new String[tokenObjs.size()];
            for(int i = 0; i < tokens.length; i++ ) {
                tokens[i] = json.getAnnotationTextValue(tokenObjs.get(i));
            }

            for (TokenNameFinder nameFinder : nameFinders) {
                Span [] partSpans = nameFinder.find(tokens);
                for (Span span:partSpans){
                    JSONObject annotation = json.newAnnotationWithType(span.getType(), tokenObjs.get(span.getStart()));
                }
            }
            return DataFactory.json(json.toString());
        } else if (discriminator == Types.TEXT)
        {
            String text = data.getPayload();
            JsonNERSerialization json = new JsonNERSerialization();
            json.setTextValue(text);
            json.setProducer(this.getClass().getName() + ":" + VERSION);
            json.setType("ner:opennlp");

            Span[] spans = find(new String[]{text});
            for (Span span : spans) {
                JSONObject annotation = json.newAnnotationWithType(span.getType());
                json.setWord(annotation, text);
                json.setStart(annotation, 0);
                json.setEnd(annotation, text.length());
            }
            return DataFactory.json(json.toString());

        }
```
