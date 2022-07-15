package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.DisciplinaDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.DisciplinaService;
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
@Tag(name = "Disciplina", description = "Endpoint(s) relacionado(s) a manipulação de Disciplinas")
@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @GET
    @APIResponse(responseCode = "200", description = "Obtem todas as Disciplinas", content = @Content(
            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.ARRAY, implementation = DisciplinaDTO.class)))
    @Operation(summary = "Buscar Disciplinas", description = "Obtem todas as Disciplinas")
    public Response get() {
        return Response.ok(disciplinaService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Obtem Disciplina pelo Id", content = @Content(
            mediaType = MediaType.APPLICATION_JSON, schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaDTO.class)))
    @APIResponse(responseCode = "404", description = "Disciplina não encontrada pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Buscar Disciplina por Id", description = "Busca Disciplina por Id")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return disciplinaService.findById(id)
                .map(disciplinaDTO -> Response.ok(disciplinaDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Operation(summary = "Salvar Disciplina", description = "Cria uma nova Disciplina")
    @APIResponse(responseCode = "201", description = "Disciplina salva", content = @Content(schema = @Schema(implementation = DisciplinaDTO.class)))
    @APIResponse(responseCode = "400", description = "Já existe uma Disciplina com esse Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response post(@NotNull @Valid DisciplinaDTO disciplinaDTO, @Context UriInfo uriInfo) {
        disciplinaService.save(disciplinaDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(disciplinaDTO.getDiscId())).build();
        return Response.created(uri).entity(disciplinaDTO).build();
    }

    @PUT
    @Path("{id}")
    @APIResponse(responseCode = "204", description = "Disciplina Atualizada", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = DisciplinaDTO.class)))
    @APIResponse(responseCode = "400", description = "Não foi encontrado Id para a Disciplina requerida", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Atualizar Disciplina", description = "Atualiza os dados da Disciplina")
    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid DisciplinaDTO disciplinaDTO) {
        if (!Objects.equals(id, disciplinaDTO.getDiscId())) {
            throw new ServiceException("O id correspondente também deverá ser passado no parâmetro");
        }
        disciplinaService.update(disciplinaDTO);
        return Response.ok(disciplinaDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Disciplina", description = "Exclui os dados da Disciplina")
    @APIResponse(responseCode = "204", description = "Registro excluído com sucesso")
    public void delete(@PathParam("id") Long id) {
        disciplinaService.excluir(id);
    }
}



