package gl8080.lifegame.web.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import gl8080.lifegame.application.SearchGameDefinitionService;

@Path("/game/definition")
@ApplicationScoped
public class GameDefinitionResource {
    
    @Inject
    private SearchGameDefinitionService service;
    
    @POST
    public Response register(@QueryParam("size") int size) {
        return null;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LifeGameDto search(@PathParam("id") long id) {
        return this.service.search(id);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modify(@PathParam("id") long id, LifeGameDto dto) {
        return null;
    }
    
    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") long id) {
        return null;
    }
}
