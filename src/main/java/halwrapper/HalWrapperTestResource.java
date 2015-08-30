package halwrapper;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;

import static java.util.Arrays.asList;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class HalWrapperTestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(@Context UriInfo uriInfo) {
        return Response.ok(new StringHal(uriInfo, asList("one", "2", "trzy"))).build();
    }

}
