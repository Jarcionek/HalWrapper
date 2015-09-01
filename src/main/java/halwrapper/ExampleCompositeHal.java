package halwrapper;

import javax.ws.rs.core.UriInfo;
import java.util.List;

import static java.util.Arrays.asList;

public class ExampleCompositeHal extends Hal {

    public final String x;
    public final int y;
    public final List<String> aList;

    public ExampleCompositeHal(UriInfo uriInfo) {
        super(uriInfo);
        this.x = "abc";
        this.y = 3;
        this.aList = asList("A", "B", "C");
    }

}
