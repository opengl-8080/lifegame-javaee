package gl8080.lifegame.web.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/game")
public class GameResource {
    
    @POST
    public Response register(@QueryParam("game-definition-id") long gameDefinitionId) {
        return null;
    }
    
    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LifeGameDto next(@PathParam("id") long id) {
        return null;
    }
    
    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") long id) {
        return null;
    }
}
