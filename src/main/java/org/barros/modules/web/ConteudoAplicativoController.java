package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ConteudoAplicativoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.ConteudoAplicativoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Conteudo e Aplicativo", description = "Endpoint(s) relacionado(s) a manipulação de Conteúdos e Aplicativos")
@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/conteudoAplicativos")
public class ConteudoAplicativoController {

    private final ConteudoAplicativoService conteudoAplicativoService;

    @GET
    @APIResponse(responseCode = "200", description = "Obtem todos os Conteúdos relacionados com Aplicativos", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.ARRAY, implementation = ConteudoAplicativoDTO.class)))
    @Operation(summary = "Buscar Conteúdos com Aplicativos", description = "Obtem todos os Conteúdos com Aplicativos")
    public Response get() {
        return Response.ok(conteudoAplicativoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Obtem Curso e Aplicativo pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = ConteudoAplicativoDTO.class)))
    @APIResponse(responseCode = "404", description = "Curso e Aplicativo não encontrado pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Buscar Curso e Aplicativo por Id", description = "Busca Conteúdo e Aplicativo por Id")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return conteudoAplicativoService.findById(id)
                .map(conteudoAplicativoDTO -> Response.ok(conteudoAplicativoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
    @POST
    @Operation(summary = "Salvar Conteúdo e Aplicativo", description = "Cria uma novo Conteúdo e Aplicativo")
    @APIResponse(responseCode = "201", description = "Conteúdo e Aplicativo salvo", content = @Content(schema = @Schema(implementation = ConteudoAplicativoDTO.class)))
    @APIResponse(responseCode = "400", description = "Já existe um Curso e Aplicativo com esse Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response post(@NotNull @Valid ConteudoAplicativoDTO conteudoAplicativoDTO, @Context UriInfo uriInfo) {
        conteudoAplicativoService.save(conteudoAplicativoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(conteudoAplicativoDTO.getId())).build();
        return Response.created(uri).entity(conteudoAplicativoDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Conteúdo e Aplicativo", description = "Exclui os dados do Conteúdo e Aplicativo")
    @APIResponse(responseCode = "204", description = "Registro excluído com sucesso")
    public void delete(@PathParam("id") Long id) {
        conteudoAplicativoService.excluir(id);
    }
}



