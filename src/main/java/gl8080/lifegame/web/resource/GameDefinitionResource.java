package gl8080.lifegame.web.resource;

import java.net.URI;

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
import javax.ws.rs.core.UriBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import gl8080.lifegame.application.RegisterGameDefinitionService;
import gl8080.lifegame.application.SearchGameDefinitionService;
import gl8080.lifegame.logic.GameDefinition;
import gl8080.lifegame.util.Maps;

@Path("/game/definition")
@ApplicationScoped
public class GameDefinitionResource {
    
    @Inject
    private RegisterGameDefinitionService registerService;
    @Inject
    private SearchGameDefinitionService searchService;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@QueryParam("size") int size) throws JsonProcessingException {
        GameDefinition gameDefinition = this.registerService.register(size);
        long id = gameDefinition.getId();
        
        URI uri = UriBuilder
                    .fromResource(GameDefinitionResource.class)
                    .path(GameDefinitionResource.class, "search")
                    .build(id);
        
        return Response
                    .created(uri)
                    .entity(Maps.map("id", id))
                    .build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LifeGameDto search(@PathParam("id") long id) {
        return this.searchService.search(id);
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
