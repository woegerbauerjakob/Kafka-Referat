package at.htl.kafka;

import at.htl.data.Line;
import at.htl.data.Position;
import at.htl.data.Rectangle;
import com.google.gson.Gson;
import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.annotations.Broadcast;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

@ApplicationScoped
public class RectangleConverter {

    @Incoming("rectangles")
    @Outgoing("draw-stream")
    @Broadcast
    @Blocking
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public String process(Rectangle rectangle) throws IOException {
        Position p1, p2, p3, p4;
        p1 = rectangle.getPosition1();
        p3 = rectangle.getPosition2();
        p2 = new Position(p1.getX(),p3.getY());
        p4 = new Position(p3.getX(),p1.getY());

        List<Line> output = new LinkedList<>();
        output.add(new Line(p1,p2));
        output.add(new Line(p2,p3));
        output.add(new Line(p3,p4));
        output.add(new Line(p4,p1));
        System.out.println("waiting for Enter");

        System.in.read();

        return new Gson().toJson(output);
    }

}
