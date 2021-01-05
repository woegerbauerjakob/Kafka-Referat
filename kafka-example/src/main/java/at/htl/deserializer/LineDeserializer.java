package at.htl.deserializer;

import at.htl.data.Line;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class LineDeserializer extends JsonbDeserializer<Line> {
    public LineDeserializer(){
        super(Line.class);
    }
}
