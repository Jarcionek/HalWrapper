package halwrapper;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

import static java.util.Collections.emptyMap;

@JsonSerialize(using = HalSerializer.class)
public class Hal<T> {

    public final String propertyName;
    public final T property;
    public final URI selfLink;
    public final Map<String, URI> extraLinks;

    public static <T> Hal hal(String propertyName, T property, UriInfo uriInfo) {
        return new Hal<>(propertyName, property, uriInfo, emptyMap());
    }

    public static <T> Hal hal(String propertyName, T property, UriInfo uriInfo, Map<String, URI> extraLinks) {
        return new Hal<>(propertyName, property, uriInfo, extraLinks);
    }

    private Hal(String propertyName, T property, UriInfo uriInfo, Map<String, URI> extraLinks) {
        this.propertyName = propertyName;
        this.property = property;
        this.selfLink = uriInfo.getRequestUri();
        this.extraLinks = extraLinks;
    }

}
