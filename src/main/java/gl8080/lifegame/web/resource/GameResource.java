package gl8080.lifegame.web.resource;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import gl8080.lifegame.application.NextStepService;
import gl8080.lifegame.application.RegisterGameService;
import gl8080.lifegame.application.RemoveGameService;
import gl8080.lifegame.application.SearchGameService;
import gl8080.lifegame.logic.Game;
import gl8080.lifegame.util.Maps;

@Path("/game")
@ApplicationScoped
public class GameResource {
    
    @Inject
    private RegisterGameService registerService;
    @Inject
    private SearchGameService searchService;
    @Inject
    private NextStepService nextService;
    @Inject
    private RemoveGameService removeService;
    
    @POST
    public Response register(@QueryParam("game-definition-id") long gameDefinitionId) {
        Game game = this.registerService.register(gameDefinitionId);
        long id = game.getId();

        URI uri = UriBuilder
                    .fromResource(GameResource.class)
                    .path(GameResource.class, "search")
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
    
    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public LifeGameDto next(@PathParam("id") long id) {
        return this.nextService.next(id);
    }
    
    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") long id) {
        this.removeService.remove(id);
        return Response.ok().build();
    }
}
