package org.barros.modules.exception;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.OffsetDateTime;

@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Inject
    Logger log;

    @Override
    public Response toResponse(IllegalArgumentException e) {
        log.errorf("Mensagem: %s", e.getMessage());

        var customError = new CustomError(Response.Status.BAD_REQUEST.getStatusCode(), e.getMessage(),
                OffsetDateTime.now());
        return Response.status(Response.Status.BAD_REQUEST).entity(customError).build();
    }
}
