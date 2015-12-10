package gl8080.lifegame.web.json;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractJsonWriter<T> implements MessageBodyWriter<T> {
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.equals(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public long getSize(T dto, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(T dto, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> headers, OutputStream out) throws IOException, WebApplicationException {
        this.mapper.writeValue(out, dto);
    }
}
