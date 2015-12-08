package gl8080.lifegame.web.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import gl8080.lifegame.application.SampleService;

@Path("sample")
@ApplicationScoped
public class SampleResource {
    
    @Inject
    private SampleService service;
    
    @POST
    @Path("/def")
    public void registerDef(@QueryParam("size") int size) {
        this.service.registerDef(size);
    }
    
    @GET
    @Path("/def")
    public void searchDef(@QueryParam("id") long id) {
        this.service.searchDef(id);
    }
    
    @POST
    public void registerGame(@QueryParam("id") int id) {
        this.service.registerGame(id);
    }
    
    @GET
    public void searchGame(@QueryParam("id") long id) {
        this.service.searchGame(id);
    }
}
