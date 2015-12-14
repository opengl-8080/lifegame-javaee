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
        // firefox の場合、 application/json;charset=UTF-8 という形で渡ってくるので、単純な equals 比較ではダメ
        return MediaType.APPLICATION_JSON_TYPE.getType().equals(mediaType.getType())
                && MediaType.APPLICATION_JSON_TYPE.getSubtype().equals(mediaType.getSubtype());
    }

    @Override
    public LifeGameDto readFrom(Class<LifeGameDto> clazz, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> header, InputStream input) throws IOException, WebApplicationException {
        return this.mapper.readValue(input, LifeGameDto.class);
    }
}
