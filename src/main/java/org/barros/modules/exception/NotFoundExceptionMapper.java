package org.barros.modules.exception;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.OffsetDateTime;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Inject
    Logger log;

    @Override
    public Response toResponse(NotFoundException e) {
        log.errorf("Mensagem: %s", e.getMessage());

        var customError = new CustomError(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage(),
                OffsetDateTime.now());
        return Response.status(Response.Status.NOT_FOUND).entity(customError).build();
    }
}
