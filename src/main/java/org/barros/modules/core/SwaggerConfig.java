package org.barros.modules.core;

import org.barros.modules.error.ErrorResponseBody;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        components = @Components(responses = {
                @APIResponse(responseCode = "500", ref = "#/components/schemas/ErrorResponseBody", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponseBody.class)))
        }),

        info = @Info(
                title = "Controle de Usuários do Lacad API",
                version = "1.0.1",
                contact = @Contact(
                        name = "Controle de Usuários do Lacad API",
                        url = "http://localhost:8082/q/swagger-ui/#/",
                        email = "suellen.barrosramos@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)
public class SwaggerConfig extends Application {
}
