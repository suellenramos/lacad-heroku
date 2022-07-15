package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.ConteudoDTO;
import org.barros.modules.dto.response.CursoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.ConteudoService;
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
import java.util.Objects;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Conteudo", description = "Endpoint(s) relacionado(s) a manipulação de Conteúdos")
@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/conteudos")
public class ConteudoController {

    private final ConteudoService conteudoService;

    @GET
    @APIResponse(responseCode = "200", description = "Obtem todos os Conteudos", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = ConteudoDTO.class)))
    @Operation(summary = "Buscar Conteúdos", description = "Obtem todos os Conteúdos")
    public Response get() {
        return Response.ok(conteudoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Obtem Conteúdos pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = ConteudoDTO.class)))
    @APIResponse(responseCode = "404", description = "Conteúdo não encontrado pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Buscar Conteúdo por Id", description = "Busca Conteúdo por Id")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return conteudoService.findById(id)
                .map(conteudoDTO -> Response.ok(conteudoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Operation(summary = "Salvar Conteúdo", description = "Cria uma novo Conteúdo")
    @APIResponse(responseCode = "201", description = "Conteúdo salvo", content = @Content(schema = @Schema(implementation = ConteudoDTO.class)))
    @APIResponse(responseCode = "400", description = "Já existe um Conteúdo com esse Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response post(@NotNull @Valid ConteudoDTO conteudoDTO, @Context UriInfo uriInfo) {
        conteudoService.save(conteudoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(conteudoDTO.getConteId())).build();
        return Response.created(uri).entity(conteudoDTO).build();
    }

    @PUT
    @Path("{id}")
    @APIResponse(responseCode = "204", description = "Conteúdo Atualizado", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = ConteudoDTO.class)))
    @APIResponse(responseCode = "400", description = "Não foi encontrado Id para o Conteúdo requerido", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Atualizar Conteúdo", description = "Atualiza os dados do Conteúdo")
    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid ConteudoDTO conteudoDTO) {
        if (!Objects.equals(id, conteudoDTO.getConteId())) {
            throw new ServiceException("O id correspondente também deverá ser passado no parâmetro");
        }
        conteudoService.update(conteudoDTO);
        return Response.ok(conteudoDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Conteúdo", description = "Exclui os dados do Conteúdo")
    @APIResponse(responseCode = "204", description = "Registro excluído com sucesso")
    public void delete(@PathParam("id") Long id) {
        conteudoService.excluir(id);
    }
}



