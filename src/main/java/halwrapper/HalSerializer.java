package halwrapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;

public class HalSerializer extends JsonSerializer<Hal> {

    @Override
    public void serialize(Hal hal, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        checkInheritance(hal.getClass());
        jgen.writeStartObject();
        if (hal instanceof SimpleHal) {
            writeSimpleHal((SimpleHal) hal, jgen);
        } else {
            writeCompositeHal(hal, jgen);
        }
        writeLinks(hal, jgen);
        jgen.writeEndObject();
    }

    private void checkInheritance(Class<? extends Hal> descendantClass) {
        if (!descendantClass.getSuperclass().equals(Hal.class)) {
            throw new IllegalArgumentException("multi level inheritance not supported");
        }
    }

    private static void writeSimpleHal(SimpleHal hal, JsonGenerator jgen) throws IOException {
        jgen.writeObjectField(hal.propertyName, hal.property);
    }

    private static void writeCompositeHal(Hal hal, JsonGenerator jgen) throws IOException {
        try {
            // annotations such as @JsonIgnore or @JsonProperty(String) not supported
            for (Field field : hal.getClass().getDeclaredFields()) {
                jgen.writeObjectField(field.getName(), field.get(hal));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeLinks(Hal hal, JsonGenerator jgen) throws IOException {
        jgen.writeFieldName("_links");
        jgen.writeStartObject();

        writeLinkField(jgen, "self", hal.selfLink);
        for (Map.Entry<String, URI> entry : hal.extraLinks.entrySet()) {
            writeLinkField(jgen, entry.getKey(), entry.getValue());
        }

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
