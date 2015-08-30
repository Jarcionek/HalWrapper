package halwrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class StringHal extends HalWrapper {

    @JsonProperty
    public final List<String> stringsblahblah;

    public StringHal(UriInfo uriInfo, List<String> list) {
        super(uriInfo);
        this.stringsblahblah = list;
    }

}
