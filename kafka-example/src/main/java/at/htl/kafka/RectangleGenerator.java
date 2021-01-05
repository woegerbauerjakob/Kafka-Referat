package at.htl.kafka;


import at.htl.data.Position;
import at.htl.data.Rectangle;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.Random;

@ApplicationScoped
public class RectangleGenerator {

    private static int xmax = 100;
    private static int ymax = 100;
    private static int interval = 5;

    Random random = new Random();

    @Outgoing("generated-rectangle")
    public Multi<Rectangle> generate(){
        return Multi.createFrom().ticks().every(Duration.ofSeconds(interval))
                .onOverflow().drop()
                .map(tick -> {
                    Position pos1 = new Position(random.nextInt(xmax),random.nextInt(ymax));
                    Position pos2 = new Position(random.nextInt(xmax),random.nextInt(ymax));
                    while (pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY()){
                        pos2 = new Position(random.nextInt(xmax),random.nextInt(ymax));
                    }
                    return new Rectangle(pos1,pos2);
                });
    }

}
