package gl8080.lifegame.web.json;

import java.util.Map;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class MapWriter extends AbstractJsonWriter<Map<?, ?>> {
}
