package halwrapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.net.URI;
import java.util.Map;

public class HalSerializer extends JsonSerializer<Hal<?>> {

    @Override
    public void serialize(Hal<?> hal, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();

            jgen.writeObjectField(hal.propertyName, hal.property);

            jgen.writeFieldName("_links");
                jgen.writeStartObject();

                    writeLinkField(jgen, "self", hal.selfLink);
                    for (Map.Entry<String, URI> entry : hal.extraLinks.entrySet()) {
                        writeLinkField(jgen, entry.getKey(), entry.getValue());
                    }

                jgen.writeEndObject();

        jgen.writeEndObject();
    }

    private static void writeLinkField(JsonGenerator jgen, String linkName, URI uri) throws IOException {
        jgen.writeFieldName(linkName);
            jgen.writeStartObject();
                jgen.writeStringField("href", link(uri));
            jgen.writeEndObject();
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
