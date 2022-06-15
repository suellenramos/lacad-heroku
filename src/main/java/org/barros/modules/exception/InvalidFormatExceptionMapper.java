package org.barros.modules.exception;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    @Inject
    Logger log;

    @Override
    public Response toResponse(InvalidFormatException exception) {
        log.errorf("Mensagem: %s", exception.getMessage());

        var path = exception.getPath().stream().map(Reference::getFieldName).collect(Collectors.joining("."));
        var message = String.format(
                "A propriedade '%s' recebeu o valor '%s', que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s",
                path, exception.getValue(), exception.getTargetType().getSimpleName());

        var customError = new CustomError(Response.Status.BAD_REQUEST.getStatusCode(), message, OffsetDateTime.now());
        return Response.status(Response.Status.BAD_REQUEST).entity(customError).build();
    }
}


