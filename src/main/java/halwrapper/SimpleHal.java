package halwrapper;

import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Map;

import static java.util.Collections.emptyMap;

public final class SimpleHal<T> extends Hal {

    public final String propertyName;
    public final T property;

    private SimpleHal(String propertyName, T property, UriInfo uriInfo, Map<String, URI> extraLinks) {
        super(uriInfo, extraLinks);
        this.propertyName = propertyName;
        this.property = property;
    }

    public static <T> SimpleHal hal(String propertyName, T property, UriInfo uriInfo) {
        return new SimpleHal<>(propertyName, property, uriInfo, emptyMap());
    }

    public static <T> SimpleHal hal(String propertyName, T property, UriInfo uriInfo, Map<String, URI> extraLinks) {
        return new SimpleHal<>(propertyName, property, uriInfo, extraLinks);
    }

}
