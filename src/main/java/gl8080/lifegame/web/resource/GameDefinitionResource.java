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

import gl8080.lifegame.application.definition.RegisterGameDefinitionService;
import gl8080.lifegame.application.definition.RemoveGameDefinitionService;
import gl8080.lifegame.application.definition.SearchGameDefinitionService;
import gl8080.lifegame.application.definition.UpdateGameDefinitionService;
import gl8080.lifegame.logic.definition.GameDefinition;
import gl8080.lifegame.util.Maps;

@Path("/game/definition")
@ApplicationScoped
public class GameDefinitionResource {
    
    @Inject
    private RegisterGameDefinitionService registerService;
    @Inject
    private SearchGameDefinitionService searchService;
    @Inject
    private UpdateGameDefinitionService saveService;
    @Inject
    private RemoveGameDefinitionService removeService;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@QueryParam("size") int size) {
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
        GameDefinition gameDefinition = this.searchService.search(id);
        return LifeGameDto.of(gameDefinition);
    }
    
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(LifeGameDto dto) {
        GameDefinition gameDefinition = this.saveService.update(dto);
        
        return Response.ok()
                        .entity(Maps.map("version", gameDefinition.getVersion()))
                        .build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") long id) {
        this.removeService.remove(id);
        return Response.noContent().build();
    }
}
