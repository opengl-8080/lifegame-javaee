package gl8080.lifegame.web.exception;

import javax.transaction.RollbackException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.Providers;

@Provider
public class RollBackExceptionMapper implements ExceptionMapper<RollbackException> {
    
    @Context
    private Providers providers;
    
    @Override
    public Response toResponse(RollbackException e) {
        Throwable cause = e.getCause();
        
        @SuppressWarnings("unchecked")
        Class<Throwable> type = (Class<Throwable>) cause.getClass();
        
        ExceptionMapper<Throwable> exceptionMapper = this.providers.getExceptionMapper(type);
        
        if (exceptionMapper == null) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        } else {
            return exceptionMapper.toResponse(cause);
        }
        
    }
}
