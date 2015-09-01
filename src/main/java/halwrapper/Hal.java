package halwrapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

import static java.util.Collections.emptyMap;

@JsonSerialize(using = HalSerializer.class)
public abstract class Hal {

    public final URI selfLink;
    public final Map<String, URI> extraLinks;

    protected Hal(UriInfo uriInfo, Map<String, URI> extraLinks) {
        if (extraLinks.containsKey("self")) {
            throw new IllegalArgumentException("extra links cannot contain self link");
        }
        this.selfLink = uriInfo.getRequestUri();
        this.extraLinks = extraLinks;
    }

    protected Hal(UriInfo uriInfo) {
        this(uriInfo, emptyMap());
    }

}
