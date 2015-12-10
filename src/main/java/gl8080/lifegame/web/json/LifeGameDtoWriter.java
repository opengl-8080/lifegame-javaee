package gl8080.lifegame.web.json;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import gl8080.lifegame.web.resource.LifeGameDto;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class LifeGameDtoWriter extends AbstractJsonWriter<LifeGameDto> {
}
