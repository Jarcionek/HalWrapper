package halwrapper;

import com.google.common.collect.ImmutableMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import static halwrapper.SimpleHal.hal;
import static java.util.Arrays.asList;

@Path("/test")
@Produces(MediaType.APPLICATION_JSON)
public class HalWrapperResource {

    @Path("/1")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test1(@Context UriInfo uriInfo) {
        return Response.ok(new StringListHal(uriInfo, asList("one", "2", "trzy"))).build();
    }

    @Path("/2")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test2(@Context UriInfo uriInfo) {
        return Response.ok(hal("stringsblahblah", asList("one", "2", "trzy"), uriInfo)).build();
    }

    @Path("/3")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test3(@Context UriInfo uriInfo) {
        return Response.ok(hal("I've done it!", asList("one", "2", "trzy"), uriInfo)).build();
    }

    @Path("/4")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test4(@Context UriInfo uriInfo) {
        Hal hal = hal(
                "I've done it!",
                asList("one", "2", "trzy"),
                uriInfo,
                ImmutableMap.of(
                        "linkOne", UriBuilder.fromUri("/api/breaklist/12345?area=MW&salesGroup=C").build(),
                        "admin", UriBuilder.fromUri("/admin?password=1234").build(),
                        "itv", UriBuilder.fromUri("itv.com").build()
                )
        );
        return Response.ok(hal).build();
    }

    @Path("/5")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response test5(@Context UriInfo uriInfo) {
        return Response.ok(new ExampleCompositeHal(uriInfo)).build();
    }

}
