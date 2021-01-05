package at.htl.deserializer;

import at.htl.data.Position;
import io.quarkus.kafka.client.serialization.JsonbDeserializer;

public class PositionDeserializer extends JsonbDeserializer<Position> {
    public PositionDeserializer(){
        super(Position.class);
    }
}
