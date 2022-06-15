package org.barros.modules.exception;

import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

    @Inject
    Logger log;

    @Override
    public Response toResponse(ConstraintViolationException e) {
        log.errorf("Mensagem: %s", e.getMessage());

        List<CustomError.Field> fields = e.getConstraintViolations().stream()
                .map(cv -> new CustomError.Field(getName(cv), cv.getMessage()))
                .collect(Collectors.toUnmodifiableList());

        var customError = new CustomError(Response.Status.BAD_REQUEST.getStatusCode(),
                "campo(s) obrigatório(s) e/ou inválido(s)", OffsetDateTime.now(), fields);
        return Response.status(Response.Status.BAD_REQUEST).entity(customError).build();

    }

    private String getName(ConstraintViolation<?> constraintViolation) {

        var optionalNode = StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false)
                .reduce((first, second) -> second);
        if (optionalNode.isPresent()) {
            return optionalNode.get().getName();
        }
        return "";
    }

}





