package org.barros.modules.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.barros.modules.dto.response.AvaliacaoDTO;
import org.barros.modules.dto.response.CursoDTO;
import org.barros.modules.exception.ServiceException;
import org.barros.modules.service.AvaliacaoService;
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
@Tag(name = "Curso", description = "Endpoint(s) relacionado(s) a manipulação de Cursos")
@SecurityRequirement(name = "jwt")
@AllArgsConstructor
@Slf4j
@Path("/v1/avaliacoes")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GET
    @APIResponse(responseCode = "200", description = "Obtem todos as Avaliações", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.ARRAY, implementation = AvaliacaoDTO.class)))
    @Operation(summary = "Buscar Avaliações", description = "Obtem todos as Avaliações")
    public Response get() {
        return Response.ok(avaliacaoService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @APIResponse(responseCode = "200", description = "Obtem Avaliação pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = AvaliacaoDTO.class)))
    @APIResponse(responseCode = "404", description = "Avaliação não encontrada pelo Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Buscar Avaliação por Id", description = "Busca Avaliação por Id")
    public Response getById(@Parameter(name = "id", required = true) @PathParam("id") Long id) {
        return avaliacaoService.findById(id)
                .map(avaliacaoDTO -> Response.ok(avaliacaoDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Operation(summary = "Salvar Avaliação", description = "Cria uma nova Avaliação")
    @APIResponse(responseCode = "201", description = "Avaliação salva", content = @Content(schema = @Schema(implementation = AvaliacaoDTO.class)))
    @APIResponse(responseCode = "400", description = "Já existe uma Avaliação com esse Id", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    public Response post(@NotNull @Valid AvaliacaoDTO avaliacaoDTO, @Context UriInfo uriInfo) {
        avaliacaoService.save(avaliacaoDTO);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(avaliacaoDTO.getAvaId())).build();
        return Response.created(uri).entity(avaliacaoDTO).build();
    }

    @PUT
    @Path("{id}")
    @APIResponse(responseCode = "204", description = "Avaliação Atualizada", content = @Content(mediaType = MediaType.APPLICATION_JSON,
            schema = @Schema(type = SchemaType.OBJECT, implementation = AvaliacaoDTO.class)))
    @APIResponse(responseCode = "400", description = "Não foi encontrado Id para a Avaliação requerida", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @Operation(summary = "Atualizar Avaliação", description = "Atualiza os dados da Avaliação")
    public Response put(@Parameter(name = "id", required = true) @PathParam("id") Long id, @NotNull @Valid AvaliacaoDTO avaliacaoDTO) {
        if (!Objects.equals(id, avaliacaoDTO.getAvaId())) {
            throw new ServiceException("O id correspondente também deverá ser passado no parâmetro");
        }
        avaliacaoService.update(avaliacaoDTO);
        return Response.ok(avaliacaoDTO).build();
       // return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Excluir Avaliação", description = "Exclui os dados de uma Avaliação")
    @APIResponse(responseCode = "204", description = "Registro excluído com sucesso")
    public void delete(@PathParam("id") Long id) {
        avaliacaoService.excluir(id);
    }
}



