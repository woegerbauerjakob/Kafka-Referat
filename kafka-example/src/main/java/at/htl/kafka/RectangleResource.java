package at.htl.kafka;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//http://localhost:8080/rectangles.html
@Path("/rectangles")
public class RectangleResource {
    @Inject
    @Channel("draw-stream")
    Publisher<String> rectangles;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.TEXT_PLAIN)
    public Publisher<String> stream(){
        System.out.println("Endpoint Registered");
        return rectangles;
    }
}
