package halwrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableMap;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

public abstract class HalWrapper {

    @JsonProperty(value = "_links", required = true)
    public final ImmutableMap<String, Map<String, String>> links;

    public HalWrapper(UriInfo uriInfo) {
        this.links = ImmutableMap.of("self", href(uriInfo.getRequestUri()));
    }

    public HalWrapper(UriInfo uriInfo, Map<String, URI> extraLinks) {
        ImmutableMap.Builder<String, Map<String, String>> builder = ImmutableMap.<String, Map<String, String>>builder();

        builder.put("self", href(uriInfo.getRequestUri()));
        extraLinks.entrySet().forEach(entry ->
                        builder.put(entry.getKey(), href(entry.getValue()))
        );

        this.links = builder.build();
    }


    private static ImmutableMap<String, String> href(URI uri) {
        return ImmutableMap.of("href", link(uri));
    }

    private static String link(URI uri) {
        String path = uri.getPath();
        if (path.startsWith("/api/")) {
            path = path.substring(5);
        } else if (path.startsWith("/")) {
            path = path.substring(1);
        }

        return path + (uri.getQuery() == null ? "" : ("?" + uri.getQuery()));
    }

}