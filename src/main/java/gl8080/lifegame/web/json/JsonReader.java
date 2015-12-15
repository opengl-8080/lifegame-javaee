package gl8080.lifegame.web.json;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;

import gl8080.lifegame.web.resource.LifeGameDto;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public class JsonReader implements MessageBodyReader<LifeGameDto> {
    
    private ObjectMapper mapper = new ObjectMapper();
    
    @Override
    public boolean isReadable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return mediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE);
    }

    @Override
    public LifeGameDto readFrom(Class<LifeGameDto> clazz, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> header, InputStream input) throws IOException, WebApplicationException {
        return this.mapper.readValue(input, LifeGameDto.class);
    }
}
