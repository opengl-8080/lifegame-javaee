package gl8080.lifegame.web.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import gl8080.lifegame.logic.exception.NotFoundEntityException;

@Provider
public class NotFoundEntityExceptionMapper implements ExceptionMapper<NotFoundEntityException> {

    @Override
    public Response toResponse(NotFoundEntityException exception) {
        return Response.status(Status.NOT_FOUND).build();
    }
}
