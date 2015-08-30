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