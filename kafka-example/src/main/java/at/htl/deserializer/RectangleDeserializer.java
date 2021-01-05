package at.htl.deserializer;

import at.htl.data.Rectangle;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class RectangleDeserializer extends JsonbDeserializer<Rectangle> {
    public RectangleDeserializer(){
        super(Rectangle.class);
    }
}
